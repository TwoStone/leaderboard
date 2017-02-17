import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Event } from '../model/event';
import { ScoreIngredientType } from '../model/score-ingredient-type';
import { EventService } from './event.service';

import { ModelService } from '../model/model';

@Component({
    templateUrl: './edit-event.component.html'
})
export class EditEventComponent implements OnInit {
    public event: Event;
    public ScoreIngredientType = ScoreIngredientType;
    private competitionId: number;

    constructor( 
        private eventService: EventService,
        private route: ActivatedRoute,
        private router: Router,
        private modelService: ModelService) {
    }

    public addPart() {
        this.event.recipe.parts.push({
            name: '',
            type: ''
        });
    }

    public ngOnInit() {
        this.modelService.onCompetitionUpdate.map((competition) => {
            return { id: competition.id }
        }).zip(this.route.params, Object.assign).subscribe((params) => {
                let eventId = params['eventId'];
                this.competitionId = params['id'];
                if (eventId === 'new') {
                    this.event = {
                        name: '',
                        description: '',
                        scalable: false,
                        recipe: {
                            parts: []
                        }
                    };
                } else {
                    this.eventService
                        .getEvent(this.competitionId, eventId)
                        .subscribe((event) => this.event = event)
                }
            });
    }

    public onSubmit() {
        this.eventService.saveEvent(this.competitionId, this.event).subscribe((event) => {
            this.modelService.updateModel();
            this.router.navigate(['../../events'], { relativeTo: this.route });
        });
    }

    public onAbort() {
        this.router.navigate(['../../events'], { relativeTo: this.route });
    }
}

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { EventService } from './event.service';
import { Event } from '../model/event';
import { ScoreIngredientType } from '../model/score-ingredient-type';

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
        private router: Router) {
    }

    public addPart() {
        this.event.type.parts.push({
            name: '',
            type: ''
        });
    }

    public ngOnInit() {
        this.route.params.subscribe((params) => {
                let eventId = params['eventId'];
                this.competitionId = +params['id'];
                if (eventId == 'new') {
                    this.event = {
                        name: '',
                        description: '',
                        scalable: false,
                        type: {
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
            this.router.navigate(['../../events'], { relativeTo: this.route });
        });
    }

    public onAbort() {
        this.router.navigate(['../../events'], { relativeTo: this.route });
    }
}

import {
    Component,
    OnInit,
    OnDestroy,
    Pipe,
    PipeTransform
} from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import { Subscription } from 'rxjs';

import { DragulaService } from 'ng2-dragula';

import { CompetitionService } from '../competition/competition.service';

import {
    ModelService,
    Event
} from '../model/model';

@Component({
    selector: 'event-list',
    templateUrl: './event-list.component.html'
})
export class EventListComponent implements OnInit, OnDestroy {

    public events: Event[];

    private subscription: Subscription;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private dragulaService: DragulaService,
        private competitionService: CompetitionService,
        private service: ModelService) {
    }

    public ngOnInit() {
        this.service.onCompetitionUpdate.subscribe((comp) => {
            this.events = comp.events;
        });

        this.subscription = this.dragulaService.dropModel.subscribe(() => {
            this.competitionService.save(this.service.competition).subscribe(() => {
                this.service.updateModel();
            });
        });
    }

    public ngOnDestroy() {
        this.subscription.unsubscribe();
    }

    public editEvent(e: Event) {
        this.router.navigate([e.id], {relativeTo: this.route});
    }

    public createEvent() {
        this.router.navigate(['new'], { relativeTo: this.route});
    }

    public goToHeats(e: Event) {
        this.router.navigate([e.id, 'heats'], { relativeTo: this.route });
    }
}

import {
    Component,
    OnInit,
    Pipe,
    PipeTransform
} from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import {
    ModelService,
    Event
} from '../model/model';

@Component({
    selector: 'event-list',
    templateUrl: './event-list.component.html'
})
export class EventListComponent implements OnInit {

    public events: Event[];

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private service: ModelService) {
    }

    public ngOnInit() {
        this.service.onCompetitionUpdate.subscribe((comp) => {
            this.events = comp.events;
        });
    }
    public editEvent(e: Event) {
        this.router.navigate(['../event', e.id], {relativeTo: this.route});
    }

    public createEvent() {
        this.router.navigate(['../event/new'], { relativeTo: this.route})
    }
}

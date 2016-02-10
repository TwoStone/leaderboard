import {
    Component,
    OnInit
} from 'angular2/core';

import {
    ModelService,
    Event
} from '../model/model';

import {
    CreateEvent
} from './create-event.component'

@Component({
    selector: 'event-list',
    template: `
        <create-event></create-event>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Event</th>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="#event of events; #i = index">
                        <td>{{ i + 1 }}</td>
                        <td>{{ event.name }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    `,
    directives: [CreateEvent]
})
export class EventList implements OnInit {

    events: Event[];

    constructor(private service: ModelService) {
    }

    ngOnInit() {
        this.service.onCompetitionUpdate.subscribe(comp => {
            this.events = comp.events;
        });
    }
}

import {
    Component,
    OnInit
} from 'angular2/core';

import {
    ModelService,
    Event
} from '../model/model';

import {
    CompetitionService,
    NewEvent
} from '../competition/competition.service';

@Component({
    selector: 'event-list',
    template: `
        <create-event></create-event>
        <table>
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

class EventModel implements NewEvent {
    name: string;
}


@Component({
    selector: 'create-event',
    template: `
         <form (ngSubmit)="onSubmit()" class="form">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text"
                    class="form-control"
                    [(ngModel)]="model.name"
                    ngControl="name"
                    #name="ngForm"
                    required>
                <div [hidden]="name || name.valid" class="alert alert-danger">
                    Name is required
                </div>
            </div>
            <button type="submit">Add</button>
        </form>
    `
})
export class CreateEvent {

    model: EventModel = new EventModel();

    constructor(
        private service: ModelService,
        private competitionService: CompetitionService) {
    }

    onSubmit() {
        this.competitionService.addEvent(this.service.competition, this.model).subscribe(e => {
            this.service.updateModel();
            this.model = new EventModel();
        });
    }
}
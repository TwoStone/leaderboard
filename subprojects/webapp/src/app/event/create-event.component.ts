import {
    Component,
    OnInit,
    Output,
    EventEmitter
} from 'angular2/core';

import {
    ModelService,
    Event,
    EventType
} from '../model/model';

import {
    CompetitionService,
    NewEvent
} from '../services';

class EventModel implements NewEvent {
    name: string;
    description: string = '';
    type: EventType;
    scalable: boolean;
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
            <div class="form-group">
                <label for="type">Type</label>
                <select class="form-control"
                    [(ngModel)]="model.type"
                    #type="ngForm"
                    ngControl="type">
                    <option [value]="eventType.FOR_TIME">for time</option>
                    <option [value]="eventType.FOR_POINTS">for points</option>
                </select>
            </div>
            <div class="checkbox">
                <label> 
                    <input type="checkbox" [(ngModel)]="model.scalable" ngControl="scalable" #scalable="ngForm"> Is scalable
                </label>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea 
                    class="form-control" 
                    [(ngModel)]="model.description" 
                    #description="ngForm" 
                    ngControl="description">
                </textarea>
            </div>
            <button class="btn btn-primary" type="submit">Add</button>
        </form>
    `
})
export class CreateEvent implements OnInit {

    model: EventModel = new EventModel();

    @Output() onCreated = new EventEmitter();

    eventType = EventType;

    constructor(
        private service: ModelService,
        private competitionService: CompetitionService) {
    }

    ngOnInit() {
        this.model.type = EventType.FOR_TIME;
    }

    onSubmit() {
        this.competitionService.addEvent(this.service.competition, this.model).subscribe(e => {
            this.service.updateModel();
            this.model = new EventModel();
            this.onCreated.emit(e);
        });
    }
}
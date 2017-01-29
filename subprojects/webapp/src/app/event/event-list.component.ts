import {
    Component,
    OnInit,
    Pipe,
    PipeTransform
} from '@angular/core';

import {
    ModelService,
    Event,
    EventType
} from '../model/model';

import {
    CreateEventComponent
} from './create-event.component'

@Pipe({name: 'typeName'})
export class TypeNamePipe implements PipeTransform {
    transform(type: EventType, args: any[]) {
        switch (type) {
            case EventType.FOR_TIME:
                return 'time';
            case EventType.FOR_POINTS:
                return 'points';
            default:
                return 'unknown';
        }
    }
}

@Component({
    selector: 'event-list',
    template: `
        <div bsModal #modal="bs-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="pull-left">Create Event</h3>
                        <button type="button" class="close pull-right" (click)="modal.hide()" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <create-event (onCreated)="modal.hide()"></create-event>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-primary" (click)="modal.show()">
            <i class="fa fa-plus"></i>
            Create event
        </button>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Event</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Scalable</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="let event of events; let i = index">
                        <td width="5%">{{ i + 1 }}</td>
                        <td>{{ event.name }}</td>
                        <td>{{ event.type | typeName }}</td>
                        <td>{{ event.description }}</td>
                        <td><div *ngIf="event.scalable"><i class="fa fa-check"></i></div></td>
                    </tr>
                </tbody>
            </table>
        </div>
    `
})
export class EventListComponent implements OnInit {

    events: Event[];

    constructor(private service: ModelService) {
    }

    ngOnInit() {
        this.service.onCompetitionUpdate.subscribe(comp => {
            this.events = comp.events;
        });
    }
}

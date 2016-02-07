import {
    Component,
    OnInit,
    Input,
    Output,
    EventEmitter
} from 'angular2/core';

import {
    ModelService,
    Competitor
} from '../model/model';

import {
    CreateCompetitorComponent
} from './create-competitor.component';


@Component({
    selector: 'competitor-list-item',
    template: `
        <tr>
            <td>{{ competitor.name }}</td>
            <td>{{ competitor.division.name }}</td>
        </tr>
    `
})
export class CompetitorListItem {
    @Input() competitor: Competitor;
}

@Component({
    selector: 'competitor-list',
    template: `
        <create-competitor>
        </create-competitor>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Division</th>
                </tr>
            </thead>
            <tbody>
                <competitor-list-item *ngFor="#competitor of competitors" [competitor]="competitor">
                </competitor-list-item>
            </tbody>
        </table>
    `,
    directives: [CompetitorListItem, CreateCompetitorComponent]
})
export class CompetitorsList implements OnInit {
    competitors: Array<Competitor>;

    constructor(
        private service: ModelService
        ) {
    }

    ngOnInit() {
        this.service.onCompetitionUpdate.subscribe(comp => {
            this.competitors = comp.competitors;
        });
    }
}

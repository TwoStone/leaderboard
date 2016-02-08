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
    selector: '[competitorListItem]',
    template: `
        <td>{{ competitor.name }}</td>
        <td>{{ competitor.division.name }}</td>
    `
})
export class CompetitorListItem {
    @Input('competitorListItem') competitor: Competitor;
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
                <tr *ngFor="#competitor of competitors" [competitorListItem]="competitor">
                </tr>
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

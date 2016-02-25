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

import {
    MODAL_DIRECTIVES
} from '../tools/tools';


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
        <modal #modal>
            <modal-header>
                <h3>Register competitor</h3>
            </modal-header>
            <modal-body>
                <create-competitor (onCreated)="modal.hide()">
                </create-competitor>
            </modal-body>
        </modal>
        <button class="btn btn-primary" (click)="modal.open()">
            <i class="fa fa-plus"></i>
            Register competitor
        </button>
        <div class="table-responsive">
            <table class="table table-hover">
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
        </div>
    `,
    directives: [CompetitorListItem, CreateCompetitorComponent, MODAL_DIRECTIVES]
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

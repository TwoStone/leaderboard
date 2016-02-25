import {Component, Input} from 'angular2/core';
import {Competition} from '../model/model';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';

import {
    MODAL_DIRECTIVES
} from '../tools/tools';

import {
    CreateCompetitionComponent
} from './create-competition.component';

import {CompetitionService} from '../services';

@Component({
    selector: 'competition-list-item',
    template: `
        <a [routerLink]="['Competition', { id: competition.id }]">
        {{ competition.name }}
        </a>
    `,
    directives: [ROUTER_DIRECTIVES]
})
class CompetitionListItem {

    @Input() competition: Competition;
}

@Component({
    template: `
        <modal #modal>
            <modal-header>
                <h3>Create Competition</h3>
            </modal-header>
            <modal-body>
                <create-competition (onCreated)="modal.hide(); onCreated()">
                </create-competition>
            </modal-body>
        </modal>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <button class="btn btn-primary" (click)="modal.open()">
                        <i class="fa fa-plus"></i>
                        Create competition 
                    </button>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>
                                        Competition
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr *ngFor="#competition of competitions">
                                    <td>
                                        <competition-list-item [competition]="competition">
                                        </competition-list-item>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-2"></div>
            </div>
        </div>
    `,
    directives: [CompetitionListItem, MODAL_DIRECTIVES, CreateCompetitionComponent]
})
export class CompetitionList {
    competitions: Competition[];

    constructor(private _competitionService: CompetitionService,
        private _router: Router) {
        this._competitionService.getAll().subscribe(competitions => this.competitions = competitions);
    }

    onCreated() {
        this._competitionService.getAll().subscribe(competitions => this.competitions = competitions);
    }
}
import {Component, Input, Host, OnInit} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {MODAL_DIRECTIVES, ModalComponent} from '../tools/tools';

import {Division, Competition, ModelService} from '../model/model';
import {CompetitionService} from '../services';

import {CreateDivisionComponent} from './create-division.component';

@Component({
    selector: 'division-item',
    template: `
        {{ division.name }}
    `,
    directives: [ROUTER_DIRECTIVES]
})
class DivisionListItem {
    @Input() division: Division;
}

@Component({
    selector: 'division-list',
    template: `
        <modal #modal>
            <modal-header>
                <h4>Create Divsion</h4>
            </modal-header>
            <modal-body>
                <create-division (onCreated)="modal.hide()"></create-division>
            </modal-body>
        </modal>
        <button (click)="modal.open()" class="btn btn-default">Add division</button>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Division</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="#division of divisions">
                        <td>
                            <division-item [division]="division">
                            </division-item>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    `,
    directives: [DivisionListItem, ROUTER_DIRECTIVES, CreateDivisionComponent, MODAL_DIRECTIVES]
})
export class DivisionListComponent implements OnInit {

    divisions: Array<Division>;

    constructor(private model: ModelService) {
    }

    ngOnInit() {
        this.model.onCompetitionUpdate.subscribe(comp => {
            this.divisions = comp.divisions
        });
    }

}
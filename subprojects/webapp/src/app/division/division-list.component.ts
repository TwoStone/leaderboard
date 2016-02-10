import {Component, Input, Host, OnInit} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {Division, Competition, ModelService} from '../model/model';
import {CompetitionService} from '../competition/competition.service';

import {CreateDivisionComponent} from './create-division.component';

@Component({
    selector: 'division-item',
    template: `
        <a *ngIf="division" [routerLink]="['Division', {divisionId: division.id}]">
            {{ division.name }}
        </a>
    `,
    directives: [ROUTER_DIRECTIVES]
})
class DivisionListItem {
    @Input() division: Division;
}

@Component({
    selector: 'division-list',
    template: `
        <create-division></create-division>
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
    directives: [DivisionListItem, ROUTER_DIRECTIVES, CreateDivisionComponent]
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
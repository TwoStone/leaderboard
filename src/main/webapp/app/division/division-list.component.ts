import {Component, Input, Host, OnInit} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {Division, Competition, ModelService} from '../model/model';
import {CompetitionService} from '../competition/competition.service';

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
        <a [routerLink]="['CreateDivision']">Create Division</a>
        <ul *ngIf="divisions">
            <li *ngFor="#division of divisions">
                <division-item [division]="division">
                </division-item>
            </li>
        </ul>
    `,
    directives: [DivisionListItem, ROUTER_DIRECTIVES]
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
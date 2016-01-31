import {Component, Input, Host, OnInit} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {Division, Competition, Model} from '../model/model';
import {CompetitionComponent} from '../competition/competition.component';
import {CompetitionService} from '../competition/competition.service';

@Component({
    selector: 'division-item',
    template: `
        <a [routerLink]="['Divisions', {divisionId: division.id}]">
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
        <ul>
            <li *ngFor="#division of divisions">
                <division-item [division]="division">
                </division-item>
            </li>
        </ul>
    `,
    directives: [DivisionListItem]
})
export class DivisionListComponent implements OnInit {
    divisions: Division[];

    constructor(private _service: CompetitionService, private _params: RouteParams, private model: Model) {
    }

    ngOnInit() {
        this.model.onCompetition.subscribe(comp => {
            this.divisions = comp.divisions;
        });
    }
}
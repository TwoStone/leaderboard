import {Component, Input, Host, OnInit} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {Division, Competition, Model} from '../model/model';
import {CompetitionComponent, CompetitionSubComponent} from '../competition/competition.component';
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
            <li *ngFor="#division of competition.divisions">
                <division-item [division]="division">
                </division-item>
            </li>
        </ul>
    `,
    directives: [DivisionListItem]
})
export class DivisionListComponent extends CompetitionSubComponent implements OnInit {

    constructor(private _service: CompetitionService, private _params: RouteParams) {
        super()
    }

    ngOnInit() {
    }
}
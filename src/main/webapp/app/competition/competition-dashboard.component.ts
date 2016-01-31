import {Component, OnInit, Host} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {CompetitionService} from './competition.service';
import {CompetitionComponent} from './competition.component';

import {Competition} from '../model/model';

@Component({
    template: `
        <a [routerLink]="['Divisions']">
        Divisions
        </a>
    `,
    directives: [ROUTER_DIRECTIVES]
})
export class CompetitionDashboardComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {

    }
}

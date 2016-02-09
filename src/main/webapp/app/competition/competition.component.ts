import {Component, OnInit, ViewChild} from 'angular2/core';
import {RouteParams, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';

import {Competition, ModelService} from '../model/model';
import {CompetitionService} from './competition.service';
import {DivisionComponent} from '../division/division.component';
import {DivisionListComponent} from '../division/division-list.component';
import {CreateDivisionComponent} from '../division/create-division.component';
import {CompetitionDashboardComponent} from './competition-dashboard.component';
import {CompetitorsList} from '../competitor/competitor-list.component';
import {EventList} from '../event/event-list.component';

@Component({
    template: `
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-4">
                    <div *ngIf="model.competition">{{model.competition.name}}</div >
                    <ul>
                        <li><a [routerLink]="['Divisions']">Divisions</a></li>
                        <li><a [routerLink]="['CompetitorList']">Competitors</a></li>
                        <li><a [routerLink]="['EventList']">Events</a></li>
                    </ul>
                </div>
                <router-outlet class="col-lg-8">
                </router-outlet>
            </div>
        </div>
    `,
    providers: [ModelService],
    directives: [DivisionListComponent, ROUTER_DIRECTIVES]
})
@RouteConfig([
    { path: '/', name: 'CompetitionDashboard', component: CompetitionDashboardComponent, useAsDefault: true },
    { path: '/divisions', name: 'Divisions', component: DivisionListComponent },
    { path: '/divisions/{divisionId}', name: 'Division', component: DivisionComponent },
    { path: '/divisions.create', name: 'CreateDivision', component: CreateDivisionComponent },
    { path: '/competitors', name: 'CompetitorList', component: CompetitorsList},
    { path: '/events', name: 'EventList', component: EventList }
])
export class CompetitionComponent implements OnInit {

    competition: Competition;

    constructor(
        private routeParams: RouteParams,
        private competitionService: CompetitionService,
        private model: ModelService) {

        this.model.onCompetitionUpdate.subscribe(comp => {
            this.competition = comp
        });
    }

    ngOnInit () {
        let competitionId =  +this.routeParams.get('id');
        this.model.competitionId = competitionId;
    }
}

import {Component, OnInit, ViewChild} from 'angular2/core';
import {RouteParams, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';

import {Competition, ModelService} from '../model/model';
import {DivisionComponent} from '../division/division.component';
import {DivisionListComponent} from '../division/division-list.component';
import {CreateDivisionComponent} from '../division/create-division.component';
import {CompetitionDashboardComponent} from './competition-dashboard.component';
import {CompetitorsList} from '../competitor/competitor-list.component';
import {EventList} from '../event/event-list.component';
import {ScoreComponent} from '../score/score.component';
import {ScoreboardComponent} from '../scoreboard/scoreboard.component';

import {CompetitionService} from '../services';

@Component({
    template: `
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-6 col-sm-3">
                    <div *ngIf="competition">
                        <h4>
                            <a [routerLink]="['CompetitionDashboard']">
                                {{ competition.name }}
                            </a>
                        </h4>
                    </div >
                    <ul class="nav">
                        <li><a [routerLink]="['Divisions']">Divisions</a></li>
                        <li><a [routerLink]="['CompetitorList']">Competitors</a></li>
                        <li><a [routerLink]="['EventList']">Events</a></li>
                        <li><a [routerLink]="['Scores']">Scores</a></li>
                        <li><a [routerLink]="['Scoreboard']">Scoreboard</a></li>
                    </ul>
                </div>
                <div class="col-xs-12 col-sm-9">
                    <router-outlet></router-outlet>
                </div>
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
    { path: '/events', name: 'EventList', component: EventList },
    { path: '/scores', name: 'Scores', component: ScoreComponent },
    { path: '/scoreboard', name: 'Scoreboard', component: ScoreboardComponent}
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

import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_BINDINGS} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/publishLast';


import {CompetitionService} from './competition/competition.service';
import {CompetitionList} from './competition/competition-list.component';
import {CompetitionComponent} from './competition/competition.component';
import {CreateCompetitionComponent} from './competition/create-competition.component';

@Component({
    selector: 'leaderboard-app',
    template: `
        <h2>Leader<strong>Board</strong></h2>
        <router-outlet></router-outlet>
    `,
    providers: [CompetitionService],
    directives: [ROUTER_DIRECTIVES]
})
@RouteConfig([
    { path: '/', name: 'Competitions', component: CompetitionList, useAsDefault: true },
    { path: 'competition/:id/...', name: 'Competition', component: CompetitionComponent },
    { path: 'competition.create', name: 'CreateCompetition', component: CreateCompetitionComponent}
])
export class AppComponent {

}
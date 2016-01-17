import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_BINDINGS} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';
import {CompetitionService} from './competition/competition.service'
import {CompetitionList} from './competition/competitionlist.component';
import {CompetitionComponent} from './competition/competition.component';
import 'rxjs/add/operator/map';

@Component({
    selector: 'leaderboard-app',
    template: `
    	<h2>Leader<strong>Board</strong></h2>
		<router-outlet></router-outlet>
    `,
    providers: [CompetitionService, HTTP_PROVIDERS, ROUTER_BINDINGS],
    directives: [ROUTER_DIRECTIVES]
})
@RouteConfig([
    { path: '/', name: 'Competitions', component: CompetitionList },
    { path: 'competition/:id', name: 'Competition', component: CompetitionComponent }
    //, { path: 'create-competition', name: 'CreateCompetition', component: CreateCompetitionComponent }
])
export class AppComponent {
	constructor() {

	}
}
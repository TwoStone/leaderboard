import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_BINDINGS} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/publishLast';


import {SERVICE_PROVIDERS} from './services';
import {CompetitionList} from './competition/competition-list.component';
import {CompetitionComponent} from './competition/competition.component';
import {CreateCompetitionComponent} from './competition/create-competition.component';
import {StandaloneScoreboardComponent} from './scoreboard/scoreboard-standalone.component';

@Component({
    selector: 'leaderboard-app',
    template: `
        <div class="navbar navbar-inverse navbar-static-top">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">
                <i class="fa fa-trophy"></i>&nbsp;
                Leader<strong>Board</strong>
              </a>
            </div>
          </div>
        </div>
        <router-outlet></router-outlet>
        <footer class="footer">
            <div class="container">
                <p class="text-muted">
                    Powered by <a href="https://github.com/TwoStone/leaderboard">Leader<strong>Board</strong></a>
                </p>
            </div>
        </footer>
    `,
    styles: [`
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            /* Set the fixed height of the footer here */
            height: 60px;
            background-color: #f5f5f5;
        }
        .container .text-muted {
            margin: 20px 0;
        }
    `],
    providers: [SERVICE_PROVIDERS],
    directives: [ROUTER_DIRECTIVES]
})
@RouteConfig([
    { path: '/', name: 'Competitions', component: CompetitionList, useAsDefault: true },
    { path: 'competition/:id/...', name: 'Competition', component: CompetitionComponent },
    { path: 'competition.create', name: 'CreateCompetition', component: CreateCompetitionComponent},
    { path: 'scores/:competitionId', name: 'ScoreboardStandalone', component: StandaloneScoreboardComponent}
])
export class AppComponent {

}
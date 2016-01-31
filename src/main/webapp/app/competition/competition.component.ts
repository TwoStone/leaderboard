import {Component, OnInit, Output} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {Competition} from './competition';
import {CompetitionService} from './competition.service';

@Component({
    template: `
        <h1 *ngIf="competition">{{competition.name}}</h1>
    `,
    providers: []
})
export class CompetitionComponent implements OnInit {

    competition: Competition;

    constructor(
        private routeParams: RouteParams,
        private competitionService: CompetitionService) {
    }

    ngOnInit () {
        let competitionId = this.routeParams.get('id');

        this.competitionService.get(competitionId).subscribe((competition: Competition) => this.competition = competition);
    }
}
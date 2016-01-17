import {Component, OnInit, Output} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';

import {Competition} from './competition';
import {CompetitionService} from './competition.service';

@Component({
	template: `
		<h1 *ngIf="competition">{{competition.name}}</h1>
	`,
	providers: [HTTP_PROVIDERS]
})
export class CompetitionComponent implements OnInit {

	competition: Competition;

	constructor(
		private _routeParams: RouteParams,
		private _competitionService: CompetitionService) {

	}

	ngOnInit () {
		let competitionId = this._routeParams.get('id');

		this._competitionService.get(competitionId).subscribe(competition => this.competition = competition);
	}
}
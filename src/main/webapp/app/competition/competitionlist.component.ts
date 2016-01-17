import {Component} from 'angular2/core';
import {CompetitionService} from './competition.service';
import {Competition} from './competition';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    template: `
        <ul>
            <li *ngFor="#competition of competitions">
                <a [routerLink]="['Competition', { id: competition.id}]">
                {{ competition.name }}
                </a>
            </li>
        </ul>
    `,
    directives: [ROUTER_DIRECTIVES]
})
export class CompetitionList {
    competitions: Competition[];

    constructor(private _competitionService: CompetitionService) {
        this._competitionService.getCompetitions().subscribe(competitions => this.competitions = competitions);
    }
}
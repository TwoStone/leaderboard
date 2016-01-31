import {Component, Input} from 'angular2/core';
import {CompetitionService} from './competition.service';
import {Competition} from './competition';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';


@Component({
    selector: 'competition-list-item',
    template: `
        <li>
            <a [routerLink]="['Competition', { id: competition.id }]">
            {{ competition.name }}
            </a>
        </li>
    `,
    directives: [ROUTER_DIRECTIVES]
})
class CompetitionListItem {

    @Input() competition: Competition;
}

@Component({
    template: `
        <button (click)="create()"> Create new</button>
        <ul>
            <competition-list-item *ngFor="#competition of competitions" [competition]="competition">
            </competition-list-item>
        </ul>
    `,
    directives: [CompetitionListItem]
})
export class CompetitionList {
    competitions: Competition[];

    constructor(private _competitionService: CompetitionService,
        private _router: Router) {
        this._competitionService.getAll().subscribe(competitions => this.competitions = competitions);
    }

    create() {
        this._router.navigate(['CreateCompetition']);
    }
}
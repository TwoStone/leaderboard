import {Component} from 'angular2/core';
import {Router} from 'angular2/router';
import {NgForm} from 'angular2/common';
import {CompetitionService, NewCompetition} from '../competition/competition.service';


class CompetitionModel implements NewCompetition {
    name: string;

    constructor() {
    }
}


@Component({
    selector: 'create-competition',
    templateUrl: 'app/competition/create-competition.html'
})
export class CreateCompetitionComponent {

    model: CompetitionModel;

    constructor(private _competitionService: CompetitionService, private _router: Router) {
        this.model = new CompetitionModel();
    }

    onSubmit() {
        this._competitionService.create(this.model).subscribe(() => {
            this._router.navigate(['Competitions']);
        });
    }
}
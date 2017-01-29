import {
    Component,
    EventEmitter,
    Output
} from '@angular/core';

import { Router } from '@angular/router';

import { CompetitionService, NewCompetition } from '../services/competition.service';

class CompetitionModel implements NewCompetition {
    name: string;

    constructor() {
    }
}

@Component({
    selector: 'create-competition',
    templateUrl: './create-competition.html'
})
export class CreateCompetitionComponent {

    model: CompetitionModel;

    @Output() onCreated = new EventEmitter();

    constructor(private _competitionService: CompetitionService, private _router: Router) {
        this.model = new CompetitionModel();
    }

    onSubmit() {
        this._competitionService.create(this.model).subscribe(competition => {
            this.onCreated.emit(competition);
        });
    }
}
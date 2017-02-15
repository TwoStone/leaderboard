import {
    Component,
    EventEmitter,
    Output
} from '@angular/core';

import { Router } from '@angular/router';

import { CompetitionService, NewCompetition } from '../competition/competition.service';

@Component({
    selector: 'create-competition',
    templateUrl: './create-competition.html'
})
export class CreateCompetitionComponent {

    public model: NewCompetition;

    @Output() public onCreated = new EventEmitter();

    constructor(private _competitionService: CompetitionService, private _router: Router) {
        this.model = {
            name: ''
        };
    }

    onSubmit() {
        this._competitionService.create(this.model).subscribe(competition => {
            this.onCreated.emit(competition);
        });
    }
}
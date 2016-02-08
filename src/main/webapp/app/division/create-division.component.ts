import {Component} from 'angular2/core';
import {Router} from 'angular2/router';

import {CompetitionService, NewDivision} from '../competition/competition.service';
import {ModelService} from '../model/model';

class DivisionModel implements NewDivision {
    name: string;
}

@Component({
    template: `
        <h1>Create new Division</h1>
        <form (ngSubmit)="onSubmit()">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" [(ngModel)]="model.name" ngControl="name" #name="ngForm" required>
                <div [hidden]="name || name.valid" class="alert alert-danger">
                    Name is required
                </div>
            </div>

            <button type="submit" class="btn btn-default">Create</button>
        </form>
    `
})
export class CreateDivisionComponent {

    model: DivisionModel;

    constructor(
        private _competitionService: CompetitionService,
        private _router: Router,
        private _model: ModelService) {
        this.model = new DivisionModel();
    }

    onSubmit() {
        this._competitionService.addDivision(this._model.competition, this.model).subscribe(d => {
            this._model.updateModel();
            this._router.navigate(['Divisions']);
        });
    }
}
import {
    Component,
    Output,
    EventEmitter
} from 'angular2/core';
import {Router} from 'angular2/router';

import {
    CompetitionService, 
    NewDivision
} from '../services';
import {ModelService} from '../model/model';

class DivisionModel implements NewDivision {
    name: string;
}

@Component({
    selector: 'create-division',
    template: `
        <form class="form" (ngSubmit)="onSubmit()">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" [(ngModel)]="model.name" ngControl="name" #name="ngForm" required autofocus>
                <div [hidden]="name || name.valid" class="alert alert-danger">
                    Name is required
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    `
})
export class CreateDivisionComponent {

    model: DivisionModel;

    @Output() onCreated = new EventEmitter();

    constructor(
        private _competitionService: CompetitionService,
        private _router: Router,
        private _model: ModelService) {
        this.model = new DivisionModel();
    }

    onSubmit() {
        this._competitionService.addDivision(this._model.competition, this.model).subscribe(d => {
            this._model.updateModel();
            this.model = new DivisionModel();
            this.onCreated.emit(d);
        });
    }
}
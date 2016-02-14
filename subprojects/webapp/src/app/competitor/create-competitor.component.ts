import {
    Component,
    OnInit
} from 'angular2/core';

import {
    ModelService,
    Division
} from '../model/model';

import {
    CompetitionService,
    NewCompetitor
} from '../services';

class CompetitorModel implements NewCompetitor {
    name: string;
    divisionId: number;
}


@Component({
    selector: 'create-competitor',
    template: `
        <form (ngSubmit)="onSubmit()" class="form">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text"
                    class="form-control"
                    [(ngModel)]="model.name"
                    ngControl="name"
                    #name="ngForm"
                    required>
                <div [hidden]="name || name.valid" class="alert alert-danger">
                    Name is required
                </div>
            </div>
            <div class="form-group">
                <label for="division">Division</label>
                <select class="form-control"
                    [(ngModel)]="model.divisionId"
                    ngControl="division"
                    #division="ngForm"
                    required>
                    <option *ngFor="#division of divisions" [value]="division.id">
                        {{ division.name }}
                    </option>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Register</button>
        </form>
    `

})
export class CreateCompetitorComponent implements OnInit {

    divisions: Array<Division>;
    model: CompetitorModel;

    constructor(
        private service: ModelService,
        private competitionService: CompetitionService) {
        this.model = new CompetitorModel();
    }

    ngOnInit() {
        this.service.onCompetitionUpdate.subscribe(comp => {
            this.divisions = comp.divisions;
        });
    }

    onSubmit() {
        this.competitionService.addCompetitor(this.service.competition, this.model).subscribe(competitor => {
            this.service.updateModel();
            this.model = new CompetitorModel();
        });
    }
}
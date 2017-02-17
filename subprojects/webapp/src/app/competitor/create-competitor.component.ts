import {
    Component,
    EventEmitter,
    OnInit,
    Output
} from '@angular/core';

import {
    Division,
    ModelService
} from '../model/model';

import { CompetitionService, NewCompetitor } from '../competition/competition.service';

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
                    name="name"
                    required>
            </div>
            <div class="form-group">
                <label for="division">Division</label>
                <select class="form-control"
                    [(ngModel)]="model.divisionId"
                    ngControl="division"
                    name="division"
                    required>
                    <option *ngFor="let division of divisions" [value]="division.id">
                        {{ division.name }}
                    </option>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Register</button>
        </form>
    `

})
export class CreateCompetitorComponent implements OnInit {

    divisions: Division[];
    model: CompetitorModel;

    @Output() onCreated = new EventEmitter();

    constructor(
        private service: ModelService,
        private competitionService: CompetitionService) {
        this.model = new CompetitorModel();
    }

    ngOnInit() {
        this.service.onCompetitionUpdate.subscribe((comp) => {
            this.divisions = comp.divisions;
        });
    }

    onSubmit() {
        this.competitionService.addCompetitor(this.service.competition, this.model).subscribe((competitor) => {
            this.service.updateModel();
            this.onCreated.emit(competitor);
            this.model = new CompetitorModel();
        });
    }
}
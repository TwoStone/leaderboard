import { Component, Input } from '@angular/core';
import { Competition } from '../model/model';

import {
    CreateCompetitionComponent
} from './create-competition.component';

import { CompetitionService } from '../services/competition.service';

@Component({
    templateUrl: './competition-list.component.html'
})
export class CompetitionListComponent {
    public competitions: Competition[];

    constructor(private _competitionService: CompetitionService) {
        this._competitionService.getAll().subscribe((competitions) => this.competitions = competitions);
    }

    public onCreated() {
        this._competitionService.getAll().subscribe((competitions) => this.competitions = competitions);
    }
}
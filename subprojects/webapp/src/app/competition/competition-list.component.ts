import { Component } from '@angular/core';
import { Competition } from '../model/model';

import { CompetitionService } from '../competition/competition.service';

@Component({
    templateUrl: './competition-list.component.html',
    styleUrls: ['./competition-list.component.css']
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
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Competition, ModelService } from '../model/model';

import { CompetitionService } from '../competition/competition.service';

@Component({
    templateUrl: './competition.component.html',
    styleUrls: ['./competition.component.css']
})
export class CompetitionComponent implements OnInit {

    public competition: Competition;

    constructor(
        private route: ActivatedRoute,
        private competitionService: CompetitionService,
        private model: ModelService) {

        this.model.onCompetitionUpdate.subscribe((comp) => {
            this.competition = comp
        });
    }

    public ngOnInit () {
        let key = 'id';
        this.route.params.subscribe((params) => {
            this.model.competitionId = +params[key];
        });
    }
}

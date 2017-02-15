import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Competition, ModelService } from '../model/model';
import { DivisionComponent } from '../division/division.component';
import { DivisionListComponent } from '../division/division-list.component';
import { CreateDivisionComponent } from '../division/create-division.component';
import { CompetitionDashboardComponent } from './competition-dashboard.component';
import { CompetitorsListComponent } from '../competitor/competitor-list.component';
import { EventListComponent } from '../event/event-list.component';
import { ScoreComponent } from '../score/score.component';
import { ScoreboardComponent } from '../scoreboard/scoreboard.component';

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

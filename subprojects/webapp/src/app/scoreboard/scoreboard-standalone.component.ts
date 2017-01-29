import {
    Component,
    OnInit,
    Input
} from '@angular/core';

import { ActivatedRoute } from '@angular/router';

import {
    Competition,
    Division,
    RankedCompetitionScore,
    RankedEventScore,
    Event,
    EventType,
} from '../model/model';

import { CompetitionService } from '../services/competition.service';
import { RankingService } from '../services/ranking.service';

@Component({
    template: `
        <div *ngIf="competition">
            <div class="header">
                <h1>{{competition.name}}</h1>
                <h3>{{division.name}}</h3>
            </div>
        <div class="row">
        <div class="col-lg-12">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th *ngFor="let event of events">
                            {{ event. name }} - Rank
                        </th>
                        <th>Score</th>
                        <th>Rank</th>
                    </tr>
                </thead>   
                <tbody>
                    <tr *ngFor="let ranking of rankings">
                        <td>{{ ranking.competitor.name }}</td>
                        <td *ngFor="let score of ranking.eventScores">
                            {{ score.rank }} ({{ score.score | asScore:"-" }})
                        </td> 
                        <td>{{ ranking.score }}</td>
                        <td>{{ ranking.rank }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        </div>
        </div>
        </div>
    `,
    styles: [
    `.navbar {
        display: none;
     }
     .footer {
         diplay: none;
     }
     
     .header {
        width: 100%;
        text-align: center;   
     }
    `
    ]
})
export class StandaloneScoreboardComponent implements OnInit {

    public competition: Competition;

    private competitionId: string;
    private divisionId: string;
    private index: number  = 0;
    private rankings: RankedCompetitionScore[];
    private events: Event[];

    constructor(
        private competitionService: CompetitionService,
        private rankingService: RankingService,
        private route: ActivatedRoute) {

        this.route.params.subscribe((params) => {
            let comIdKey = 'competitionId'
            this.competitionId = params[comIdKey];
        });
    }

    public ngOnInit() {
        this.competitionService.get(+this.competitionId).subscribe((competition) => {
            this.competition = competition;
            this.events = this.competition.events;
            this.updateView();
        });
    }

    public updateView() {
        let division = this.getNextDivision();
        this.rankingService.getRankingForCompetition(this.competition.id, division.id).subscribe((ranking) => {
            this.rankings = ranking;
            setTimeout(() => {
                this.updateView();
            }, 30 * 1000);
        });
    }

    get division() {
        return this.competition.divisions[this.index];
    }

    private getNextDivision(): Division {
        this.index = (this.index + 1) % (this.competition.divisions.length);
        return this.competition.divisions[this.index];
    }
}

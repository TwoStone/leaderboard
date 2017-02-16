import { StandaloneScoreBoardModule } from './standalone-score-board.module';
import { Observable } from 'rxjs/Observable';
import { AbstractCompetitionComponent } from '../../competition/shared/abstract-competition.component';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RankingService } from '../../services/ranking.service';
import { CompetitionService } from '../../services/competition.service';
import { Competition } from '../../model/competition';
import { RankedCompetitionScore } from '../../model/ranked-competition-score';
import { Event } from '../../model/event';
import { Division } from '../../model/division';

@Component({
    selector: 'standalone-score-board',
    templateUrl: 'standalone-score-board.component.html',
    styleUrls: ['standalone-score-board.component.css']
})
export class StandaloneScoreBoardComponent extends AbstractCompetitionComponent {

    private static DIVISION_QUERY_PARAM = 'division';

    private competitionId: number;
    private rankings: Observable<RankedCompetitionScore[]>;
    private events: Observable<Event[]>;
    private division: Observable<Division>;

    constructor(
        private competitionService: CompetitionService,
        private rankingService: RankingService,
        private router: Router,
        route: ActivatedRoute) {
        super(route);

        this.competition.subscribe((c) => {
            this.competitionId = c.id;
        });

        this.events = this.competition.map((competition) => {
            return competition.events;
        });

        this.division = this.route.queryParams.flatMap((params) => {
            let divisionId = params[StandaloneScoreBoardComponent.DIVISION_QUERY_PARAM] ;
            if (divisionId) {
                return this.competition.map((c) => {
                    return c.divisions.find((d) => d.id === +divisionId);
                });
            } else {
                return this.competition.map((c) => {
                    return c.divisions[0];
                });
            }
        });

        this.rankings = this.division.flatMap((d) => {
            return this.rankingService.getRankingForCompetition(this.competitionId, d.id);
        });

        this.rankings.subscribe(() => {
            setTimeout(() => {
                this.nextDivision.subscribe((d) => {
                    let queryParams = {};
                    queryParams[StandaloneScoreBoardComponent.DIVISION_QUERY_PARAM] = d.id

                    this.router.navigate([], {
                        queryParams
                    });
                });
            }, 10 * 1000);
        });
    }

    private get nextDivision(): Observable<Division> {
        return this.competition.zip(this.division).map((values) => {
            let c = values[0];
            let d = values[1];
            let currentIndex = c.divisions.indexOf(d);
            let nextIndex = (currentIndex + 1) % c.divisions.length;
            return c.divisions[nextIndex];
        });
    }
}

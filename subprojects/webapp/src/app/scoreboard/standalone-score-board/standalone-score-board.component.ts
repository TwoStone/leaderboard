import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AbstractCompetitionComponent } from '../../competition/shared/abstract-competition.component';
import { Division } from '../../model/division';
import { Event } from '../../model/event';
import { RankedCompetitionScore } from '../../model/ranked-competition-score';
import { RankingService } from '../../services/ranking.service';

@Component({
    selector: 'standalone-score-board',
    templateUrl: 'standalone-score-board.component.html',
    styleUrls: ['standalone-score-board.component.scss']
})
export class StandaloneScoreBoardComponent extends AbstractCompetitionComponent {

    private static DIVISION_QUERY_PARAM = 'division';

    private competitionId: number;
    private rankings: Observable<RankedCompetitionScore[]>;
    private events: Observable<Event[]>;
    private division: Observable<Division>;

    constructor(
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

        this.rankings.subscribe((r) => {
            setTimeout(() => {
                let totalHeight = $('.score-container').height()
                let itemHeight = $('.score-row').first().height();
                let self = this;
                $('.score-container').animate({
                    scrollTop: ($('.score-row').last().offset().top + itemHeight - totalHeight)
                }, {
                    easing: 'linear',
                    always: (() => {
                        self.scrollFinished()
                    }),
                    duration: r.length * 2500,
                });
            }, 2500);
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

    private scrollFinished() {

        setTimeout(() => {
            this.nextDivision.subscribe((d) => {
                let queryParams = {};
                queryParams[StandaloneScoreBoardComponent.DIVISION_QUERY_PARAM] = d.id

                this.router.navigate([], {
                    queryParams
                });
            });
        }, 2500);
    }
}

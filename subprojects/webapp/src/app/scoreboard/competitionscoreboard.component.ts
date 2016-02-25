import {
    Component,
    OnChanges,
    Input,
    ViewEncapsulation
} from 'angular2/core';

import {
    NgFor
} from 'angular2/common';

import {
    RankingService
} from '../services';

import {
    RankedCompetitionScore,
    RankedEventScore,
    ModelService,
    Event
} from '../model/model';

import {
    ScoreDisplayPipe
} from '../score/pipes';

@Component({
    selector: 'event-header',
    template: `
        <th>{{event.name}} - Score</th>
        <th>{{event.name}} - Rank</th>
    `
})
class EventHeader {

    @Input() event: Event;
}

@Component({
    selector: 'event-score',
    template: `
        <td>
            {{score.score.score}}
        </td>
        <td>
            {{score.rank}}
        </td>
    `
})
class EventScore {
    @Input() score: RankedEventScore;
}

@Component({
    selector: 'competitionScoreBoard',
    template: `
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th *ngFor="#event of events">
                            {{ event. name }} - Rank
                        </th>
                        <th>Score</th>
                        <th>Rank</th>
                    </tr>
                </thead>   
                <tbody>
                    <tr *ngFor="#ranking of rankings">
                        <td>{{ ranking.competitor.name }}</td>
                        <td *ngFor="#score of ranking.eventScores">
                            {{ score.rank }} ({{ score.score | asScore:"-" }})
                        </td> 
                        <td>{{ ranking.score }}</td>
                        <td>{{ ranking.rank }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    `,
    directives: [EventScore, EventHeader],
    pipes: [ScoreDisplayPipe]
})
export class CompetitionScoreboard implements OnChanges {

    @Input() competitionId: number;
    @Input() divisionId: number;

    rankings: RankedCompetitionScore[];
    events: Event[];

    constructor(private rankingService: RankingService,
        private modelService: ModelService) {
        modelService.onCompetitionUpdate.subscribe(competition => {
            this.events = competition.events;
        });
    }

    ngOnChanges() {
        if (this.competitionId && this.divisionId) {
            this.rankingService.getRankingForCompetition(this.competitionId, this.divisionId).subscribe(rankings => {
                this.rankings = rankings;
            });
        }
    }
}
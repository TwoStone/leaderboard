import {
    Component,
    Input,
    OnChanges
} from '@angular/core';

import {
    RankingService
} from '../services/ranking.service';

import {
    Event,
    ModelService,
    RankedCompetitionScore,
    RankedEventScore
} from '../model/model';

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
                            {{ score.rank }} ({{ score.score | scoreToString:"-" }})
                        </td> 
                        <td>{{ ranking.score }}</td>
                        <td>{{ ranking.rank }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    `
})
export class CompetitionScoreboard implements OnChanges {

    @Input() competitionId: number;
    @Input() divisionId: number;

    rankings: RankedCompetitionScore[];
    events: Event[];

    constructor(private rankingService: RankingService,
                private modelService: ModelService) {
        modelService.onCompetitionUpdate.subscribe((competition) => {
            this.events = competition.events;
        });
    }

    ngOnChanges() {
        if (this.competitionId && this.divisionId) {
            this.rankingService.getRankingForCompetition(this.competitionId, this.divisionId).subscribe((rankings) => {
                this.rankings = rankings;
            });
        }
    }
}
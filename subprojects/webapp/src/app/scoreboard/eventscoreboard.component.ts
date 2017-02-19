import {
    Component,
    Input,
    OnChanges
} from '@angular/core';

import { RankingService } from '../services/ranking.service';

import {
    RankedEventScore
} from '../model/model';

@Component({
    selector: 'eventScoreBoard',
    viewProviders: [],
    template: `
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Rank</th>
                        <th>Score</th>

                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="let score of scores">
                        <td>{{ score.score.competitor.name }}</td>
                        <td>{{ score.rank }}</td>
                        <td>{{ score.score | scoreToString:"-" }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    `
})
export class EventScoreBoard implements OnChanges {
    @Input() eventId: number;
    @Input() divisionId: number;
    @Input() competitionId: number;

    scores: RankedEventScore[];

    constructor(private rankingService: RankingService) {
    }

    ngOnChanges() {
        this.updateData();
    }

    updateData() {
        this.rankingService.getRankingForEvent(this.competitionId, this.eventId, this.divisionId).subscribe((rankings) => {
            this.scores = rankings;
        });
    }
}
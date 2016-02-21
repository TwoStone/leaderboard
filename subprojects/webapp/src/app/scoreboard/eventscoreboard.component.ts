import {
    Component,
    Input,
    OnChanges
} from 'angular2/core';

import {
    RankingService
} from '../services';

import {
    NullAsPipe
} from '../score/pipes';

import {
    RankedEventScore
} from '../model/model';

@Component({
    selector: 'eventScoreBoard',
    template: `
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Score</th>
                        <th>Rank</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="#score of scores">
                        <td>{{ score.score.competitor.name }}</td>
                        <td>{{ score.score.score | nullAs:"-" }}</td>
                        <td>{{ score.rank }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    `,
    pipes: [NullAsPipe]
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
        this.rankingService.getRankingForEvent(this.competitionId, this.eventId, this.divisionId).subscribe(rankings => {
            this.scores = rankings;
        });
    }
}
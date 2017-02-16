import { RankedCompetitionScore } from '../../../model/model';
import { Observable } from 'rxjs/Observable';
import { Component, Input } from '@angular/core';

import { Event } from '../../../model/event';

@Component({
    selector: 'score-board-line-item',
    templateUrl: 'score-board-line-item.component.html',
    styleUrls: ['score-board-line-item.component.scss']
})
export class ScoreBoardLineItemComponent {

    @Input()
    public events: Observable<Event>;

    @Input()
    public ranking: RankedCompetitionScore;


}

import {
    Component,
    Input
} from '@angular/core';

import {
    RankedEventScore
} from '../model/model';

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
class EventScoreComponent {
    @Input() public score: RankedEventScore;
}

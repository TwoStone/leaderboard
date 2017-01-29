import {
    Component,
    Input
} from '@angular/core';

import { Event } from '../model/model';

@Component({
    selector: 'event-header',
    template: `
        <th>{{event.name}} - Score</th>
        <th>{{event.name}} - Rank</th>
    `
})
class EventHeaderComponent {

    @Input() public event: Event;
}

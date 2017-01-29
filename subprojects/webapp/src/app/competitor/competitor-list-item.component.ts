import { Component, Input } from '@angular/core';

import { Competitor } from '../model/competitor';

@Component({
    selector: '[competitorListItem]',
    template: `
        <td>{{ competitorItem.name }}</td>
        <td>{{ competitorItem.division.name }}</td>
    `,
})
export class CompetitorListItemComponent {
    @Input() public competitorItem: Competitor;
}
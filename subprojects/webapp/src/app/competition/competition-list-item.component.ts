import { Component, Input } from '@angular/core';
import { Competition } from '../model/model';

@Component({
    selector: 'competition-list-item',
    template: `
        <a [routerLink]="['/competition', competition.id]">
        {{ competition.name }}
        </a>
    `
})
export class CompetitionListItemComponent {

    @Input() competition: Competition;
}
import { Component, Input, OnInit } from '@angular/core';
import { Division, ModelService } from '../model/model';

@Component({
    selector: 'division-item',
    template: `
        {{ divisionItem.name }}
    `
})
export class DivisionListItemComponent {
    @Input() divisionItem: Division;
}

@Component({
    selector: 'division-list',
    templateUrl: './division-list.component.html',
})
export class DivisionListComponent implements OnInit {

    divisions: Division[];

    constructor(private model: ModelService) {
    }

    ngOnInit() {
        this.model.onCompetitionUpdate.subscribe((comp) => {
            this.divisions = comp.divisions
        });
    }

}
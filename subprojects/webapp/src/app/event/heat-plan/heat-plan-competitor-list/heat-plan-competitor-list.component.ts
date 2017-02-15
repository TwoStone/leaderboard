import { Competitor } from '../../../model/model';
import { Component, Input, OnInit } from '@angular/core';

import { Division } from '../../../model/division';

@Component({
    selector: 'heat-plan-competitor-list',
    templateUrl: './heat-plan-competitor-list.component.html',
    styleUrls: ['./heat-plan-competitor-list.component.css']
})
export class HeatPlanCompetitorListComponent implements OnInit {
    
    @Input()
    public competitors: Competitor[];

    @Input()
    public divisions: Division[];

    public comps: Map<number, Competitor[]>;


    public ngOnInit() {
        let result = new Map();
        this.divisions.forEach((d) => {
            result[d.id] = this.getCompetitors(d);
        });
        this.comps = result;
    }

    public getCompetitors(division: Division): Competitor[] {
        return this.competitors.filter((competitor) => {
            return competitor.division.id === division.id
        });
    }
}

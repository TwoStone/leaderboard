import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Competition, Competitor } from '../../model/model';

import { HeatPlan } from './heat-plan';
import { Heat } from './heat';

@Component({
    selector: 'heat-plan',
    templateUrl: './heat-plan.component.html',
    styleUrls: ['./heat-plan.component.css']
})
export class HeatPlanComponent implements OnInit {

    public plan: HeatPlan;
    public competition: Competition;

    constructor(
        private route: ActivatedRoute
    ) {
    }

    public ngOnInit() {
        this.plan = this.route.snapshot.data['heatPlan'];
        this.competition = this.route.snapshot.parent.data['competition'];
    }

    public addHeat() {
        this.plan.heats.push({
            competitors: []
        });
    }

    public competitorAdded(heat: Heat, competitor: Competitor) {
        let competitors = this.plan.heats.map((h) => {
            return h.competitors
        }).reduce((result, current) => {
            return result.concat(...current);
        }, [] as Competitor[]);
        this.plan.remainingCompetitors = this.competition.competitors.filter((c) => {
            return competitors.indexOf(c) < 0
        });
    }
}

import { HeatPlan } from '../heat-plan';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Division } from '../../../model/division';
import { Competition, Competitor } from '../../../model/model';

@Component({
    selector: 'heat-plan-competitor-list',
    templateUrl: './heat-plan-competitor-list.component.html',
    styleUrls: ['./heat-plan-competitor-list.component.css']
})
export class HeatPlanCompetitorListComponent {

    @Input()
    public plan: HeatPlan;

    @Input()
    public competition: Competition;

    public get divisions(): Division[] {
        return this.competition.divisions;
    }

    public get competitors(): Competitor[] {
        let assignedIds = this.plan.heats.map((h) => h.competitors)
            .reduce((p, c) => {
            p.push(...c);
            return p;
        }, []).map((c) => c.id);

        return this.competition.competitors.filter((competitor) => {
            return assignedIds.indexOf(competitor.id) < 0;
        });
    }
}

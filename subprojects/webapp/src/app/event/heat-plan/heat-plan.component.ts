import { HeatDisplayComponent } from './heat-display/heat-display.component';
import { Observable } from 'rxjs/Rx';
import { Component, ComponentRef, EventEmitter, OnInit, ViewChildren, ViewContainerRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Competition, Competitor } from '../../model/model';

import { Heat } from './heat';
import { HeatPlan } from './heat-plan';

@Component({
    selector: 'heat-plan',
    templateUrl: './heat-plan.component.html',
    styleUrls: ['./heat-plan.component.css']
})
export class HeatPlanComponent {

    public plan: HeatPlan;
    public competition: Competition;

    public onAssignmentRequested: EventEmitter<Competitor> = new EventEmitter();
    public onAssignmentFinished: EventEmitter<void> = new EventEmitter<void>();

    public remainingCompetitors: Competitor[] = [];

    constructor(
        private route: ActivatedRoute
    ) {
        this.route.data.pluck('heatPlan').subscribe((plan: HeatPlan) => {
            this.plan = plan;
        });

        this.route.parent.data.pluck('competition').subscribe((competition: Competition) => {
            this.competition = competition;
            this.remainingCompetitors = this.getRemainingCompetitors();
        });
    }

    public addHeat() {
        this.onAssignmentFinished.emit();
        this.plan.heats.push({
            competitors: []
        });
    }

    public getRemainingCompetitors(): Competitor[] {
        let assigned = this.plan.heats.map((h) => h.competitors).reduce((prev: Competitor[], current: Competitor[]) => {
            prev.push(...current);
            return prev;
        }, []);

        return this.competition.competitors.filter((c) => {
            return assigned.indexOf(c) < 0;
        });
    }

    public competitorAdded(heat: Heat, competitor: Competitor) {
        this.onAssignmentFinished.emit();
        this.remainingCompetitors = this.getRemainingCompetitors();
    }

    public requestHeatAssignment(competitor: Competitor) {
        this.onAssignmentRequested.emit(competitor);
    }

    public removeHeat(heat: Heat) {
        this.plan.heats.splice(this.plan.heats.indexOf(heat));
        this.remainingCompetitors = this.getRemainingCompetitors();
    }
}

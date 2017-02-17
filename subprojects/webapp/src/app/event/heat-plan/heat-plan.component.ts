import { Subject } from 'rxjs/Subject';
import { DragulaService } from 'ng2-dragula/components/dragula.provider';
import { HeatPlanService } from './heat-plan.service';
import { HeatDisplayComponent } from './heat-display/heat-display.component';
import { Observable } from 'rxjs/Rx';
import {
    Component,
    ComponentRef,
    EventEmitter,
    OnDestroy,
    OnInit,
    ViewChildren,
    ViewContainerRef
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Competition, Competitor } from '../../model/model';

import { Heat } from './heat';
import { HeatPlan } from './heat-plan';

@Component({
    selector: 'heat-plan',
    templateUrl: './heat-plan.component.html',
    styleUrls: ['./heat-plan.component.css']
})
export class HeatPlanComponent implements OnInit, OnDestroy {

    public plan: HeatPlan;
    public competition: Competition;

    constructor(
        private dragService: DragulaService,
        private heatService: HeatPlanService,
        private route: ActivatedRoute
    ) {
        this.route.data.pluck('heatPlan').subscribe((plan: HeatPlan) => {
            this.plan = plan;
        });

        this.route.parent.data.pluck('competition').subscribe((competition: Competition) => {
            this.competition = competition;
        });
    }

    public ngOnInit() {
        this.dragService.setOptions('heats-bag', {
            invalid: ((el: HTMLElement) => {
                return el.classList.contains('no-drag');
            })
        });
    }

    public ngOnDestroy() {
        this.dragService.destroy('heats-bag');
    }

    public addHeat() {
        this.plan.heats.push({
            competitors: []
        });
    }

    public saveHeats() {
        this.heatService.savePlan(this.competition.id, this.plan).subscribe((newPlan) => {
            this.plan = newPlan;
        });
    }

    public moveHeatUp(heat: Heat) {
        let index = this.plan.heats.indexOf(heat);
        if (index > 0) {
            let removed = this.plan.heats.splice(index, 1);
            this.plan.heats.splice(index - 1, 0, ...removed);
        }
    }

    public moveHeatDown(heat: Heat) {
        let index = this.plan.heats.indexOf(heat);
        if (index < this.plan.heats.length - 1) {
            let removed = this.plan.heats.splice(index, 1);
            this.plan.heats.splice(index + 1, 0, ...removed);
        }
    }

    public removeHeat(heat: Heat) {
        this.plan.heats.splice(this.plan.heats.indexOf(heat), 1);
    }

    public fillByRanking() {
        this.heatService.fillByRanking(this.competition.id, this.plan).subscribe((p) => {
            this.plan = p;
        });
    }

    public copyFromPrevious() {
        this.heatService.copyFromPreviousEvent(this.competition.id, this.plan).subscribe((p) =>{
            this.plan = p;
        });
    }

    public merge() {
        this.heatService.merge(this.competition.id, this.plan).subscribe((p) => {
            this.plan = p;
        });
    }
}

import { Component } from '@angular/core';

import { HeatPlan } from './heat-plan';

@Component({
    selector: 'heat-plan',
    templateUrl: 'heat-plan.component.html',
    styleUrls: ['heat-plan.component.css']
})
export class HeatPlanComponent {

    public plan: HeatPlan;
}

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from '@angular/router';

import { Observable } from 'rxjs';

import { HeatPlan } from './heat-plan';
import { HeatPlanService } from './heat-plan.service';

@Injectable()
export class HeatPlanResolver implements Resolve<HeatPlan> {

    constructor(private heatPlanService: HeatPlanService) {}

    public resolve(route: ActivatedRouteSnapshot): Observable<HeatPlan> {
        let competitionId = route.parent.params['id'];
        let eventId = route.params['eventId'];
        return this.heatPlanService.getPlan(competitionId, eventId);
    }
}

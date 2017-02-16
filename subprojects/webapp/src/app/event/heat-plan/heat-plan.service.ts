import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HeatPlan } from './heat-plan';

@Injectable()
export class HeatPlanService {

    constructor() {}

    public getPlan(eventId: number): Observable<HeatPlan> {
        return Observable.of({
            event: {
                id: eventId,
                name: 'Some event'
            },
            heats: [],
            heatSize: 5
        } as HeatPlan)
    }

    public savePlan(plan: HeatPlan): Observable<HeatPlan> {
        return undefined;
    }
}
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { Event } from '../../model/event';
import { HeatPlan } from './heat-plan';

@Injectable()
export class HeatPlanService {

    constructor() {}

    public getPlan(eventId: number): Observable<HeatPlan> {
        return undefined;
    }

    public savePlan(plan: HeatPlan): Observable<HeatPlan> {
        return undefined;
    }
}
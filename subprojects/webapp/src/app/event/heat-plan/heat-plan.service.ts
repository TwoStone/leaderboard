import { Competitor } from '../../model/model';
import { Injectable } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import { Event } from '../../model/event';
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
            remainingCompetitors: [
                {
                    name: "Demo Team One",
                    id: 5,
                    division: {
                        id: 2
                    }
                },
                {
                    name: "Demo Team Two",
                    id: 6,
                    division: {
                        id: 2
                    }
                },
            ],
            heatSize: 5
        } as HeatPlan)
    }

    public savePlan(plan: HeatPlan): Observable<HeatPlan> {
        return undefined;
    }
}
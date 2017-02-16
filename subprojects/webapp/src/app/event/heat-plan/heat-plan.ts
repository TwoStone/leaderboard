import { Event } from '../../model/event';
import { Heat } from './heat';

export interface HeatPlan {
    event: Event;
    heats: Heat[];
    heatSize: number;
    mixDivisions: boolean;
}

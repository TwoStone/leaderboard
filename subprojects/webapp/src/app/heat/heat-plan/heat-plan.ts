import { Event } from '../../model/event';
import { Competitor } from '../../model/competitor';
import { Heat } from '../heat';

export interface HeatPlan {
    event: Event;
    heats: Heat[];
    remainingCompetitors: Competitor[];
    mixDivisions: boolean;
}
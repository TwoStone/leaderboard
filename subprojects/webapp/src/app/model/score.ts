import {Competitor} from './competitor';
import {Event} from './event';

export interface Score {
    id?: number;
    event: Event;
    competitor: Competitor;
    value: number;
}
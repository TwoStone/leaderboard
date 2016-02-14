import {Division} from './division';
import {Competitor} from './competitor';
import {Event} from './event';

export interface Competition {
    name: string;
    id: number;
    divisions: Division[];
    competitors: Competitor[];
    events: Event[];
}
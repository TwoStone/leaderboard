import { Competitor } from './competitor';
import { Entity } from './entity';
import { Event } from './event';
import { PartialScore } from './partial-score';

export interface Score extends Entity {
    event: Event;
    competitor: Competitor;
    scaled: boolean;
    parts: Map<string, PartialScore>;
    notSet: boolean;
}
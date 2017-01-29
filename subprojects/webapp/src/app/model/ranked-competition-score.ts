import { Competitor } from './competitor';
import { RankedEventScore } from './ranked-event-score';

export class RankedCompetitionScore {
    rank: number;
    competitor: Competitor;
    eventScores: RankedEventScore[];
}
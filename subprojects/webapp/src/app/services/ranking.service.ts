import {
    Injectable
} from 'angular2/core';

import {
    Http
} from 'angular2/http';

import {
    RankedEventScore
} from '../model/model';

@Injectable()
export class RankingService {

    constructor(private http: Http) {
    }

    getRankingForEvent(competitionId: number, eventId: number, divisionId: number) {
        let url = `/api/ranking/${competitionId}/${eventId}/${divisionId}`;
        return this.http.get(url).map(res => res.json());
    }

    getRankingForCompetition(competitionId: number, divisionId: number) {
        let url = `/api/ranking/${competitionId}/all/${divisionId}`;
        return this.http.get(url).map(res => res.json());
    }
}
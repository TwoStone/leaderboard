import {
    Injectable
} from '@angular/core';

import {
    Http
} from '@angular/http';

@Injectable()
export class RankingService {

    constructor(private http: Http) {
    }

    public getRankingForEvent(competitionId: number, eventId: number, divisionId: number) {
        let url = `/api/ranking/${competitionId}/${eventId}/${divisionId}`;
        return this.http.get(url).map((res) => res.json());
    }

    public getRankingForCompetition(competitionId: number, divisionId: number) {
        let url = `/api/ranking/${competitionId}/all/${divisionId}`;
        return this.http.get(url).map((res) => res.json());
    }
}

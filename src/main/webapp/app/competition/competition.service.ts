import {Injectable} from 'angular2/core';
import {Http, Headers} from 'angular2/http';
import {Competition, Division} from '../model/model';
import {Observable} from 'rxjs/observable';

export interface NewCompetition {
    name: string;
}

@Injectable()
export class CompetitionService {

    private baseUrl = '/api/competitions';

    constructor(private http: Http) {
        this.http = http;
    }

    create(competition: NewCompetition) {
        let body = `name=${competition.name}`;

        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        return this.http.post('api/competitions/create', body, {
            headers: headers
        });
    }

    getAll(): Observable<Array<Competition>> {
        return this.http.get('api/competitions')
            .map(res => res.json());
    }

    get(id: string): Observable<Competition> {
        return this.http.get('api/competitions/' + id)
            .map(res => res.json());
    }

    getDivisions(competitionId: string): Observable<Array<Division>> {
        return this.http.get(`api/competitions/${competitionId}/divisions`)
            .map(res => res.json());
    }
}
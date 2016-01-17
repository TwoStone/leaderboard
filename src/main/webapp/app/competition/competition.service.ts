import {Injectable} from 'angular2/core';
import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Competition} from './competition';
    
@Injectable()
export class CompetitionService {
    
    constructor(private http: Http) {
        this.http = http;
    }

    getCompetitions() {
        return this.http.get('api/competitions')
            .map(res => res.json()._embedded.competitions)
    }

    get(id: string) {
		return this.http.get('api/competitions/' + id)
			.map(res => res.json());
    }


}
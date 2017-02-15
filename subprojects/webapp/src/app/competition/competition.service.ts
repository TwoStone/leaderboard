import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import {
    Competition,
    Division,
    Competitor,
    Event
} from '../model/model';

import * as Rx from 'rxjs';

export interface NewCompetition {
    name: string;
}

export interface NewDivision {
    name: string;
}

export interface NewCompetitor {
    name: string;
    divisionId: number;
}

@Injectable()
export class CompetitionService {

    private baseUrl = '/api/competitions';

    constructor(private http: Http) {
        this.http = http;
    }

    public create(competition: NewCompetition): Rx.Observable<Competition> {
        let body = `name=${competition.name}`;

        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        return this.http.post('api/competitions/create', body, {
            headers
        }).map((res) => res.json());
    }

    public save(competition: Competition) {
        let url = 'api/competitions';
        let body = JSON.stringify(competition);

        let headers =  new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(url, body, {
            headers
        }).map((res) => res.json());
    }

    public getAll(): Rx.Observable<Competition[]> {
        return this.http.get('api/competitions')
            .map((res) => res.json());
    }

    public get(id: number): Rx.Observable<Competition> {
        return this.http.get(`api/competitions/${id}`)
            .map((res) => res.json());
    }

    public addDivision(comp: Competition, newDivision: NewDivision): Rx.Observable<Division> {
        let body = `name=${newDivision.name}`;

        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        return this.http.post(`api/competitions/${comp.id}/divisions.add`, body, {
            headers
        }).map((res) => res.json());
    }

    public addCompetitor(comp: Competition, newCompetitor: NewCompetitor): Rx.Observable<Competitor> {
        let body = JSON.stringify(newCompetitor);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(`api/competitions/${comp.id}/competitors.add`, body, {
            headers
        }).map((res) => res.json());
    }
}

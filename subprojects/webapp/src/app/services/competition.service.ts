import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import {
    Competition,
    Division,
    Competitor,
    Event,
    EventType
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

export interface NewEvent {
    name: string;
    description: string;
    type: EventType;
    scalable: boolean;
}

@Injectable()
export class CompetitionService {

    private baseUrl = '/api/competitions';

    constructor(private http: Http) {
        this.http = http;
    }

    create(competition: NewCompetition): Rx.Observable<Competition> {
        let body = `name=${competition.name}`;

        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        return this.http.post('api/competitions/create', body, {
            headers: headers
        }).map(res => res.json());
    }

    getAll(): Rx.Observable<Array<Competition>> {
        return this.http.get('api/competitions')
            .map(res => res.json());
    }

    get(id: number): Rx.Observable<Competition> {
        return this.http.get(`api/competitions/${id}`)
            .map(res => res.json());
    }

    addDivision(comp: Competition, newDivision: NewDivision): Rx.Observable<Division> {
        let body = `name=${newDivision.name}`;

        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        return this.http.post(`api/competitions/${comp.id}/divisions.add`, body, {
            headers: headers
        }).map(res => res.json());
    }

    addCompetitor(comp: Competition, newCompetitor: NewCompetitor): Rx.Observable<Competitor> {
        let body = JSON.stringify(newCompetitor);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(`api/competitions/${comp.id}/competitors.add`, body, {
            headers: headers
        }).map(res => res.json());
    }

    addEvent(comp: Competition, newEvent: NewEvent): Rx.Observable<Event> {
        let body = JSON.stringify(newEvent);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(`api/competitions/${comp.id}/events.add`, body, {
            headers: headers
        }).map(res => res.json());
    }
}
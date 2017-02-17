import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { Event } from '../model/event';

@Injectable()
export class EventService {

    constructor(private http: Http) {
    }

    public getEvent(competitionId: number, eventId: number): Observable<Event> {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.get(`api/competitions/${competitionId}/events/${eventId}`, {
            headers
        }).map((res) => res.json());
    }

    public saveEvent(competitionId: number, event: Event): Observable<Event> {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(`api/competitions/${competitionId}/events`, JSON.stringify(event), {
            headers
        }).map((res) => res.json());
    }
}

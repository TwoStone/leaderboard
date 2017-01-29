import { Injectable } from '@angular/core';

import { Http, Headers } from '@angular/http';

import * as Rx from 'rxjs';

import {
    Event,
    Competition,
    Competitor,
    Score
} from '../model/model';

@Injectable()
export class ScoreService {

     constructor(private http: Http) { }

     getScoresForEvent(eventId: number): Rx.Observable<Array<Score>> {
         let url = `api/scores/event/${eventId}`;
         return this.http.get(url).map(res => res.json());
     }

     addScore(score: Score): Rx.Observable<Score> {
         let url = `api/scores/`;
         let body = JSON.stringify(score);

         let headers = new Headers();
         headers.append('Content-Type', 'application/json');

         return this.http.post(url, body, {
             headers: headers
         }).map(res => res.json());
     }
 }
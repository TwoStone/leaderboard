import {Injectable} from 'angular2/core';

import {Http, Headers} from 'angular2/http';

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

     getScoresForEvent(competition: Competition, event: Event): Rx.Observable<Array<Score>> {
         let url = `api/scores/event/${event.id}`;
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
import { Injectable } from '@angular/core';

import { Http, Headers, URLSearchParams } from '@angular/http';

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

     public getScoresForEvent(eventId: number): Rx.Observable<Score[]> {
         let url = `api/scores/event/${eventId}`;
         return this.http.get(url).map((res) => res.json());
     }

     public getScore(competitionId: number, eventId: number, competitorId: number): Rx.Observable<Score> {
         let url = `api/scores`;
         let params: URLSearchParams =  new URLSearchParams();
         params.set('competition', competitionId.toString());
         params.set('event', eventId.toString());
         params.set('competitor', competitorId.toString());

         return this.http.get(url, {
             search: params
         }).map((res) => res.json());
     }

     public addScore(score: Score): Rx.Observable<Score> {
         let url = `api/scores/`;
         let body = JSON.stringify(score);

         let headers = new Headers();
         headers.append('Content-Type', 'application/json');

         return this.http.post(url, body, {
             headers
         }).map((res) => res.json());
     }
 }

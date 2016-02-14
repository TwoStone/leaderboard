import {Injectable} from 'angular2/core';

import {Http} from 'angular2/http';

import * as Rx from 'rxjs';

import {EventType} from '../model/model';



@Injectable()
export class EventTypeService {

    constructor(private http: Http) { }

    getAll(): Rx.Observable<Array<EventType>> {
        return this.http.get('/api/eventtypes')
            .map(res => res.json());
    }
}
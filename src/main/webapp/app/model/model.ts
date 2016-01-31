import {Injectable} from 'angular2/core';
import {Observable} from 'rxjs/Observable';
import {ReplaySubject} from 'rxjs/subject/ReplaySubject';

export interface Competition {
    name: string;
    id: number;
    divisions: Division[];
}


export interface Division {
    name: string;
    id: number;
}


@Injectable()
export class Model {

    private _competition: Competition;

    private _onCompetition: ReplaySubject<Competition>;

    constructor() {
        this._onCompetition = new ReplaySubject(1);
    }

    set competition(competition: Competition) {
        this._competition = competition;
        this._onCompetition.next(competition);
    }

    get competition() {
        return this._competition;
    }

    get onCompetition(): Observable<Competition> {
        return this._onCompetition;
    }
}


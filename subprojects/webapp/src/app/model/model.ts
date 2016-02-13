import {Injectable} from 'angular2/core';

import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';
import {ReplaySubject} from 'rxjs/subject/ReplaySubject';


import {CompetitionService} from '../competition/competition.service';

export interface Competition {
    name: string;
    id: number;
    divisions: Division[];
    competitors: Competitor[];
    events: Event[];
}

export interface Division {
    name: string;
    id: number;
}

export interface Competitor {
    name: string;
    id: number;
    division: Division;
}

export interface Event {
    name: string;
    id: number;
}

export interface Score {
    id?: number;
    event: Event;
    competitor: Competitor;
    value: number;
}

@Injectable()
export class ModelService {

    private _competitionId: number;
    private _competition: Competition;
    private _competitionUpdated: Subject<Competition>;

    constructor(private service: CompetitionService) {
        this._competitionUpdated = new ReplaySubject();
    }

    set competitionId(id: number) {
        this._competitionId = id;
        this.updateModel();
    }

    get competition() {
        return this._competition;
    }

    get onCompetitionUpdate(): Observable<Competition> {
        return this._competitionUpdated;
    }

    updateModel() {
        if (this._competitionId) {
            this.service.get(this._competitionId).subscribe(comp => {
                this._competition = comp;
                this._competitionUpdated.next(comp);
            });
        }
    }
}

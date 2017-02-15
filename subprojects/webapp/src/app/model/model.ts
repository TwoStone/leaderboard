import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { ReplaySubject } from 'rxjs/ReplaySubject';

import { CompetitionService } from '../competition/competition.service';

import { Competition } from './competition';

export * from './competition';
export * from './competitor';
export * from './event';
export * from './score';
export * from './division';
export * from './ranked-event-score';
export * from './ranked-competition-score';

@Injectable()
export class ModelService {

    private _competitionId: number;
    private _competition: Competition;
    private _competitionUpdated: Subject<Competition>;

    constructor(private service: CompetitionService) {
        this._competitionUpdated = new ReplaySubject<Competition>();
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

    public updateModel() {
        if (this._competitionId) {
            this.service.get(this._competitionId).subscribe((comp) => {
                this._competition = comp;
                this._competitionUpdated.next(comp);
            });
        }
    }
}

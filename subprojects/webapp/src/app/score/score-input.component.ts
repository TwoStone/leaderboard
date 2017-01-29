import {
    Component,
    Type,
    Input,
    Output
} from '@angular/core';

import {
    Event,
    EventType,
    Score
} from '../model/model';

import * as moment from 'moment';

export interface ScoreInput {
    score: Score
}

@Component({
    template: `
        <div class="form-group">
            <label for="score">Time</label>
            <input type="time" class="form-control" [(ngModel)]="time"/>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-lg-8">
                    <input type="number" class="form-control" #reps/>
                </div>
                <div class="col-lg-4">
                    <button class="btn btn-default" (click)="addReps($event, reps)">Add missing reps</button>
                </div>
            </div>
        </div>
    `
})
export class ForTimeInputComponent implements ScoreInput {

    @Input() score: Score;

    get time() {
        if (this.score) {
            return moment.duration(this.score.score, 'seconds').format('mm:ss');
        } else {
            return '';
        }
    }

    set time(time: string) {
        if (time.length === 0) {
            this.score.score = null;
        }
        let parsed = /(\d\d):(\d\d)/.exec(time);
        if (parsed) {
            this.score.score = moment
                .duration(+parsed[1], 'minutes')
                .add(+parsed[2], 'seconds')
                .asSeconds();
        }
    }

    addReps(event: MouseEvent, input: any) {
        event.preventDefault();
        let reps = input.valueAsNumber;
        if (reps > 0) {
            this.score.score += reps;
            input.value = '';
        }
    }
}

@Component({
    template: `
        <div class="form-group">
            <label for="score">Points</label>
            <input type="number" class="form-control" [(ngModel)]="score.score"/>
        </div>
    `
})
export class ForPointsInputComponent implements ScoreInput {
    @Input() score: Score;
}

export class ScoreInputFactory {
    static getComponentForType(event: Event): Type<ScoreInput> {
        switch (event.type) {
            case EventType.FOR_POINTS:
                return ForPointsInputComponent;
            case EventType.FOR_TIME:
                return ForTimeInputComponent;
        }
    }
}
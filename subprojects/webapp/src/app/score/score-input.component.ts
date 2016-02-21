import {
    Component,
    Type,
    Input,
    Output
} from 'angular2/core';

import {
    Event,
    EventType,
    Score
} from '../model/model';

@Component({
    template: `
        <div class="form-group">
            <label for="score">Time</label>
            <input type="time" class="form-control" [ngModel]="time" (ngModelChange)="setScore($event)"/>
        </div>
    `
})
class ForTimeInput {

    time: string;

    private _score: Score;

    @Input()
    set score(score: Score) {
        let m = <any>moment.duration(score.score, 'seconds')
        this.time = m.format('mm:ss');
        this._score = score;
    }

    setScore(value) {
        let parsed = /(\d\d):(\d\d)/.exec(value);
        let minutes = parsed[1];
        let seconds = parsed[2];
        let duration = moment.duration(+minutes, 'minutes')
            .add(+seconds, 'seconds');
        this._score.score = duration.asSeconds();
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
class ForPointsInput {
    @Input() score: Score;
}

export class ScoreInputFactory {
    static getComponentForType(event: Event): Type {
        switch (event.type) {
            case EventType.FOR_POINTS:
                return ForPointsInput;
            case EventType.FOR_TIME:
                return ForTimeInput;
        }
    }
}
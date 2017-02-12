import { Component, EventEmitter } from '@angular/core';

import * as moment from 'moment';

import { ScoreInput } from './score-input';

@Component({
    templateUrl: './score-time-input.component.html'
})
export class ScoreTimeInputComponent implements ScoreInput {

    public valueChanged: EventEmitter<number> = new EventEmitter<number>();

    public name: string;

    private _seconds: number;

    private _minutes: number;

    public get value(): number {
        return moment.duration(this.minutes, 'minutes').add(this.seconds, 'seconds').asSeconds();
    }

    public set value(value: number) {
        if (value) {
            let duration = moment.duration(value, 'seconds');
            this._minutes = duration.minutes();
            this._seconds = duration.seconds();
        }
    }

    public get seconds() {
        return this._seconds;
    }

    public set seconds(seconds: number) {
        if (seconds >= 60) {
            let duration = moment.duration(this.minutes, 'minutes').add(seconds, 'seconds');
            this._seconds = duration.seconds();
            this._minutes = duration.minutes();
        } else {
            this._seconds = seconds;
            this.valueChanged.emit(this.value);
        }
    }

    public get minutes() {
        return this._minutes;
    }

    public set minutes(minutes: number) {
        this._minutes = minutes;
        this.valueChanged.emit(this.value);
    }

    public clear() {
        this._minutes = null;
        this._seconds = null;
        this.valueChanged.emit(null);
    }
}
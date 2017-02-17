import { Component, EventEmitter } from '@angular/core';

import { ScoreInput } from './score-input';

@Component({
    templateUrl: './score-point-input.component.html'
})
export class ScorePointInputComponent implements ScoreInput {

    public valueChanged: EventEmitter<number> = new EventEmitter<number>();

    public name: string;

    private _value: number;

    get value() {
        return this._value;
    }

    set value(v: number) {
        this._value = v;
        this.valueChanged.emit(v);
    }

    public clear() {
        this.value = null;
    }
}
import { Component, EventEmitter } from '@angular/core';

import { ScoreInput } from './score-input';

@Component({
    template: `
        <div class="alert alert-danger">Type for {{name}} unknown</div>
    `
})
export class ScoreUnknownInputComponent implements ScoreInput {
    public value: number;
    public name: string;
    public valueChanged: EventEmitter<number> =  new EventEmitter<number>();
}

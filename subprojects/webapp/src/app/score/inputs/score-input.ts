import { EventEmitter } from '@angular/core';

export interface ScoreInput {
    value: number;
    valueChanged: EventEmitter<number>;
    name: string;
}

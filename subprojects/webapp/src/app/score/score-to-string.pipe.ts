import { Pipe, PipeTransform } from '@angular/core';

import * as moment from 'moment';

import { Score } from '../model/score';
import { ScoreIngredientType } from '../model/score-ingredient-type';
import { PartialScore } from '../model/partial-score';

@Pipe({
    name: 'scoreToString'
})
export class ScoreToStringPipe implements PipeTransform {
    public transform(value: Score, args: string): string {
        if (!value || Object.keys(value.parts).length === 0) {
            return args || '';
        }

        let s = value.event.recipe.parts.map((type) => {
            let score: PartialScore = value.parts[type.name];
            return score ? {
                name: score.name,
                value: this.formatScore(score.value, type.type)
            } : undefined;
        }).map((score) => {
            if (score) {
                return `${score.value}`;
            }
        }).filter((v) => {
            return v !== undefined;
        }).join(' > ');

        if (value.scaled) {
            return '(s) ' + s;
        }
        return s;
    }

    private formatScore(value: number, type: ScoreIngredientType) {
        switch (type) {
            case ScoreIngredientType.TIME:
                return moment.duration(value, 'seconds').format('mm:ss');
            default:
                return value;
        }
    }
}
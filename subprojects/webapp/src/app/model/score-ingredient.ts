import { Entity } from './entity';
import { ScoreIngredientType } from './score-ingredient-type';

export interface ScoreIngredient extends Entity {
    name: string;
    type: ScoreIngredientType;
}

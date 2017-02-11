import { Entity } from './entity';
import { ScoreIngredient } from './score-ingredient';

export interface ScoreRecipe extends Entity {
    parts: ScoreIngredient[];
}

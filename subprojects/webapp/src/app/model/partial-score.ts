import { Entity } from './entity';

export interface PartialScore extends Entity {
    name: string;
    value: number;
}
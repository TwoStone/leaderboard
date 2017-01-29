export interface Event {
    name: string;
    description: string;
    type: EventType;
    id: number;
    scalable: boolean;
}

export class EventType {
    static FOR_TIME: string = 'FOR_TIME';
    static FOR_POINTS: string = 'FOR_POINTS';
}
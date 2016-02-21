export interface Event {
    name: string;
    description: string;
    type: EventType;
    id: number;
}

export enum EventType {
    FOR_TIME,
    FOR_POINTS
}
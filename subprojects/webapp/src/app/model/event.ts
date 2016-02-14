export interface Event {
    name: string;
    description: string;
    type: EventType;
    id: number;
}

export interface EventType {
    id: number;
    name: string;
    ordering: string;
}
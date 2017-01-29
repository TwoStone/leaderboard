import {
    EventEmitter,
    Injectable
 } from '@angular/core';

 import * as Rx from 'rxjs';

@Injectable()
export class EventBus {

    private events: any;

    constructor() {
        this.events = {};
    }

    public on(event: string): Rx.Observable<any> {
        if (!this.events[event]) {
            this.events[event] = new EventEmitter();
        }
        return this.events[event];
    }

    public fire(event: string, arg?: any) {
        if (!this.events[event]) {
            this.events[event] = new EventEmitter();
        }
        this.events[event].emit(arg);
    }
}

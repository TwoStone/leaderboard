import { Competitor, Division } from '../../../model/model';
import { Heat } from '../heat';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'heat-display',
    templateUrl: 'heat-display.component.html',
    styleUrls: ['heat-display.component.css']
})
export class HeatDisplayComponent {
    
    @Input()
    public heat: Heat;

    @Input()
    public num: number;

    @Input()
    public size: number;

    @Input()
    public divisions: Division[]

    @Output()
    public onCompetitorAdded: EventEmitter<Competitor> = new EventEmitter();

    public get emptyLanes() {
        let empty = this.size - this.heat.competitors.length;
        let lanes = [];
        for (let i = 0; i < empty; i++) {
            lanes.push({});
        }
        return lanes;
    }

    public get divisionZones() {
        return this.divisions.map((d) => {
            return d.id;
        });
    }

    public addTo(event: any) {
        this.heat.competitors.push(event.dragData);
        this.onCompetitorAdded.emit(event.dragData);
    }

    public allowDrop() {
        return (() => this.heat.competitors.length < this.size);
    }
}

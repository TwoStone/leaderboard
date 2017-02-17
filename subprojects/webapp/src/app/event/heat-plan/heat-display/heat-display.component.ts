import { DragulaService } from 'ng2-dragula/components/dragula.provider';
import { Observable } from 'rxjs/Rx';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Competitor, Division } from '../../../model/model';
import { Heat } from '../heat';

@Component({
    selector: 'heat-display',
    templateUrl: 'heat-display.component.html',
    styleUrls: ['heat-display.component.scss']
})
export class HeatDisplayComponent {

    @Input()
    public heat: Heat;
    @Input()
    public num: number;
    @Input()
    public size: number;
    @Input()
    public divisions: Division[];
    @Output()
    public onRemove: EventEmitter<Heat> = new EventEmitter();
    @Output()
    public onMoveUp: EventEmitter<Heat> = new EventEmitter();
    @Output()
    public onMoveDown: EventEmitter<Heat> = new EventEmitter();

    public get emptyLanes() {
        let empty = this.size - this.heat.competitors.filter((c) => c).length;
        return Math.max(empty, 0);
    }

    public get emptyLanesArray() {
        return Array(this.emptyLanes).fill({});
    }

    public get displayState() {
        let currentSize = this.heat.competitors.length;
        let full = currentSize === this.size;
        let toFull = currentSize > this.size;

        return {
            'panel-primary': !full,
            'panel-success': full,
            'panel-danger': toFull
        }
    }

    public get heatDivisions() {
        return this.heat.competitors.map((competitor) => {
                return competitor.division;
            }).reduce((prev: Division[], current: Division) => {
                if (prev.findIndex((d) => d.id === current.id) < 0) {
                    prev.push(current);
                }
                return prev;
            }, []).map((division) => division.name).sort();
    }

    public moveUp() {
        this.onMoveUp.emit(this.heat);
    }

    public moveDown() {
        this.onMoveDown.emit(this.heat);
    }

    public removeHeat() {
        this.onRemove.emit(this.heat);
    }

    public remove(competitor: Competitor) {
        this.heat.competitors.splice(this.heat.competitors.findIndex((o) => {
            return o.id === competitor.id
        }), 1);
    }
}

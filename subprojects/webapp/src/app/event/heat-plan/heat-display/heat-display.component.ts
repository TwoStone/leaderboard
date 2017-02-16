import { Observable } from 'rxjs/Rx';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Competitor, Division } from '../../../model/model';
import { Heat } from '../heat';

@Component({
    selector: 'heat-display',
    templateUrl: 'heat-display.component.html',
    styleUrls: ['heat-display.component.scss']
})
export class HeatDisplayComponent implements OnInit {

    @Input()
    public heat: Heat;

    @Input()
    public num: number;

    @Input()
    public size: number;

    @Input()
    public divisions: Division[];

    @Input()
    public assignmentRequest: Observable<Competitor>;

    @Input()
    public assignmentFinish: Observable<void>;

    @Output()
    public onCompetitorAdded: EventEmitter<Competitor> = new EventEmitter();

    @Output()
    public onRemove: EventEmitter<Heat> = new EventEmitter();

    private canAssign: boolean;
    private assignmentPending: Competitor;

    public get emptyLanes() {
        let empty = this.size - this.heat.competitors.length;
        let lanes = [];
        for (let i = 0; i < empty; i++) {
            lanes.push({});
        }
        return lanes;
    }

    public get displayState() {
        return {
            'panel-default': !this.assignmentPending,
            'panel-success': this.assignmentPending && this.canAssign,
            'panel-warning': this.assignmentPending && !this.canAssign,
            'assignable': this.assignmentPending && this.canAssign,
            'not-assignable': this.assignmentPending && !this.canAssign
        }
    }

    public ngOnInit() {
        this.assignmentRequest.subscribe((competitor) => {
            this.assignmentPending = competitor;
            this.tryAssign(competitor);
        });

        this.assignmentFinish.subscribe(() => {
            this.assignmentPending = null;
        });
    }

    public tryAssign(competitor: Competitor) {
        this.canAssign = this.heat.competitors.length < this.size;
    }

    public assignCompetitor() {
        if (this.assignmentPending && this.canAssign) {
            this.heat.competitors.push(this.assignmentPending);
            this.onCompetitorAdded.emit(this.assignmentPending);
        }
    }

    public remove(competitor: Competitor) {
        let removed = this.heat.competitors.splice(this.heat.competitors.indexOf(competitor), 1);
        this.onCompetitorAdded.emit(removed[0]);
    }

    public removeHeat() {
        this.onRemove.emit(this.heat);
    }
}

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Division } from '../../../model/division';
import { Competitor } from '../../../model/model';

@Component({
    selector: 'heat-plan-competitor-list',
    templateUrl: './heat-plan-competitor-list.component.html',
    styleUrls: ['./heat-plan-competitor-list.component.css']
})
export class HeatPlanCompetitorListComponent {

    @Input()
    public competitors: Competitor[];

    @Input()
    public divisions: Division[];

    @Output()
    public onAssignRequested: EventEmitter<Competitor> = new EventEmitter();

    public assignToHeat(competitor: Competitor) {
        this.onAssignRequested.emit(competitor);
    }
}

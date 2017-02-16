import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { DndModule } from 'ng2-dnd';
import { FilterByDivisionPipe } from './filter-by-division.pipe';

import { HeatPlanCompetitorListComponent } from './heat-plan-competitor-list.component';

@NgModule({
    imports: [
        CommonModule,
        DndModule
    ],
    declarations: [
        FilterByDivisionPipe,
        HeatPlanCompetitorListComponent,
    ],
    exports: [
        FilterByDivisionPipe,
        HeatPlanCompetitorListComponent,
    ]
})
export class HeatPlanCompetitorListModule {

}

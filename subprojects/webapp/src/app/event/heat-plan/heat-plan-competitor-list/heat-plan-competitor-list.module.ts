import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { DndModule } from 'ng2-dnd';

import { HeatPlanCompetitorListComponent } from './heat-plan-competitor-list.component';

@NgModule({
    imports: [
        CommonModule,
        DndModule
    ],
    declarations: [
        HeatPlanCompetitorListComponent,
    ],
    exports: [
        HeatPlanCompetitorListComponent,
    ]
})
export class HeatPlanCompetitorListModule {

}

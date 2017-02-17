import { DragulaModule } from 'ng2-dragula/components/dragular.module';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FilterByDivisionPipe } from './filter-by-division.pipe';
import { HeatPlanCompetitorListComponent } from './heat-plan-competitor-list.component';

@NgModule({
    imports: [
        CommonModule,
        DragulaModule
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

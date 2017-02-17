import { DragulaModule } from 'ng2-dragula/components/dragular.module';
import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeatDisplayModule } from './heat-display/heat-display.module';
import { HeatPlanCompetitorListModule } from './heat-plan-competitor-list/heat-plan-competitor-list.module';

// This Module's Components
import { HeatPlanComponent } from './heat-plan.component';
import { HeatPlanResolver } from './heat-plan.resolver';
import { HeatPlanService } from './heat-plan.service';

@NgModule({
    imports: [
        DragulaModule,
        CommonModule,
        FormsModule,
        HeatDisplayModule,
        HeatPlanCompetitorListModule,
    ],
    declarations: [
        HeatPlanComponent,
    ],
    exports: [
        HeatPlanComponent,
    ],
    providers: [
        HeatPlanService,
        HeatPlanResolver
    ]
})
export class HeatPlanModule {

}

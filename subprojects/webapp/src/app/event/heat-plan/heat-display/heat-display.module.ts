import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';

import { DndModule } from 'ng2-dnd';

// This Module's Components
import { HeatDisplayComponent } from './heat-display.component';

@NgModule({
    imports: [
        CommonModule,
        DndModule
    ],
    declarations: [
        HeatDisplayComponent,
    ],
    exports: [
        HeatDisplayComponent,
    ]
})
export class HeatDisplayModule {

}

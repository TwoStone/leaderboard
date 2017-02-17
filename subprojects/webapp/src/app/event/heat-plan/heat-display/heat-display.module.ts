import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { DragulaModule } from 'ng2-dragula/components/dragular.module';
import { HeatDisplayComponent } from './heat-display.component';

@NgModule({
    imports: [
        DragulaModule,
        CommonModule
    ],
    declarations: [
        HeatDisplayComponent
    ],
    exports: [
        HeatDisplayComponent
    ]
})
export class HeatDisplayModule {

}

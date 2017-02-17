import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HeatDisplayComponent } from './heat-display.component';

@NgModule({
    imports: [
        CommonModule
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

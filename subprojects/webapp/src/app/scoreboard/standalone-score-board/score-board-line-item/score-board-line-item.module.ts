import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';
import { PipesModule } from '../../../pipes/pipes.module';
import { ScoreModule } from '../../../score/score.module';

// This Module's Components
import { ScoreBoardLineItemComponent } from './score-board-line-item.component';

@NgModule({
    imports: [
        CommonModule,
        PipesModule,
        ScoreModule
    ],
    declarations: [
        ScoreBoardLineItemComponent,
    ],
    exports: [
        ScoreBoardLineItemComponent,
    ]
})
export class ScoreBoardLineItemModule {

}

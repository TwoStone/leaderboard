import { ScoreModule } from '../../../score/score.module';
import { PipesModule } from '../../../pipes/pipes.module';
import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';

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

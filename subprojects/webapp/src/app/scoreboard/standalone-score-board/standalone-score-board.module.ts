import { CompetitionModule } from '../../competition/competition.module';
import { ServiceModule } from '../../services/service.module';
import { CommonModule } from '@angular/common';
import { ScoreModule } from '../../score/score.module';
import { NgModule } from '@angular/core';
import { StandaloneScoreBoardComponent } from './standalone-score-board.component';
import { ScoreBoardLineItemModule } from './score-board-line-item/score-board-line-item.module';

@NgModule({
    imports: [
        CommonModule,
        CompetitionModule,
        ScoreModule,
        ScoreBoardLineItemModule,
        ServiceModule
    ],
    declarations: [
        StandaloneScoreBoardComponent,
    ],
    exports: [
        StandaloneScoreBoardComponent,
    ]
})
export class StandaloneScoreBoardModule {

}

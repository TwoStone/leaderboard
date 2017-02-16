import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CompetitionModule } from '../../competition/competition.module';
import { ScoreModule } from '../../score/score.module';
import { ServiceModule } from '../../services/service.module';
import { ScoreBoardLineItemModule } from './score-board-line-item/score-board-line-item.module';
import { StandaloneScoreBoardComponent } from './standalone-score-board.component';

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

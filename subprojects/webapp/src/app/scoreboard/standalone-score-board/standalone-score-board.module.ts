import { CompetitionModule } from '../../competition/competition.module';
import { ServiceModule } from '../../services/service.module';
import { CommonModule } from '@angular/common';
import { ScoreModule } from '../../score/score.module';
import { NgModule } from '@angular/core';
import { StandaloneScoreBoardComponent } from './standalone-score-board.component';

@NgModule({
    imports: [
        CommonModule,
        CompetitionModule,
        ScoreModule,
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

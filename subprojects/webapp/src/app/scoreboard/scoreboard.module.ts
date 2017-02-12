import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { ScoreboardComponent }   from './scoreboard.component';
import { CompetitionScoreboard } from './competitionscoreboard.component';
import { EventScoreBoard } from './eventscoreboard.component';
import { StandaloneScoreboardComponent } from './scoreboard-standalone.component';

import { ScoreModule } from '../score/score.module';

import { RankingService } from '../services/ranking.service';

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ScoreModule
    ],
    declarations:
    [
        ScoreboardComponent,
        CompetitionScoreboard,
        EventScoreBoard,
        StandaloneScoreboardComponent
    ],
    providers: [
        RankingService
    ],
})
export class ScoreboardModule { }

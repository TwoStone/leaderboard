import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { CompetitionScoreboard } from './competitionscoreboard.component';
import { EventScoreBoard } from './eventscoreboard.component';
import { ScoreboardComponent }   from './scoreboard.component';

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
        EventScoreBoard
    ],
    providers: [
        RankingService
    ],
})
export class ScoreboardModule { }

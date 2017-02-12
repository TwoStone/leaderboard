import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { ScoreComponent } from './score.component';
import { EditScoreComponent } from './edit-score.component';

import { PipesModule } from '../pipes/pipes.module';

import { ScoreInputModule } from './inputs/score-input.module';

import { ScoreToStringPipe } from './score-to-string.pipe';

@NgModule({
    imports: [
        ScoreInputModule,
        CommonModule,
        FormsModule,
        PipesModule
    ],
    declarations: [
        ScoreComponent,
        EditScoreComponent,
        ScoreToStringPipe
    ],
    exports: [
        ScoreToStringPipe
    ]
})
export class ScoreModule {
}

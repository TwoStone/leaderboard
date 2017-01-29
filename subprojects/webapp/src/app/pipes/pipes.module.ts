import { NgModule } from '@angular/core';

import { NullAsPipe, ScoreDisplayPipe, ScoreFilterPipe, ScoringQueryFilterPipe } from './pipes';

@NgModule({
    declarations: [
        NullAsPipe,
        ScoreDisplayPipe,
        ScoreFilterPipe,
        ScoringQueryFilterPipe
    ],
    exports: [
        NullAsPipe,
        ScoreDisplayPipe,
        ScoreFilterPipe,
        ScoringQueryFilterPipe
    ]
})
export class PipesModule {
}
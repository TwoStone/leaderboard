import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ScoreInputComponent }   from './score-input.component';
import { ScorePointInputComponent } from './score-point-input.component';
import { ScoreTimeInputComponent } from './score-time-input.component';
import { ScoreUnknownInputComponent } from './score-unkown-input.component';

@NgModule({
    imports: [
        FormsModule
    ],
    exports: [
        ScoreInputComponent
    ],
    declarations: [
        ScoreInputComponent,
        ScorePointInputComponent,
        ScoreTimeInputComponent,
        ScoreUnknownInputComponent
    ],
    providers: [],
    entryComponents: [
        ScorePointInputComponent,
        ScoreTimeInputComponent,
        ScoreUnknownInputComponent
    ]
})
export class ScoreInputModule { }

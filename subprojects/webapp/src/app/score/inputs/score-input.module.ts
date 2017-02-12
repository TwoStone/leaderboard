import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ScoreInputComponent }   from './score-input.component';
import { ScorePointInputComponent } from './score-point-input.component';

@NgModule({
    imports: [
        FormsModule
    ],
    exports: [
        ScoreInputComponent
    ],
    declarations: [
        ScoreInputComponent,
        ScorePointInputComponent
    ],
    providers: [],
    entryComponents: [
        ScorePointInputComponent
    ]
})
export class ScoreInputModule { }

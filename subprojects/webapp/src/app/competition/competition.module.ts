import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ModalModule } from 'ng2-bootstrap/modal';

import { PipesModule } from '../pipes/pipes.module';

import { DivisionComponent } from '../division/division.component';
import { DivisionListComponent, DivisionListItemComponent } from '../division/division-list.component';
import { CreateDivisionComponent } from '../division/create-division.component';
import { CompetitionDashboardComponent } from './competition-dashboard.component';
import { CompetitorsListComponent } from '../competitor/competitor-list.component';
import { CompetitorListItemComponent } from '../competitor/competitor-list-item.component';
import { CreateCompetitorComponent } from '../competitor/create-competitor.component';
import { ScoreComponent } from '../score/score.component';
import { EditScoreComponent } from '../score/edit-score.component';
import { ForPointsInputComponent, ForTimeInputComponent } from '../score/score-input.component';
import { ScoreboardComponent } from '../scoreboard/scoreboard.component';
import { EventScoreBoard } from '../scoreboard/eventscoreboard.component';
import { CompetitionScoreboard } from '../scoreboard/competitionscoreboard.component';

import { EventModule } from '../event/event.module';
import { CompetitionRoutingModule } from './competition-routing.module';

@NgModule({
    imports: [
        ModalModule,
        CommonModule,
        FormsModule,
        PipesModule,
        EventModule,
        CompetitionRoutingModule
    ],
    declarations: [
        DivisionComponent,
        DivisionListComponent,
        DivisionListItemComponent,
        CreateDivisionComponent,
        CompetitionDashboardComponent,
        CompetitorsListComponent,
        CompetitorListItemComponent,
        CreateCompetitorComponent,
        ScoreComponent,
        EditScoreComponent,
        ForPointsInputComponent,
        ForTimeInputComponent,
        ScoreboardComponent,
        EventScoreBoard,
        CompetitionScoreboard
    ], entryComponents: [
        ForTimeInputComponent,
        ForPointsInputComponent
    ]
})
export class CompetitionModule {

}

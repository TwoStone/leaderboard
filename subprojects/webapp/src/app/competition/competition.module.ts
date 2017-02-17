import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { ModalModule } from 'ng2-bootstrap/modal';
import { CompetitorListItemComponent } from '../competitor/competitor-list-item.component';
import { CompetitorsListComponent } from '../competitor/competitor-list.component';
import { CreateCompetitorComponent } from '../competitor/create-competitor.component';
import { CreateDivisionComponent } from '../division/create-division.component';
import { DivisionListComponent, DivisionListItemComponent } from '../division/division-list.component';
import { DivisionComponent } from '../division/division.component';
import { EventModule } from '../event/event.module';
import { PipesModule } from '../pipes/pipes.module';
import { ScoreModule } from '../score/score.module';
import { ScoreboardModule } from '../scoreboard/scoreboard.module';
import { ServiceModule } from '../services/service.module';
import { CompetitionDashboardComponent } from './competition-dashboard.component';
import { CompetitionService } from './competition.service';
import { CompetitionResolver } from './shared/competition.resolver';

import { ModelService } from '../model/model';

@NgModule({
    imports: [
        ScoreboardModule,
        ModalModule,
        CommonModule,
        FormsModule,
        PipesModule,
        EventModule,
        ScoreModule,
        ServiceModule
    ],
    declarations: [
        DivisionComponent,
        DivisionListComponent,
        DivisionListItemComponent,
        CreateDivisionComponent,
        CompetitionDashboardComponent,
        CompetitorsListComponent,
        CompetitorListItemComponent,
        CreateCompetitorComponent
    ],
    providers: [
        CompetitionService,
        ModelService,
        CompetitionResolver
    ]
})
export class CompetitionModule {

}

import { ServiceModule } from '../services/service.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ModalModule } from 'ng2-bootstrap/modal';

import { PipesModule } from '../pipes/pipes.module';

import { DivisionComponent } from '../division/division.component';
import { DivisionListComponent, DivisionListItemComponent } from '../division/division-list.component';
import { CreateDivisionComponent } from '../division/create-division.component';
import { CompetitionDashboardComponent } from './competition-dashboard.component';
import { CompetitionResolver } from './shared/competition.resolver';
import { CompetitorsListComponent } from '../competitor/competitor-list.component';
import { CompetitorListItemComponent } from '../competitor/competitor-list-item.component';
import { CreateCompetitorComponent } from '../competitor/create-competitor.component';

import { ScoreboardModule } from '../scoreboard/scoreboard.module';
import { ScoreModule } from '../score/score.module';
import { EventModule } from '../event/event.module';
import { CompetitionRoutingModule } from './competition-routing.module';

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
        ServiceModule,
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
        CreateCompetitorComponent
    ],
    providers: [ ModelService, CompetitionResolver ]
})
export class CompetitionModule {

}

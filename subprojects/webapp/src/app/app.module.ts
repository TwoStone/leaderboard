import { TooltipModule } from 'ng2-bootstrap/tooltip';
import { StandaloneScoreBoardModule } from './scoreboard/standalone-score-board/standalone-score-board.module';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { RouterModule, Routes } from '@angular/router';
import { BrowserModule }  from '@angular/platform-browser';
import { ModalModule } from 'ng2-bootstrap/modal';

import { PipesModule } from './pipes/pipes.module';

import { AppComponent } from './app.component';
import { CompetitionListComponent } from './competition/competition-list.component';
import { CompetitionListItemComponent } from './competition/competition-list-item.component';
import { CompetitionComponent } from './competition/competition.component';
import { CreateCompetitionComponent } from './competition/create-competition.component';

import { CompetitionModule } from './competition/competition.module';

import { ServiceModule } from './services/service.module';

import { AppRoutingModule } from './app-routing.module';

import 'bootstrap/dist/css/bootstrap.css'
import '../styles/style.css'

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    ModalModule.forRoot(),
    FormsModule,
    PipesModule,
    ServiceModule,
    CompetitionModule,
    TooltipModule.forRoot(),
    StandaloneScoreBoardModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    CompetitionListComponent,
    CompetitionListItemComponent,
    CompetitionComponent,
    CreateCompetitionComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }

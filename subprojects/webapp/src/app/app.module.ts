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
import { StandaloneScoreboardComponent } from './scoreboard/scoreboard-standalone.component';

import { CompetitionModule } from './competition/competition.module';

import { ServiceModule } from './services/service.module';

import { AppRoutingModule } from './app-routing.module';

import 'bootstrap/dist/css/bootstrap.css'

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    ModalModule.forRoot(),
    FormsModule,
    PipesModule,
    ServiceModule,
    CompetitionModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    CompetitionListComponent,
    CompetitionListItemComponent,
    CompetitionComponent,
    CreateCompetitionComponent,
    StandaloneScoreboardComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
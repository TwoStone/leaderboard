import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import 'bootstrap/dist/css/bootstrap.css';
import { ModalModule } from 'ng2-bootstrap/modal';
import { TooltipModule } from 'ng2-bootstrap/tooltip';
import '../styles/style.css';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CompetitionListItemComponent } from './competition/competition-list-item.component';
import { CompetitionListComponent } from './competition/competition-list.component';
import { CompetitionComponent } from './competition/competition.component';
import { CompetitionModule } from './competition/competition.module';
import { CreateCompetitionComponent } from './competition/create-competition.component';
import { requestOptionsProvider } from './http/default-request-options.service';
import { PipesModule } from './pipes/pipes.module';
import { StandaloneScoreBoardModule } from './scoreboard/standalone-score-board/standalone-score-board.module';
import { ServiceModule } from './services/service.module';

@NgModule({
  imports: [
    HttpModule,
    BrowserModule,
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
  providers: [
    requestOptionsProvider
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }

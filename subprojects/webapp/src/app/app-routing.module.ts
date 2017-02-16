import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompetitionDashboardComponent } from './competition/competition-dashboard.component';
import { CompetitionListComponent } from './competition/competition-list.component';
import { CompetitionComponent } from './competition/competition.component';
import { CompetitionResolver } from './competition/shared/competition.resolver';
import { CompetitorsListComponent } from './competitor/competitor-list.component';
import { CreateDivisionComponent } from './division/create-division.component';
import { DivisionListComponent } from './division/division-list.component';
import { DivisionComponent } from './division/division.component';
import { EditEventComponent } from './event/edit-event.component';
import { EventListComponent } from './event/event-list.component';
import { HeatPlanComponent } from './event/heat-plan/heat-plan.component';
import { HeatPlanResolver } from './event/heat-plan/heat-plan.resolver';
import { EditScoreComponent } from './score/edit-score.component';
import { ScoreComponent } from './score/score.component';
import { ScoreboardComponent } from './scoreboard/scoreboard.component';
import { StandaloneScoreBoardComponent } from './scoreboard/standalone-score-board/standalone-score-board.component';

const appRoutes: Routes = [
    {
        path: 'competition/:id',
        component: CompetitionComponent,
        resolve: {
            competition: CompetitionResolver
        },
        children: [
            {
              path: 'scores/standalone',
              component: StandaloneScoreBoardComponent,
              resolve: {
                competition: CompetitionResolver
              }
            },
            { path: '', component: CompetitionDashboardComponent },
            { path: 'divisions', component: DivisionListComponent },
            { path: 'divisions/:divisionId', component: DivisionComponent },
            { path: 'divisions.create', component: CreateDivisionComponent },
            { path: 'events', component: EventListComponent },
            { path: 'events/:eventId', component: EditEventComponent },
            {
                path: 'events/:eventId/heats',
                component: HeatPlanComponent,
                resolve: {
                    heatPlan: HeatPlanResolver
                }
            },
            {
                path: 'scores',
                children:
                    [
                        { path: '', component: ScoreComponent },
                        { path: 'event/:eventId/competitor/:competitorId', component: EditScoreComponent }
                    ]
            },
            { path: 'competitors', component: CompetitorsListComponent},
            { path: 'scoreboard', component: ScoreboardComponent}
        ]
    },
    { path: 'competitions', component: CompetitionListComponent },
    { path: '', redirectTo: 'competitions', pathMatch: 'full'},
];

@NgModule({
    exports: [
        RouterModule
    ],
    imports: [
        RouterModule.forRoot(appRoutes, {
            useHash: true,
        }),
    ]
})
export class AppRoutingModule { }

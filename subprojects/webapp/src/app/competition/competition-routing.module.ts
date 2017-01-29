import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompetitionComponent } from './competition.component';
import { DivisionComponent } from '../division/division.component';
import { DivisionListComponent } from '../division/division-list.component';
import { CreateDivisionComponent } from '../division/create-division.component';
import { CompetitionDashboardComponent } from './competition-dashboard.component';
import { CompetitorsListComponent } from '../competitor/competitor-list.component';
import { EventListComponent } from '../event/event-list.component';
import { ScoreComponent } from '../score/score.component';
import { ScoreboardComponent } from '../scoreboard/scoreboard.component';

const routes: Routes = [
    {
        path: 'competition/:id',
        component: CompetitionComponent,
        children: [
            { path: '', component: CompetitionDashboardComponent },
            { path: 'divisions', component: DivisionListComponent },
            { path: 'divisions/:divisionId', component: DivisionComponent },
            { path: 'divisions.create', component: CreateDivisionComponent },
            { path: 'competitors', component: CompetitorsListComponent},
            { path: 'events', component: EventListComponent },
            { path: 'scores', component: ScoreComponent },
            { path: 'scoreboard', component: ScoreboardComponent}
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class CompetitionRoutingModule {
}

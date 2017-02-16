import { StandaloneScoreBoardComponent } from './scoreboard/standalone-score-board/standalone-score-board.component';
import { CompetitionResolver } from './competition/shared/competition.resolver';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompetitionListComponent } from './competition/competition-list.component';
import { CompetitionComponent } from './competition/competition.component';
import { CreateCompetitionComponent } from './competition/create-competition.component';

const appRoutes: Routes = [
    { path: 'competition.create', component: CreateCompetitionComponent},
    {
        path: 'competitions/:id/scores/standalone',
        component: StandaloneScoreBoardComponent,
        resolve: {
            competition: CompetitionResolver
        }
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

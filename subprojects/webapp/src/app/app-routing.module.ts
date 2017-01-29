import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompetitionListComponent } from './competition/competition-list.component';
import { CompetitionComponent } from './competition/competition.component';
import { CreateCompetitionComponent } from './competition/create-competition.component';
import { StandaloneScoreboardComponent } from './scoreboard/scoreboard-standalone.component';

const appRoutes: Routes = [
    { path: 'competition/:id/...', component: CompetitionComponent },
    { path: 'competition.create', component: CreateCompetitionComponent},
    { path: 'scores/:competitionId', component: StandaloneScoreboardComponent},
    { path: 'competitions', component: CompetitionListComponent },
    { path: '', redirectTo: 'competitions', pathMatch: 'full'}
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes, {
            useHash: true
        })
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule { }
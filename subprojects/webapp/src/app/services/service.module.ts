import { NgModule } from '@angular/core';

import { CompetitionService } from './competition.service';
import { RankingService } from './ranking.service';
import { ScoreService } from './score.service';

@NgModule({
    providers: [
        CompetitionService,
        RankingService,
        ScoreService
    ]
})
export class ServiceModule {

}

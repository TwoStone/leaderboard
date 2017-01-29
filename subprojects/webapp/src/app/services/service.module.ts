import { NgModule } from '@angular/core';

import { CompetitionService } from './competition.service';
import { EventBus } from './eventbus';
import { RankingService } from './ranking.service';
import { ScoreService } from './score.service';

@NgModule({
    providers: [
        CompetitionService,
        EventBus,
        RankingService,
        ScoreService
    ]
})
export class ServiceModule {

}

import { NgModule } from '@angular/core';

import { RankingService } from './ranking.service';
import { ScoreService } from './score.service';

@NgModule({
    providers: [
        RankingService,
        ScoreService
    ]
})
export class ServiceModule {

}

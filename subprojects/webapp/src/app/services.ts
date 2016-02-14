import {Type} from 'angular2/core';

export * from './services/competition.service';
export * from './services/event-type.service';
export * from './services/score.service';
export * from './services/eventbus';

import {CompetitionService} from './services/competition.service';
import {EventTypeService} from './services/event-type.service';
import {ScoreService} from './services/score.service';
import {EventBus} from './services/eventbus';

export const SERVICE_PROVIDERS: Type[] = [
    CompetitionService,
    EventTypeService,
    ScoreService,
    EventBus
];
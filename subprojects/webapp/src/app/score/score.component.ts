import {
    Component,
    OnInit,
    ViewChild
} from '@angular/core';

import { ModalDirective } from 'ng2-bootstrap';

import {
    ModelService,
    Event,
    Score,
    Division
} from '../model/model';

import { ScoreEditComponent } from './score-edit.component';
import { ScoreService } from '../services/score.service';
import { EventBus } from '../services/eventbus';

@Component({
    templateUrl: './score.component.html',
    styles: [`
        .clickable {
            cursor: pointer;
        }
    `],
    providers : [ScoreService],
})
export class ScoreComponent implements OnInit {

    events: Event[] = [];
    divisions: Division[] = [];
    event: string = '';
    scores: Score[] = [];
    query: string = '';
    division: string = 'all';
    onlyUnset: boolean = false;

    @ViewChild(ModalDirective) $modal: ModalDirective;
    @ViewChild(ScoreEditComponent) $edit: ScoreEditComponent;

    constructor(
        private modelService: ModelService,
        private scoreService: ScoreService,
        private eventBus: EventBus) {
        this.eventBus.on('score.updated').subscribe(score => {
            this.updateScores(score.event.id);
        });
    }

    selectScore(score: Score) {
        this.$edit.score = score;
        this.$modal.show();
    }

    ngOnInit() {
        this.modelService.onCompetitionUpdate.subscribe(competition => {
            this.events = competition.events
            if (this.events.length > 0) {
                this.event = competition.events[0].id.toString();
                this.divisions = competition.divisions;
                this.updateScores(+this.event);
            }
        });
    }

    eventChanged(e: number) {
        this.updateScores(e);
    }

    updateScores(eventId: number) {
        this.scoreService.getScoresForEvent(eventId).subscribe(scores => {
            this.scores = scores;
        });
    }
}
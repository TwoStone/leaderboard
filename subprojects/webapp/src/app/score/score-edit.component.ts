import {
     Component,
     Input,
     Output,
     EventEmitter
} from 'angular2/core';

import {
    ScoreService,
    EventBus
} from '../services';

import {
    Score
} from '../model/model';

@Component({
    selector: 'score-edit',
    template: `
        <div>
            <form (ngSubmit)="onSubmit()" class="form" *ngIf="_score">
                <div class="form-group">
                    <label for="name">Name</label>
                    <div #name>{{ _score.competitor.name }}</div>
                </div>
                <div class="form-group">
                    <label for="division">Division</label>
                    <div #division>{{ _score.competitor.name}}</div>
                </div>
                <div class="form-group">
                    <label for="event">Event</label>
                    <div #event>{{ _score.event.name}}</div>
                </div>
                <div class="form-group">
                    <label for="score">Score</label>
                    <input type="number" class="form-control" [(ngModel)]="scoreValue"/>
                </div>
                <button class="btn btn-primary" type="submit">Submit</button>
            </form>            
        </div>
    `
})
export class ScoreEditComponent {

    _score: Score;

    @Output() onSubmitted: EventEmitter<Score> = new EventEmitter();

    constructor(private scoreService: ScoreService,
        private eventBus: EventBus) { }

    @Input()
    set score(score: Score) {
        if (score) {
            this._score = jQuery.extend({}, score);
        }
    }

    get scoreValue(): string {
        return this._score.value >= 0 ? this._score.value.toString() : '';
    }

    set scoreValue(val: string) {
        this._score.value = val.length === 0 ? -1 : +val;
    }

    onSubmit() {
        this.scoreService.addScore(this._score).subscribe(score => {
            this.onSubmitted.emit(score);
            this.eventBus.fire('score.updated', score);
        });
    }
}
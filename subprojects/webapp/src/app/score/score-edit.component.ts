import {
     Component,
     Input,
     Output,
     EventEmitter,
     ElementRef,
     ComponentRef,
     DynamicComponentLoader,
     Type
} from 'angular2/core';

import {
    ScoreService,
    EventBus
} from '../services';

import {
    Score,
    EventType
} from '../model/model';

import {
    ScoreInputFactory
} from './score-input.component';

@Component({
    selector: 'score-edit',
    template: `
        <div>
            <form (ngSubmit)="onSubmit()" class="form">
                <div class="form-group"  *ngIf="_score">
                    <label for="name">Name</label>
                    <div #name>{{ _score.competitor.name }}</div>
                </div>
                <div class="form-group"  *ngIf="_score">
                    <label for="division">Division</label>
                    <div #division>{{ _score.competitor.division.name }}</div>
                </div>
                <div class="form-group"  *ngIf="_score">
                    <label for="event">Event</label>
                    <div #event>{{ _score.event.name}}</div>
                </div>
                <div #scoreInput></div>
                <button class="btn btn-primary" type="submit">Submit</button>
            </form>            
        </div>
    `
})
export class ScoreEditComponent {

    _score: Score;

    @Output() onSubmitted: EventEmitter<Score> = new EventEmitter();

    private inputComponent: ComponentRef;

    constructor(
        private scoreService: ScoreService,
        private componentLoader: DynamicComponentLoader,
        private elementRef: ElementRef,
        private eventBus: EventBus) {
    }

    @Input()
    set score(score: Score) {
        if (score) {
            this._score = jQuery.extend({}, score);

            if (this.inputComponent) {
                this.inputComponent.dispose();
            }

            this.componentLoader.loadIntoLocation(
                ScoreInputFactory.getComponentForType(score.event),
                this.elementRef,
                'scoreInput').then(ref => {
                    this.inputComponent = ref;
                    ref.instance.score = this._score;
                });
        }
    }

    get scoreValue(): string {
        return this._score.score ? this._score.score.toString() : '';
    }

    set scoreValue(val: string) {
        this._score.score = val.length === 0 ? null : +val;
    }

    onSubmit() {
        this.scoreService.addScore(this._score).subscribe(score => {
            this.onSubmitted.emit(score);
            this.eventBus.fire('score.updated', score);
        });
    }
}
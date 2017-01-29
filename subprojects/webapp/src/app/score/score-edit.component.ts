import {
     Component,
     Input,
     Output,
     EventEmitter,
     ElementRef,
     ComponentRef,
     ComponentFactoryResolver,
     Type,
     ViewChild,
     ViewContainerRef
} from '@angular/core';

import * as jQuery from 'jquery';

import { ScoreService } from '../services/score.service';
import { EventBus } from '../services/eventbus';

import {
    Score,
    EventType
} from '../model/model';

import {
    ScoreInputFactory, ScoreInput
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
                <div class="checkbox" *ngIf="_score && _score.event.scalable">
                    <label>
                        <input type="checkbox" [(ngModel)]="_score.scaled"> Scaled
                    </label>
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

    @ViewChild('scoreInput', { read: ViewContainerRef })
    private element: ViewContainerRef;

    private inputComponent: ComponentRef<ScoreInput>;

    constructor(
        private scoreService: ScoreService,
        private componentLoader: ComponentFactoryResolver,
        private elementRef: ElementRef,
        private eventBus: EventBus) {
    }

    @Input()
    set score(score: Score) {
        if (score) {
            this._score = jQuery.extend({}, score);

            if (this.inputComponent) {
                this.inputComponent.destroy();
            }

            let componentFactory = this.componentLoader.resolveComponentFactory(ScoreInputFactory.getComponentForType(score.event));
            let component = this.element.createComponent(componentFactory);
            component.instance.score = this._score;
            this.inputComponent = component;
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
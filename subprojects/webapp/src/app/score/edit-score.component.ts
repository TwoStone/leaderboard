import {
     Component,
     Input,
     Output,
     EventEmitter,
     ElementRef,
     ComponentRef,
     ComponentFactoryResolver,
     OnInit,
     Type,
     ViewChild,
     ViewContainerRef
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import * as jQuery from 'jquery';

import { ScoreService } from '../services/score.service';
import { EventBus } from '../services/eventbus';

import {
    Score,
} from '../model/model';

import {
    //ScoreInputFactory, ScoreInput
} from './score-input.component';

@Component({
    templateUrl: './edit-score.component.html'
})
export class EditScoreComponent implements OnInit {

    public score: Score;

    @ViewChild('scoreInput', { read: ViewContainerRef })
    private element: ViewContainerRef;

    //private inputComponent: ComponentRef<ScoreInput>;

    constructor(
        private scoreService: ScoreService,
        private componentLoader: ComponentFactoryResolver,
        private route: ActivatedRoute,
        private elementRef: ElementRef,
        private eventBus: EventBus) {
    }

    get scoreValue(): string {
        // return this._score.score ? this._score.score.toString() : '';
        return '';
    }

    set scoreValue(val: string) {
        // this._score.score = val.length === 0 ? null : +val;
    }

    public ngOnInit() {
        this.route.params.subscribe((params) => {
            let eventId = params['eventId'];
            let competitorId = params['competitorId'];
            this.scoreService.getScore(eventId, competitorId).subscribe((score) => {
                this.score = score;
            });
        });
    }

    onSubmit() {
        this.scoreService.addScore(this._score).subscribe(score => {
            this.onSubmitted.emit(score);
            this.eventBus.fire('score.updated', score);
        });
    }
}
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

import {
    Score,
} from '../model/model';

@Component({
    templateUrl: './edit-score.component.html'
})
export class EditScoreComponent implements OnInit {

    public score: Score;

    @ViewChild('scoreInput', { read: ViewContainerRef })
    private element: ViewContainerRef;

    constructor(
        private scoreService: ScoreService,
        private componentLoader: ComponentFactoryResolver,
        private route: ActivatedRoute,
        private elementRef: ElementRef) {
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

    public onSubmit() {
        // TOOD Save score
    }
}
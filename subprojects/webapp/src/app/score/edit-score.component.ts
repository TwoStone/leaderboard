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
import {  } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import * as jQuery from 'jquery';
import * as moment from 'moment';
import { Observable } from 'rxjs';

import { ModelService } from '../model/model';
import { ScoreService } from '../services/score.service';
import { Score } from '../model/model';
import { PartialScore } from '../model/partial-score';
import { ScoreIngredient } from '../model/score-ingredient';
import { ScoreIngredientType } from '../model/score-ingredient-type';

@Component({
    templateUrl: './edit-score.component.html',
    styleUrls: ['./edit-score.component.css']
})
export class EditScoreComponent implements OnInit {

    public score: Score;

    @ViewChild('scoreInput', { read: ViewContainerRef })
    private element: ViewContainerRef;

    constructor(
        private scoreService: ScoreService,
        private componentLoader: ComponentFactoryResolver,
        private route: ActivatedRoute,
        private router: Router,
        private modelService: ModelService,
        private elementRef: ElementRef) {
    }

    public ngOnInit() {
        this.modelService.onCompetitionUpdate.map((competition) => {
            return {
                competitionId: competition.id
            };
        }).zip(this.route.params, Object.assign).subscribe((params) => {
            let competitionId = params['competitionId'];
            let eventId = params['eventId'];
            let competitorId = params['competitorId'];
            this.scoreService.getScore(competitionId, eventId, competitorId).subscribe((score) => {
                this.score = score;
            });
        });
    }

    public getType(type: ScoreIngredientType) {
        switch (type) {
            case ScoreIngredientType.POINTS:
                return 'number';
            case ScoreIngredientType.TIME:
                return 'time';
            default:
                return 'text';
        }
    }

    public onSubmit() {
        this.scoreService.addScore(this.score).subscribe(() => {
            this.navigateBack();
        });
    }

    public abort() {
        this.navigateBack();
    }

    public setScore(score: PartialScore) {
        this.score.parts[score.name] = score;
    }

    public getScore(name: string) {
        return this.score.parts[name];
    }

    public getValue(score: Score, ingredient: ScoreIngredient): string {
        let part = score.parts[ingredient.name];
        if (part) {
            return this.formatValue(part.value.toString(), ingredient.type).toString();
        } else {
            return '';
        }
    }

    public setValue(score: Score, ingredient: ScoreIngredient, $event: any) {
        let part = score.parts[ingredient.name];
        let value = this.unformatValue($event.toString(), ingredient.type);
        if (part) {
            part.value = value;
        } else {
            let newPart = {
                name: ingredient.name,
                value: value
            }
            score.parts[ingredient.name] = newPart;
        }
    }

    private unformatValue(value: string, type: ScoreIngredientType): number {
        switch (type) {
            case ScoreIngredientType.TIME:
                let parsed = /(?:(\d?\d):)?(\d?\d)/.exec(value);
                if (parsed) {
                    let duration = moment.duration(+parsed[2], 'seconds');
                    if (parsed[1]) {
                        duration = duration.add(+parsed[1], 'minutes');
                    }
                    return duration.asSeconds();
                }
            default:
                return +value;
        }
    }

    private formatValue(value: string, type: ScoreIngredientType) {
        switch (type) {
            case ScoreIngredientType.POINTS:
                return +value;
            case ScoreIngredientType.TIME:
                let val = +value;
                if (val < 59) {
                    return val;
                } else {
                    return moment.duration(+value, 'seconds').format('mm:ss');
                }
            default:
                return value
        }
    }

    private navigateBack() {
        this.router.navigate(['scores'], {
            relativeTo: this.route.parent.parent,
            queryParams: {
                event: this.score.event.id
            }
        });
    }
}

import {
    Component,
    OnInit,
    Pipe,
    PipeTransform
} from 'angular2/core';

import {
    ModelService,
    Event,
    Score,
    Division
} from '../model/model';

import {
    ScoreService
} from './score.service';

@Pipe({ name: 'score' })
class ScorePipe implements PipeTransform {
    transform(value: number) {
        return value < 0 ? 'not set' : value;
    }
}

@Pipe({ name: 'scoresFilter'})
class ScoresFilterPipe implements PipeTransform {
    transform(values: Score[], args: string[]) {
        let query = args[0];
        if (query.length === 0) {
            return values;
        }

        return values.filter((score, index, arr) => {
            return score.competitor.name.score(query) > 0;
        }).sort((a, b) => {
            let scoreA = a.competitor.name.score(query);
            let scoreB = b.competitor.name.score(query);
            return scoreB - scoreA;
        });
    }
}

@Pipe({ name: 'inDivision' })
class DivisionScoreFilter implements PipeTransform {
    transform(scores: Score[], args: string[]) {
        if (args.length === 0) {
            return scores;
        }
        let divisionId = args[0];
        if (divisionId.length === 0) {
            return scores;
        }

        return scores.filter((score, index, args) => {
            return score.competitor.division.id === +divisionId;
        });
    }
}

@Component({
    template: `
        <div class="form-inline">
            <div class="form-group">
                <label for="query">Search</label>
                <input class="form-control" type="text" [(ngModel)]="query"/>
            </div>
            <div class="form-group">
                <label for="division">Division</label>
                <select class="form-control" [(ngModel)]="division">
                    <option *ngFor="#division of divisions" [value]="division.id">
                        {{ division.name }}
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label for="event">Event</label>
                <select class="form-control" [ngModel]="event" (ngModelChange)="eventChanged($event)">
                    <option *ngFor="#e of events" [value]="e.id">{{ e.name }}</option>
                </select>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Division</th>
                        <th>Score</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="#score of scores | inDivision:division | scoresFilter:query">
                        <td>{{ score.competitor.name }}</td>
                        <td>{{ score.competitor.division.name }}</td>
                        <td>{{ score.value | score }}</td>
                    </tr>
                </tbody>
            </table>
            <div *ngIf="scores.length == 0">
                No scores for selected event.
            </div>
        </div>
    `,
    providers : [ScoreService],
    pipes: [ScorePipe, ScoresFilterPipe, DivisionScoreFilter]
})
export class ScoreComponent implements OnInit {

    events: Event[] = [];
    divisions: Division[] = [];
    event: string = '';
    scores: Score[] = [];
    query: string = '';
    division: string = '';

    constructor(
        private modelService: ModelService,
        private scoreService: ScoreService) { }

    ngOnInit() {
        this.modelService.onCompetitionUpdate.subscribe(competition => {
            this.events = competition.events
            this.divisions = competition.divisions;
        });
    }

    eventChanged(e: number) {
        this.scoreService.getScoresForEvent(e).subscribe(scores => {
            this.scores = scores;
        });
    }
}
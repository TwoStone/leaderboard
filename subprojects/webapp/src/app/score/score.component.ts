import {
    Component,
    OnInit,
    ViewChild
} from 'angular2/core';

import {
    MODAL_DIRECTIVES,
    ModalComponent
} from '../tools/tools';

import {
    ModelService,
    Event,
    Score,
    Division
} from '../model/model';

import {
    ScoreEditComponent
} from './score-edit.component';

import {
    ScoreService,
    EventBus
} from '../services';

import {
    ScoreFilterPipe,
    ScoringQueryFilterPipe,
    NullAsPipe
} from './pipes';

@Component({
    template: `
        <modal #modal>
            <modal-header>
                <h3>
                    Edit score
                </h3>
            </modal-header>
            <modal-body>
                <score-edit [score]="selectedScore" (onSubmitted)="modal.hide()">
                </score-edit>
            </modal-body>
        </modal>
        <div class="form-inline">
            <div class="form-group">
                <label for="query">Search</label>
                <input class="form-control" type="text" [(ngModel)]="query"/>
            </div>
            <div class="form-group">
                <label for="division">Division</label>
                <select class="form-control" [(ngModel)]="division">
                    <option value="all">All</option>
                    <option *ngFor="#division of divisions" [value]="division.id">
                        {{ division.name }}
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label for="event">Event</label>
                <select class="form-control" [(ngModel)]="event" (ngModelChange)="eventChanged($event)">
                    <option *ngFor="#e of events" [value]="e.id">{{ e.name }}</option>
                </select>
            </div>
            <div class="form-group">
                <label for="onlyUnset">only unset</label>
                <input type="checkbox" class="form-control" [(ngModel)]="onlyUnset"/>
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
                    <tr *ngFor="#score of scores | scoreFilter:division:onlyUnset | scoringQueryFilter:query" 
                        (click)="selectScore(score)">
                        <td>{{ score.competitor.name }}</td>
                        <td>{{ score.competitor.division.name }}</td>
                        <td>{{ score.score | nullAs:"not set" }}</td>
                    </tr>
                </tbody>
            </table>
            <div *ngIf="scores.length == 0">
                No scores for selected event.
            </div>
        </div>
    `,
    directives: [ScoreEditComponent, MODAL_DIRECTIVES],
    providers : [ScoreService],
    pipes: [NullAsPipe, ScoringQueryFilterPipe, ScoreFilterPipe]
})
export class ScoreComponent implements OnInit {

    events: Event[] = [];
    divisions: Division[] = [];
    event: string = '';
    scores: Score[] = [];
    query: string = '';
    division: string = '';
    onlyUnset: boolean = false;

    @ViewChild(ModalComponent) $modal: ModalComponent;
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
        this.$modal.open();
    }

    ngOnInit() {
        this.modelService.onCompetitionUpdate.subscribe(competition => {
            this.events = competition.events
            this.divisions = competition.divisions;
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
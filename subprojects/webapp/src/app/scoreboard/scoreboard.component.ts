import {
    Component,
    OnInit,
} from '@angular/core';

import {
    CompetitionService,
} from '../competition/competition.service';

import {
    Competition,
    Division,
    Event,
    ModelService
} from '../model/model';

interface ScoreQuery {
    event: number;
    division: number;
}

@Component({
    selector: 'scoreboard',
    template: `
        <h3>Scoreboard</h3>
        <div class="form-inline">
            <!-- Division -->
            <div class="form-group">
                <label for="division">Division</label>
                <select class="form-control" [(ngModel)]="query.division">
                    <option *ngFor="let division of divisions" [value]="division.id">{{ division.name }}</option>
                </select>
            </div>
            <!-- Event -->
            <div class="form-group">
                <label for="event">Event</label>
                <select class="form-control" [(ngModel)]="query.event">
                    <option value="0">all</option>
                    <option *ngFor="let event of events" [value]="event.id">{{ event.name }}</option>
                </select>
            </div>
        </div>
        <div *ngIf="competition">
            <eventScoreBoard *ngIf="query.event != 0"
                [competitionId]="competition.id" 
                [eventId]="query.event" 
                [divisionId]="query.division">
            </eventScoreBoard>
            <competitionScoreBoard *ngIf="query.event == 0"
                [competitionId]="competition.id"
                [divisionId]="query.division">
            </competitionScoreBoard>
        </div>
        <div *ngIf="!competition">
            Loading...
        </div>
    `
})
export class ScoreboardComponent implements OnInit {

    public competition: Competition;
    public divisions: Division[];
    public events: Event[];
    public query: ScoreQuery = {
        division: 0,
        event: 0
    }

    constructor(
        private competitionService: CompetitionService,
        private modelService: ModelService) {
    }

    get firstDivision() {
        if (this.divisions) {
            return this.divisions[0].id;
        }
    }

    public ngOnInit() {
        this.modelService.onCompetitionUpdate.subscribe((competition) => {
            this.competition = competition;
            this.divisions = competition.divisions;
            this.query.division = this.divisions ? this.divisions[0].id : 0;
            this.events = competition.events;
            this.query.event = 0;
        })
    }
}

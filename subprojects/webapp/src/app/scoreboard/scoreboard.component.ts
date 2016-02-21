import {
    Component,
    OnInit,
} from 'angular2/core';

import {
    CompetitionService,
} from '../services';

import {
    ModelService,
    Competition,
    Division,
    Event
} from '../model/model';

import {
    EventScoreBoard
} from './eventscoreboard.component';

import {
    CompetitionScoreboard
} from './competitionscoreboard.component';

class ScoreQuery {
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
                <select class="form-control" [(ngModel)]="query.division" (ngModelChange)="filterChanged($event)">
                    <option *ngFor="#division of divisions" [value]="division.id">{{ division.name }}</option>
                </select>
            </div>
            <!-- Event -->
            <div class="form-group">
                <label for="event">Event</label>
                <select class="form-control" [(ngModel)]="query.event" (ngModelChange)="filterChanged($event)">
                    <option value="0">all</option>
                    <option *ngFor="#event of events" [value]="event.id">{{ event.name }}</option>
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
    `,
    directives: [EventScoreBoard, CompetitionScoreboard]
})
export class ScoreboardComponent implements OnInit {

    competition: Competition;
    divisions: Division[];
    events: Event[];
    query: ScoreQuery = new ScoreQuery();

    constructor(
        private competitionService: CompetitionService,
        private modelService: ModelService) {
    }

    get firstDivision() {
        if (this.divisions) {
            return this.divisions[0].id;
        }
    }

    ngOnInit() {
        this.modelService.onCompetitionUpdate.subscribe(competition => {
            this.competition = competition;
            this.divisions = competition.divisions;
            this.query.division = this.divisions[0].id;
            this.events = competition.events;
            this.query.event = 0;
        })
    }

    filterChanged(event: any) {
        console.log(event);
    }

}
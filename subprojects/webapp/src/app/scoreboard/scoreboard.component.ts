import {
    Component,
    OnInit
} from 'angular2/core';

import {
    CompetitionService,
} from '../services';

import {
    ModelService,
    Division,
    Event
} from '../model/model';

class ScoreQuery {
    event: number;
    division: number;
}

@Component({
    selector: 'scoreboard',
    template: `
        <h3>Scoreboard</h3>
        <form class="form">
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
                <select class="form-control" [(ngModel)]="query.event" (ngModelChange)="filterChnaged($event)">
                    <option value="0">all</option>
                    <option *ngFor="#event of events" [value]="event.id">{{ event.name }}</option>
                </select>
            </div>
        </form>
    `
})
export class ScoreboardComponent implements OnInit {

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
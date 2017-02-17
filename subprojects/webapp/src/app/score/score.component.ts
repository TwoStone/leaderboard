import {
    Component,
    OnInit
} from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import {
    Division,
    Event,
    ModelService,
    Score
} from '../model/model';

import { ScoreService } from '../services/score.service';

@Component({
    templateUrl: './score.component.html',
    styles: [`
        .clickable {
            cursor: pointer;
        }
    `]
})
export class ScoreComponent implements OnInit {

    public events: Event[] = [];
    public divisions: Division[] = [];
    public event: string = '';
    public scores: Score[] = [];
    public query: string = '';
    public division: string = 'all';
    public onlyUnset: boolean = false;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private modelService: ModelService,
        private scoreService: ScoreService) {
    }

    public eventChanged($event: string) {
        this.event = $event;
        this.router.navigate([], {
            queryParams: {
                event: $event
            },
            relativeTo: this.route
        });
    }

    public selectScore(score: Score) {
        this.router.navigate(['event', score.event.id, 'competitor', score.competitor.id ], { relativeTo: this.route });
    }

    public ngOnInit() {
        this.route.queryParams.map((params) => params['event'])
            .subscribe((eventId) => {
                if (eventId) {
                    this.updateScores(eventId);
                }
            });

        let snapshot = this.route.snapshot;
        this.modelService.onCompetitionUpdate.subscribe((competition) => {
            this.events = competition.events
            if (this.events.length > 0) {
                this.event =  snapshot.queryParams['event'] || competition.events[0].id.toString();
                this.divisions = competition.divisions;
                this.updateScores(+this.event);
            }
        });
    }

    public updateScores(eventId: number) {
        this.scoreService.getScoresForEvent(eventId).subscribe((scores) => {
            this.scores = scores;
        });
    }
}

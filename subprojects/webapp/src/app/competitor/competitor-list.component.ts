import {
    Component,
    OnInit,
    Input,
    Output,
    EventEmitter
} from '@angular/core';

import {
    ModelService,
    Competitor
} from '../model/model';

import {
    CreateCompetitorComponent
} from './create-competitor.component';

@Component({
    selector: '[competitorListItem]',
    template: `
        <td>{{ competitorItem.name }}</td>
        <td>{{ competitorItem.division.name }}</td>
    `
})
export class CompetitorListItemComponent {
    @Input() competitorItem: Competitor;
}

@Component({
    selector: 'competitor-list',
    template: `
        <div bsModal #modal="bs-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="pull-left">Register Competitor</h3>
                        <button type="button" class="close pull-right" (click)="modal.hide()" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <create-competitor (onCreated)="modal.hide()">
                        </create-competitor>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-primary" (click)="modal.show()">
            <i class="fa fa-plus"></i>
            Register competitor
        </button>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Division</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="let competitor of competitors" competitorListItem [competitorItem]="competitor">
                    </tr>
                </tbody>
            </table>
        </div>
    `
})
export class CompetitorsList implements OnInit {
    competitors: Array<Competitor>;

    constructor(
        private service: ModelService
        ) {
    }

    ngOnInit() {
        this.service.onCompetitionUpdate.subscribe(comp => {
            this.competitors = comp.competitors;
        });
    }
}

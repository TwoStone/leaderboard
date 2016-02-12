import {
    Component,
    Input
} from 'angular2/core';

import {
    ModalComponent
} from './modal'

@Component({
    selector: 'modal-header',
    template: `
        <div class="modal-header">
            <button *ngIf="showClose"
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"><span aria-hidden="true" (click)="modal.close()">&times;</span></button>
            <ng-content></ng-content>
        </div>
    `
})
export class ModalHeaderComponent {

    @Input('show-close') showClose: boolean;

    constructor(private modal: ModalComponent) {}
}

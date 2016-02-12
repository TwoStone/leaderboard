import {Component} from 'angular2/core';

@Component({
    selector: 'modal-body',
    template: `
    <div class="modal-body">
        <ng-content></ng-content>
    </div>
    `
})
export class ModalBodyComponent {
}
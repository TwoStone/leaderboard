/// <reference path="../../../../typings/browser.d.ts" />
import {
    Component,
    Input,
    Output,
    EventEmitter
} from 'angular2/core';

let id: number = 0;
function uniqueId(prefix: string): string {
    return `${prefix}_${id++}`;
}

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
    
    constructor(private modal:ModalComponent) {}
}

@Component({
    selector: 'modal-body',
    template: `
    <div class="modal-body">
        <ng-content></ng-content>
    </div>
    `
})

@Component({
    selector: 'modal',
    template: `
        <div id="{{id}}"" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog">
            <div class="modal-content">
              <ng-content></ng-content>
            </div><!-- /.modal-content -->
          </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    `
})
class ModalComponent {
    id: string = uniqueId('modal');
    $modal: JQuery;
    
    @Output() onHide = new EventEmitter();
    
    @Input() modalOptions: ModalOptions;
    
    ngOnInit() {
        this.$modal = jQuery(`$$${id}`).modal(this.modalOptions);
        this.$modal.appendTo('body').modal({ show: false });
        this.$modal.on('hide.bs.modal', (e) => {
            this.onHide.next(e); 
        });
    }
    
    open() {
        this.$modal.modal('show');
    }
    
    hide() {
        this.$modal.modal('hide');
    }
}
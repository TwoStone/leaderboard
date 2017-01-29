import {
    Component,
    Input,
    Output,
    EventEmitter,
    Type,
    AfterViewInit
} from '@angular/core';

let id: number = 0;
function uniqueId(prefix: string): string {
    return `${prefix}_${id++}`;
}

@Component({
    selector: 'modal',
    template: `
        <div id="{{id}}" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog">
            <div class="modal-content">
              <ng-content></ng-content>
            </div>
          </div>
        </div>
    `
})
export class ModalComponent implements AfterViewInit {
    id: string = uniqueId('modal');
    $modal: JQuery;

    @Output() onHide = new EventEmitter();
    @Output() onShown = new EventEmitter();

    ngAfterViewInit() {
        this.$modal = jQuery('#' + this.id);
        this.$modal.appendTo('body').modal({ show: false });
        this.$modal.on('hide.bs.modal', (e) => {
            this.onHide.emit(e);
        });
        this.$modal.on('shown.bs.modal', (e) => {
            this.onShown.emit(e);
        });
    }

    open() {
        this.$modal.modal('show');
    }

    hide() {
        this.$modal.modal('hide');
    }
}
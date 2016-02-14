import {Type} from 'angular2/core';

import {ModalComponent} from './modal/modal';
import {ModalBodyComponent} from './modal/modal-body';
import {ModalHeaderComponent} from './modal/modal-header';

export * from './modal/modal';
export * from './modal/modal-body';
export * from './modal/modal-header';
 

export const MODAL_DIRECTIVES: Type[] = [
    ModalComponent,
    ModalBodyComponent,
    ModalHeaderComponent
];
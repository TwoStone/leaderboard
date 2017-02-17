import { Injectable, Provider } from '@angular/core';
import { BaseRequestOptions, RequestOptions } from '@angular/http';

@Injectable()
export class DefaultRequestOptions extends BaseRequestOptions {

    constructor() {
        super();
        this.headers.set('Content-Type', 'application/json');
    }
}

export const requestOptionsProvider: Provider = { provide: RequestOptions, useClass: DefaultRequestOptions };

import { Component } from '@angular/core';

import { Division } from '../model/model';

@Component({
    template: `
        <h1>{{division.name}}</h1>
    `
})
export class DivisionComponent {

    division: Division;

    constructor() {
    }

}
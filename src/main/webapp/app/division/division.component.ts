import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Division} from '../model/model';

@Component({
    template: `
        <h1>{{division.name}}</h1>
    `
})
export class DivisionComponent {

    division: Division;

    constructor(private _params: RouteParams) {
    }

}
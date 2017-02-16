import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { Competition } from '../../model/model';
import { CompetitionService } from '../competition.service';

@Injectable()
export class CompetitionResolver implements Resolve<Competition> {

    public static COMPETTITION_ROUTE_PARAM: string = 'id';

    constructor(private service: CompetitionService) {
    }

    public resolve(route: ActivatedRouteSnapshot): Observable<Competition> {
        let id = route.params[CompetitionResolver.COMPETTITION_ROUTE_PARAM]
            || route.parent.params[CompetitionResolver.COMPETTITION_ROUTE_PARAM];
        return this.service.get(+id);
    }

}
import { Injectable } from '@angular/core'
import { ActivatedRouteSnapshot, Resolve } from '@angular/router'
import { Observable } from 'rxjs/Rx'
import { CompetitionService } from '../competition/competition.service'
import { Competition } from '../model/model'

@Injectable()
export class CompetitionResolver implements Resolve<Competition> {

    constructor(
        private competitionService: CompetitionService
    ) {}

    public resolve(route: ActivatedRouteSnapshot): Observable<Competition> {
        let competitionId = route.params['id']
        return this.competitionService.get(+competitionId)
    }
}
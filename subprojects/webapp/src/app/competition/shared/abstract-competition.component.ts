import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Competition } from '../../model/model';

export abstract class AbstractCompetitionComponent {

    protected competition: Observable<Competition>;

    constructor(protected route: ActivatedRoute) {
        this.competition = this.route.data.map((data) => {
            return data['competition'];
        });
    }
}

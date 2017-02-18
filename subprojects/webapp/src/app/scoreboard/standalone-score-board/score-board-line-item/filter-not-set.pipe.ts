import { Pipe, PipeTransform } from '@angular/core';
import { RankedEventScore } from '../../../model/model';

@Pipe({
    name: 'filterNotSet'
})
export class FilterNotSetPipe implements PipeTransform {

    public transform(score: RankedEventScore[]) {
        if (score) {
            return score.filter((s) => !s.score.notSet);
        }
    }
}

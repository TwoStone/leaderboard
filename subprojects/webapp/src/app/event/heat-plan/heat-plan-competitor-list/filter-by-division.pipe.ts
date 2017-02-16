import { Pipe, PipeTransform } from '@angular/core';
import { Competitor, Division } from '../../../model/model';

@Pipe({
    name: 'filterByDivision'
})
export class FilterByDivisionPipe implements PipeTransform {
    public transform(competitors: Competitor[], division: Division): any {
        if (competitors) {
            return competitors.filter((c) => {
                        return c.division.id === division.id
            });
        }
    }
}

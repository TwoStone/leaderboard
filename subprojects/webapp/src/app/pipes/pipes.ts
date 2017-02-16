import {
    Pipe,
    PipeTransform
} from '@angular/core';

import {
    Score
} from '../model/model';

@Pipe({ name: 'scoringQueryFilter'})
export class ScoringQueryFilterPipe implements PipeTransform {
    transform(values: Score[], query: string) {
        if (query.length === 0) {
            return values;
        }

        return values.filter((score, index, arr) => {
            return score.competitor.name.score(query) > 0;
        }).sort((a, b) => {
            let scoreA = a.competitor.name.score(query);
            let scoreB = b.competitor.name.score(query);
            return scoreB - scoreA;
        });
    }
}

@Pipe({ name: 'scoreFilter' })
export class ScoreFilterPipe implements PipeTransform {
    transform(scores: Score[], divisionId: string, onlyUnset: boolean) {

        let result: Score[];

        if (divisionId.length === 0 || divisionId === 'all') {
            result = scores;
        } else {
            result = scores.filter((score, index, args) => {
                return score.competitor.division.id === +divisionId;
            });
        }

        if (onlyUnset) {
            result = result.filter((score, index, array) => {
                 return score.id == null;
            });
        }
        return result;
    }
}

@Pipe({
    name: 'nullAs'
})
export class NullAsPipe implements PipeTransform {

    transform(value: any, args: any[]) {
        if (value) {
            return value;
        }

        if (args.length !== 1) {
            throw new Error('nullAs pipe needs one argument');
        }
        return args[0];
    }
}

@Pipe({
    name: 'asScore'
})
export class ScoreDisplayPipe implements PipeTransform {
    transform(value: Score, args: any[]): string {
        // if (!value.score) {
        //     return args[0];
        // }

        let result = '';

//         switch (value.event.type) {
// // '            case EventType.FOR_TIME:
// //                 result += moment.duration(value.score, 'seconds').format('mm:ss');
// //                 break;
// //             default:
// //                 result += value.score.toString();
// //                 break;
// '        }

        if (value.scaled) {
            result += '-s';
        }

        return result;
    }
}
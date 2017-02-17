import * as moment from 'moment';

declare module 'moment' {
    interface Duration {
        format(template: string, precision?: string, settings?: DurationFormatSettings): string;
    }

    interface DurationFormatSettings {
        template: string;
        precision: number;
        trim: boolean;
        forceLength: boolean;
    }
}
/// <reference path="./browser/ambient/moment/moment.d.ts" />

declare module moment {
    interface Duration {
        format(format: string): string;
    }
}
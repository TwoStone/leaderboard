// Compiled using typings@0.6.8
// Source: https://raw.githubusercontent.com/DefinitelyTyped/DefinitelyTyped/e78260ef9b39498a6a1bb513eeb4e0ba37203c25/string_score/string_score.d.ts
// Type definitions for string_score 0.1.22
// Project: https://github.com/joshaven/string_score
// Definitions by: Marcin Porębski <https://github.com/marcinporebski/>
// Definitions: https://github.com/borisyankov/DefinitelyTyped

declare module "string_score"
{
    // nothing here as it's only extending the build in String class
}

interface String {
    score: (word: string, fuzzy?: number) => number;
}
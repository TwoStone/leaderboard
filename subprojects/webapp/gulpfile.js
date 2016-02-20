var gulp = require('gulp');
var ts = require('gulp-typescript');
var del = require('del');
var sourcemaps = require('gulp-sourcemaps');
var tslint = require("gulp-tslint");
var watch = require('gulp-watch');
var batch = require('gulp-batch');


var tsConfig = require('./tsconfig.json').compilerOptions;

var targetDir = './dist/public';

var paths = {
    index : ['./src/index.html'],
    scripts : ['./src/**/*.ts']
}

gulp.task('ts', ['clean'], function () {
    return gulp.src(paths.scripts)
        .pipe(sourcemaps.init())
        .pipe(ts(tsConfig))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(targetDir));
});

gulp.task('copy-index', ['clean'], function () {
    return gulp.src(paths.index)
        .pipe(gulp.dest(targetDir));
});

gulp.task('copy-templates', ['clean'], function() {
    return gulp.src('./src/**/*.html')
        .pipe(gulp.dest(targetDir));
});

gulp.task('copy-modules', ['clean'], function (cb) {
    var dependencies = require('./package.json').dependencies;

    for(var module in dependencies){
        gulp.src('./node_modules/' + module + '/**')
            .pipe(gulp.dest(targetDir + "/node_modules/" + module))
    }

    cb();
});

gulp.task("tslint", function () {
    return gulp.src(paths.scripts)
        .pipe(tslint())
        .pipe(tslint.report("verbose"));
});

gulp.task('clean', function () {
    return del([
         targetDir
       ], {
        force: true
    });
});

gulp.task('watch', function() {
    watch(paths.scripts, batch(function (events, cb) {
       gulp.start('ts', cb); 
    }));
});

gulp.task('check', ['tslint']);

gulp.task('build', ['ts', 'copy-index', 'copy-modules', 'copy-templates']);

gulp.task('default', ['build']);
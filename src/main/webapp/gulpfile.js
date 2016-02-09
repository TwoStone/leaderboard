var gulp = require('gulp');
var ts = require('gulp-typescript');
var del = require('del');
var sourcemaps = require('gulp-sourcemaps');

var paths = {
    index : ['./index.html'],
    scripts : ['app/**/*.ts']
}

var tsConfig = require('./tsconfig.json').compilerOptions;

var targetDir = '../resources/public/';

gulp.task('ts', ['clean'], function () {
    return gulp.src(paths.scripts)
        .pipe(sourcemaps.init())
        .pipe(ts(tsConfig))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(targetDir + 'app'));
});

gulp.task('copy-index', ['clean'], function () {
    return gulp.src(paths.index)
        .pipe(gulp.dest(targetDir));
});

gulp.task('copy-templates', ['clean'], function() {
    return gulp.src('app/**/*.html')
        .pipe(gulp.dest(targetDir + 'app'));
});

gulp.task('copy-modules', ['clean'], function (cb) {
    var dependencies = require('./package.json').dependencies;

    for(var module in dependencies){
        gulp.src('./node_modules/' + module + '/**')
            .pipe(gulp.dest(targetDir + "node_modules/" + module))
    }

    cb();
})

gulp.task('clean', function () {
    return del([
         targetDir
       ], {
        force: true
    });
});

gulp.task('build', ['ts', 'copy-index', 'copy-modules', 'copy-templates'], function () {

});

gulp.task('default', function() {
  // place code for your default task here
});
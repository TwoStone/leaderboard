var path = require('path'),
    cssMinify = require('gulp-minify-css'),
    less = require('gulp-less'),
    clean = require('gulp-clean'),
    flatten = require('gulp-flatten'),
    debug = require('gulp-debug'),
    gulp = require('gulp'),
    Builder = require('jspm').Builder;

var paths = {
    baseUrl: path.join(__dirname, 'src'),
    bowerLibs: ['src/lib/**', '!src/lib/*/test/*'],
    css: {
        files: ['src/css/*.css']
    },
    less: ['src/less/*'],
    assets: ["src/cache.manifest", "src/index.html"],
    images: ["src/img/*"],
    fonts: ['src/fonts/*', 'src/lib/**/fonts/*'],
    destination: './dist'
};

// Optimize application CSS files and copy to "dist" folder
gulp.task('optimize-and-copy-css', function() {
    return gulp.src(paths.css.files)
        .pipe(cssMinify())
        .pipe(gulp.dest(paths.destination + '/css'));
});

// Optimize application JavaScript files and copy to "dist" folder
gulp.task('optimize-and-copy-js', function(cb) {
    var builder = new Builder();
    builder.loadConfig('./src/config.js')
        .then(function() {            
            builder.bundle('app/app', paths.destination + '/app/app.js', { minify: true, sourceMaps: true });
            cb();
        })
        .catch(function(err) {
            cb(err);
        });
});

// Copy jspm-managed JavaScript dependencies to "dist" folder
gulp.task('copy-lib', function() {
    return gulp.src(paths.bowerLibs)
        .pipe(gulp.dest(paths.destination + '/lib'));
});

gulp.task('copy-images', function() {
    return gulp.src(paths.images)
        .pipe(gulp.dest(paths.destination + '/img'));
});

gulp.task('copy-assets', function() {
    return gulp.src(paths.assets)
        .pipe(gulp.dest(paths.destination))
});

gulp.task('copy-fonts', function () {
    return gulp.src(paths.fonts)
        .pipe(flatten())
        .pipe(gulp.dest(paths.destination + '/fonts'));
});

gulp.task('less', function () {
    return gulp.src(paths.less)
        .pipe(less())
        .pipe(cssMinify({noRebase: true}))
        .pipe(gulp.dest(paths.destination + '/css'));
});

gulp.task('clean', function () {
    return gulp.src(paths.destination, {read: false})
        .pipe(clean({force: true}));
});

gulp.task('build', ['optimize-and-copy-css', 'optimize-and-copy-js', 'copy-lib',
    'copy-images', 'less', 'copy-assets', 'copy-fonts'], function(){});
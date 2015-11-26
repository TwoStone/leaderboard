var path = require('path'),
    cssMinify = require('gulp-minify-css'),
    less = require('gulp-less'),
    clean = require('gulp-clean'),
    flatten = require('gulp-flatten'),
    debug = require('gulp-debug'),
    jade = require('gulp-jade'),
    connect = require('gulp-connect'),
    concat = require('gulp-concat'),
    watch = require('gulp-watch'),
    gulp = require('gulp'),
    Builder = require('jspm').Builder;

var paths = {
    baseUrl: path.join(__dirname, 'src'),
    bowerLibs: ['src/lib/**', '!src/lib/*/test/*'],
    css: ['src/css/*.css', '.tmp/**/*.css'],
    less: ['src/less/*.less'],
    assets: ['src/cache.manifest', 'src/index.html'],
    images: ['src/img/*'],
    fonts: ['src/fonts/*', 'src/lib/**/fonts/*'],
    jade: ['src/**/*.jade'],
    html: ['.tmp/**/*.html'],
    destination: './dist'
};

// Optimize application CSS files and copy to 'dist' folder
gulp.task('optimize-and-copy-css', ['less'], function() {
    return gulp.src(paths.css)
        .pipe(cssMinify())
        .pipe(concat('app.min.css'))
        .pipe(gulp.dest(paths.destination + '/css'));
});

// Optimize application JavaScript files and copy to 'dist' folder
gulp.task('optimize-and-copy-js', function(cb) {
    var builder = new Builder();
    builder.loadConfig('./src/config.js')
        .then(function() {  
            builder.buildStatic('app/app', paths.destination + '/app.min.js', { 
                minify: true, 
                sourceMaps: true 
            });
            cb();
        })
        .catch(function(err) {
            cb(err);
        });
});

gulp.task('optimize-and-copy-html', ['jade:prod'], function() {
    return gulp.src(paths.html)
        .pipe(gulp.dest(paths.destination));
});

// Copy jspm-managed JavaScript dependencies to 'dist' folder
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

gulp.task('jade:dev', function () {
    return gulp.src(paths.jade)
        .pipe(jade({
            pretty: true,
            locals: {
                production: false
            }
        }))
        .pipe(gulp.dest('.tmp'));
});

gulp.task('jade:prod', function () {
    return gulp.src(paths.jade)
        .pipe(jade({
            locals: {
                production: true
            }
         }))
        .pipe(gulp.dest('.tmp'));
});

gulp.task('less', function () {
    return gulp.src(paths.less)
        .pipe(less())
        .pipe(gulp.dest('.tmp/css'));
});

gulp.task('clean', function () {
    return gulp.src([paths.destination, '.tmp'], {read: false})
        .pipe(clean({force: true}));
});

// Development Tasks
gulp.task('webserver', function () {
	connect.server({
		port: 10000,
		root: ['src', '.tmp'],
        livereload: true
	})
});

gulp.task('watch', function () {
	gulp.watch(paths.less, ['less']);
    gulp.watch(paths.jade, ['jade:dev']);
});

gulp.task('livereload', function () {
    gulp.src(['.tmp/*.{css,html}', ,'src/*.js'])
        .pipe(watch(['.tmp/*.{css,html}', 'src/*.js']))
        .pipe(connect.reload());
});

gulp.task('runDist', ['build'], function () {
    connect.server({
        port: 11000,
        root: ['dist'],
        livereload: true
    });
});

gulp.task('develop', ['less', 'jade:dev', 'webserver', 'watch', 'livereload']);

gulp.task('build', [
    'optimize-and-copy-css', 
    'optimize-and-copy-js', 
    'optimize-and-copy-html',
    'copy-lib',
    'copy-images', 
    'copy-assets', 
    'copy-fonts']);
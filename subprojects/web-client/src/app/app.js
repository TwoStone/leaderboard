import 'bootstrap';
import angular from 'angular';
import $ from 'jquery';

import mainModule from './main/module';

var appModule = 'leaderboard.app'

class AppController {
	constructor() {
		this.title = "Leaderboard";
	}
}

angular.module(appModule, [mainModule]).
	controller('appController', AppController);

angular.element(document).ready(function () {
	angular.bootstrap(document, [appModule])
});

export default appModule;
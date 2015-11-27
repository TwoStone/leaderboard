import angular from 'angular';

import MainController from './controller'

import mainTemplate from './main.html!ng-template'

var moduleName = 'leaderboard.core'


angular.module(moduleName, [])
	.controller('mainController', MainController)
	.directive('main', function () {
		return {
			templateUrl: mainTemplate.templateUrl,
			controller: 'mainController',
			controllerAs: 'main'
		}
	});


export default moduleName;
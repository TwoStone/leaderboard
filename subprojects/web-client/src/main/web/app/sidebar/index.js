import angular from 'angular';

import sidebarTemplate from './sidebar.html!ng-template';
import SidebarController from './sidebar-controller';
import SidebarService from './sidebar-service';

var moduleName = 'leaderboard.sidebar'
	
var module = angular.module(moduleName, []);

module.controller('SidebarController', SidebarController);

module.directive('sidebar', function () {
	return {
		controller: 'SidebarController',
		controllerAs: 'sidebarCtrl',
		templateUrl: sidebarTemplate.templateUrl
	}
});

module.factory('sidebar', SidebarService.factory);

export default moduleName;	
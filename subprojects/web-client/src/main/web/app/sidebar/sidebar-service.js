class SidebarService {
	
	constructor($rootScope) {
		this.$rootScope = $rootScope;
	}
	
	static factory($rootScope) {
		return new SidebarService($rootScope);
	}
}

SidebarService.factory.$inject = ['$rootScope'];

export default SidebarService;

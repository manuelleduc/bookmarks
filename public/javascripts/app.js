var phonecatApp = angular.module('bookmarkApp', [ 'ngRoute',
		'bookmarkControllers' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/bookmarks', {
		templateUrl : 'partials/list-bookmarks',
		controller : 'ListBookmarkCtrl'
	}).when('/create-bookmark', {
		templateUrl : 'partials/create-bookmark',
		controller : 'CreateBookmarkCtrl'
	}).otherwise({
		redirectTo : '/bookmarks'
	});
}]);

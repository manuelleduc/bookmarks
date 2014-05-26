var phonecatApp = angular.module('bookmarkApp', [ 'ngRoute',
		'bookmarkControllers' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/bookmarks', {
		templateUrl : jsRoutes.controllers.BookmarkPartialsController.index().url,
		controller : 'ListBookmarkCtrl'
	}).when('/create-bookmark', {
		templateUrl : jsRoutes.controllers.BookmarkPartialsController.create().url,
		controller : 'CreateBookmarkCtrl'
	}).otherwise({
		redirectTo : '/bookmarks'
	});
}]);

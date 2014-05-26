angular.module('bookmarkResource', ['ngResource']).
	factory('BookmarkResource', function ($resource) {
		return $resource(jsRoutes.controllers.BookmarkApiController.list().url, {});
	});
angular.module('bookmarkResource', ['ngResource']).
	factory('BookmarkResource', function ($resource) {
		return $resource("/api/bookmarks", {});
	});
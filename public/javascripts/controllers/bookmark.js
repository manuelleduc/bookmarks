var bookmarkControllers = angular.module('bookmarkControllers',
		[ 'bookmarkResource' ]);

bookmarkControllers.controller('ListBookmarkCtrl', [ '$scope', '$http',
		'BookmarkResource', function($scope, $http, BookmarkResource) {
			BookmarkResource.query(function(data) {
				$scope.bookmarks = data;
			});
		} ]);

bookmarkControllers.controller('CreateBookmarkCtrl', [ '$scope',
		'BookmarkResource', '$location',
		function($scope, BookmarkResource, $location) {
			$scope.errorList = [];

			$scope.saveForm = function() {
				BookmarkResource.save($scope.bookmark, function() {
					$location.path('/');
				}, function errorHandler(errorResponse) {
					$scope.errorList = errorResponse.data;
				});
			}
		} ]);
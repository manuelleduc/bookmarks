define([ 'underscore', 'backbone', 'models/bookmark' ], function(_, Backbone,
		Bookmark) {
	var BookmarksCollection = Backbone.Collection.extend({
		model: Bookmark,
		url: '/bookmarks'
	});
	
	return new BookmarksCollection();
});
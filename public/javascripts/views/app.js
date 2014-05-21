define([ 'jquery', 'underscore', 'backbone', 'collections/bookmarks',
		'views/bookmark'], function($, _,
		Backbone, Bookmarks, BookmarkView) {
	var AppView = Backbone.View.extend({
		el : '#bookmarkapp',
		template : _.template("OK"),
		initialize : function() {
			this.listenTo(Bookmarks, 'reset', this.addAll);
			Bookmarks.fetch({
				reset : true
			});
		},
		addAll : function() {
			this.$('#bookmarks-list').html('');
			Bookmarks.each(this.addOne, this);
		},
		addOne : function(bookmark) {
			var view = new BookmarkView({
				model : bookmark
			});
			$('#bookmarks-list').append(view.render().el);
		},
	});

	return AppView;
});
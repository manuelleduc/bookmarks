define([ 'jquery', 'underscore', 'backbone', 'text!templates/bookmark.html' ],
		function($, _, Backbone, bookmarkTemplate) {
	var BookmarkView = Backbone.View.extend({
		tagName: 'div',
		className: 'panel panel-default',
		template: _.template(bookmarkTemplate),
		render: function() {
			console.log(this.el, this.$el);
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		}
		
	});
	
	return BookmarkView;
});
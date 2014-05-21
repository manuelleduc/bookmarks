define(['underscore', 'backbone'], function(_, Backbone) {
	var Bookmark = Backbone.Model.extend({
		defaults: {
			title: '',
			link: '',
			comment: '',
			tags: []
		}
	});
	
	return Bookmark;
});
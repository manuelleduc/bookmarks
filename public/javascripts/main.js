require.config({
	/*
	 * The shim config allows us to configuredependencies for scripts that do
	 * not call define() to register a module
	 */
	shim : {
		underscore : {
			exports : '_'
		},
		jquery : {
			exports : '$'
		},
		backbone : {
			deps : [ 'underscore', 'jquery' ],
			exports : 'Backbone'
		}
	},
	paths : {
		jquery : './libs/jquery',
		underscore : './libs/underscore',
		backbone : './libs/backbone',
		text: './libs/require-text'
	}
});

require([ 'backbone', 'views/app', 'routers/router' ], function(Backbone,
		AppView, Workspace) {
	new Workspace();
	Backbone.history.start();
	new AppView();
});

// require([ 'backbone', 'views/app', 'routers/router' ], function(Backbone,
// AppView, Workspace) {
/* jshint nonew:false */
// Initialize routing and start Backbone.history()
// new Workspace();
// Backbone.history.start();
// Initialize the application view
// new AppView();
// });

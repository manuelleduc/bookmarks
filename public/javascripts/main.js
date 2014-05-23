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
		jquery : './libs/jquery/dist/jquery',
		underscore : './libs/underscore/underscore',
		backbone : './libs/backbone/backbone',
		text: './libs/requirejs-text/text'
	}
});

require([ 'backbone', 'views/app', 'routers/router' ], function(Backbone,
		AppView, Workspace) {
	new Workspace();
	Backbone.history.start();
	new AppView();
});

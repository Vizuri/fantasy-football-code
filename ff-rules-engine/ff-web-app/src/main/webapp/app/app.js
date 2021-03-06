var ffApp = angular.module('ffApp', ['ui.bootstrap', 'ngRoute', 'ngAnimate', 'ownerController', 'leagueController', 'teamController' ]);

ffApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/owners', {
		templateUrl: 'app/partials/ownerList.html',
		controller: 'ListController'
	}).
	when('/owner_summary', {
		templateUrl: 'app/partials/ownerDetails.html',
		controller: 'DetailsController'
	}).
	when('/edit_league_details', {
		templateUrl: 'app/partials/edit_league.html',
		controller: 'EditLeagueController'
	}).
	when('/join_league', {
		templateUrl: 'app/partials/join_league.html',
		controller: 'JoinLeagueController'
	}).
	when('/create_league', {
		templateUrl: 'app/partials/create_league.html',
		controller: 'CreateLeagueController'
	}).
	when('/edit_team_details', {
		templateUrl: 'app/partials/edit_team.html',
		controller: 'EditTeamController'
	}).
	otherwise({
		redirectTo: '/owners'
	});
}]);

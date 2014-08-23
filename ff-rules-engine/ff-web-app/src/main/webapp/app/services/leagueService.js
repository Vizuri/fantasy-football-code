var module = angular.module('leagueService', []);

module.factory('League', function(){
	var newLeague = {};
	var allLeagues = {};
	return {
		getLeague: function(){
			return newLeague;
		},
		setLeague: function(league){
			newLeague = league;
		},
		getAllLeagues: function(){
			return allLeagues;
		},
		setAllLeagues: function(leagues){
			allLeagues = leagues;
		}
	};
});
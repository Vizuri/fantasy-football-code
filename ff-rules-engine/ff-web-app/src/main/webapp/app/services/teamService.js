var module = angular.module('teamService', []);

module.factory('Team', function(){
	var newTeam = {};
	return {
		getTeam : function(){
			return newTeam;
		},
		setTeam : function(team){
			newTeam = team;
		}
	};
});
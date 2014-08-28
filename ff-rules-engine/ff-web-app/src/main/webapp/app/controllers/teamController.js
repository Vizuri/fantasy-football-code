var teamModule = angular.module('teamController', ['teamService', 'leagueService']);

teamModule.controller('EditTeamController', ['$scope', '$http', 'Team', 'League', function($scope, $http, Team, League){
	$scope.newTeam = Team.getTeam();
	console.log("About to query on team: "+angular.toJson($scope.newTeam));
	$http.get('rest/teams/'+ $scope.newTeam.id).
	success(function(data, status, headers, config){
		console.log("Status: "+status);
		$scope.status = status;
		$scope.teamSummary = data;
		var league = League.getLeague();		
		$scope.changeWeek(league.currentWeek);
		console.log("The current active week on page load is: "+league.currentWeek);
	}).
	error(function(data, status, headers, config){
		console.log("Error returned: "+data);
		$scope.data = data || "Request failed";
		$scope.status = status;
	});
	
	$scope.changeWeek = function(value) {
		var i = Number(value);
		$scope.currentWeek = i;
		console.log("The current active week is: "+$scope.currentWeek);
		while (i > 0) {
			if ($scope.teamSummary.teamRosterMap[String(i)]) {
				$scope.teamRosters = $scope.teamSummary.teamRosterMap[String(i)];
				break;
			}
			i--;
		}
	};
}]);
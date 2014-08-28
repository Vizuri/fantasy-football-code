var teamModule = angular.module('teamController', ['teamService']);

teamModule.controller('EditTeamController', ['$scope', '$http', 'Team', function($scope, $http, Team){
	$scope.newTeam = Team.getTeam();
	console.log("About to query on team: "+angular.toJson($scope.newTeam));
	$http.get('rest/teams/'+ $scope.newTeam.id).
	success(function(data, status, headers, config){
		console.log("Status: "+status);
		$scope.status = status;
		$scope.teamSummary = data;
	}).
	error(function(data, status, headers, config){
		console.log("Error returned: "+data);
		$scope.data = data || "Request failed";
		$scope.status = status;
	});
	
	$scope.changeWeek = function(value) {
		var i = Number(value);
		$scope.currentWeek = i;
		while (i > 0) {
			if ($scope.teamSummary.teamRosterMap[String(i)]) {
				$scope.teamRosters = $scope.teamSummary.teamRosterMap[String(i)];
				break;
			}
			i--;
		}
	};
}]);
var teamModule = angular.module('teamController', ['teamService']);

teamModule.controller('EditTeamController', ['$scope', '$http', 'Team', function($scope, $http, Team){
	$scope.newTeam = Team.getTeam();
	$http.get('rest/teams/'+ $scope.newTeam.id).
	success(function(data, status, headers, config){
		console.log("Status: "+status);
		$scope.status = status;
		$scope.teamRosters = data;
	}).
	error(function(data, status, headers, config){
		console.log("Error returned: "+data);
		$scope.data = data || "Request failed";
		$scope.status = status;
	});
}]);
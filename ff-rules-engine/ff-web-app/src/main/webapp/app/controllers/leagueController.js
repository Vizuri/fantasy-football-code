var leagueModule = angular.module('leagueController', ['leagueService']);

leagueModule.controller('EditLeagueController', ['$scope', '$http', 'League', function($scope, $http, League){
	$scope.newLeague = League.getLeague();
	console.log("Before update league is "+$scope.newLeague.name+", "+$scope.newLeague.currentWeek);
	$scope.updateLeague = function(league) {
		$scope.newLeague = angular.copy(league);
		console.log("After update league is " + $scope.newLeague.name
				+ ", " + $scope.newLeague.currentWeek);
		// Call the update operation..

		$http({
			method: 'PUT',
			url: 'rest/leagues',
			data: JSON.stringify($scope.newLeague),
			headers: { 'Content-Type' : 'application/JSON' }
		}).				
	      success(function(data, status, headers, config) {
				console.log("Returned league after update: "
						+ data.name);
				console.log("Status: " + status);
				$scope.status = status;
				$scope.league = data;
		}).
		error(function(data, status, headers, config) {
			console.log("Error returned: " + data);
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};
}]);

leagueModule.controller('JoinLeagueController', ['$scope', '$http', 'League', function($scope, $http, League){
	$scope.leagues = League.getAllLeagues();
	$scope.leagueOrder = "name";
}]);
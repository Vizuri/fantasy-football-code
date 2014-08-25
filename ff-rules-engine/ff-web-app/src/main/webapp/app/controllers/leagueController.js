var leagueModule = angular.module('leagueController', ['leagueService', 'ownerService']);

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

leagueModule.controller('JoinLeagueController', ['$scope', '$http', 'Owner', 'League', function($scope, $http, Owner, League){
	$scope.leagues = League.getAllLeagues();
	$scope.leagueOrder = "name";
	
	$scope.join = function(league){
		console.log("The passed league is: "+league);
		//toJson() to remove the '$$hashKey' key from league object that angular adds with ng-repeat directive
		$scope.league = angular.toJson(league);
		$scope.league.commissioner = Owner.getOwner();
		
		$scope.teamJoined = {
				"leagueId" : league.id,
				"owner" :  Owner.getOwner()
		};
		
		$http({
			method: 'POST',
			url: 'rest/leagues/joinLeague',
			data: JSON.stringify($scope.teamJoined),
			headers: { 'Content-Type' : 'application/json' }
		}).				
	      success(function(data, status, headers, config) {
				console.log("Returned league after joining league: "
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

leagueModule.controller('CreateLeagueController', ['$scope', '$http', 'Owner', function($scope, $http, Owner){
	$scope.createLeague = function(newLeague){
		$scope.newLeague = newLeague;
		$scope.newLeague.commissioner = Owner.getOwner();
		$http({
			method: 'POST',
			url: 'rest/leagues/createLeague',
			data: JSON.stringify($scope.newLeague),
			headers: { 'Content-Type' : 'application/json' }
		}).				
	      success(function(data, status, headers, config) {
				console.log("Returned league after creating one: "
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




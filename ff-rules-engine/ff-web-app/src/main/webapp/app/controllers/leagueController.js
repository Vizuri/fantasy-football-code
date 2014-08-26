var leagueModule = angular.module('leagueController', ['leagueService', 'ownerService']);

leagueModule.controller('EditLeagueController', ['$scope', '$http', 'League', 'Owner', function($scope, $http, League, Owner){
	$scope.newLeague = League.getLeague();
	console.log("Before change, the league was "+angular.toJson($scope.newLeague));
	$scope.updateLeague = function(league) {
		$scope.newLeague = angular.copy(league);
		console.log("The league is now changed to " + angular.toJson($scope.newLeague));
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
				var league = data;
				console.log("Updated league is "+angular.toJson(league));
				//Updating the league inside ownerDetails using Owner Service
				var ownerDetails = Owner.getDetails();
				for(var count = 0; count < ownerDetails.leagues.length; count++){
					if(league.id === ownerDetails.leagues[count].id){
						ownerDetails.leagues[count] = league;
						break;
					}
				}
				
				Owner.setDetails(ownerDetails);
				alert("League Updated Successfully...");
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
	$scope.orderby = 'name';
	$scope.reverse = false;
	
	$scope.join = function(league){
		console.log("The passed league is: "+league);
		//The toJson() to remove the '$$hashKey' key from league object that angular adds with ng-repeat directive
		//Source: https://docs.angularjs.org/api/ng/function/angular.toJson
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
				$scope.team = data;
				var ownerDetails = Owner.getDetails();
				//Check the current team size from the owner service before adding one.
				console.log("BEFORE::teams length is "+ownerDetails.teams.length);
				//Push the new team that just got created into the original list
				ownerDetails.teams.push($scope.team);
				//Update the service with the latest leagues
				Owner.setDetails(ownerDetails);
				//Check the current team size from the service after adding one.
				console.log("AFTER::teams length is "+ownerDetails.teams.length);
				alert("League Joined Successfully..");
		}).
		error(function(data, status, headers, config) {
			console.log("Error returned: " + data);
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};
	$scope.setOrder = function (orderby) {
        if (orderby === $scope.orderby)
        {
            $scope.reverse = !$scope.reverse;
        }
        $scope.orderby = orderby;
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
				var ownerDetails = Owner.getDetails();
				//Check the current league size from the owner service before adding one.
				console.log("BEFORE::leagues length is "+ownerDetails.leagues.length); 
				//Push the new league that just got created into the original list
				ownerDetails.leagues.push($scope.league);
				//Update the service with the latest leagues
				Owner.setDetails(ownerDetails);
				//Check the current league size from the service after adding one.
				console.log("AFTER::leagues length is "+ownerDetails.leagues.length); 
				alert("League Created Successfully..");
		}).
		error(function(data, status, headers, config) {
			console.log("Error returned: " + data);
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};

}]);




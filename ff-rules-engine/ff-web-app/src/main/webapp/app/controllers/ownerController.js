var ownerModule = angular.module('ownerController', ['ngAnimate', 'ngDropdowns', 'ownerService', 'leagueService', 'teamService']);

ownerModule.controller('ListController', ['$scope', '$http', '$location', 'Owner', function($scope, $http, $location, Owner) {
	$http.get('rest/owners').success(function(data, status) {
		$scope.select = "Pick an owner..";
		$scope.owners = data;
		$scope.ownerOrder = "name";
		$scope.selectedOwner = {};
		$scope.status = status;
	}).
	error(function(data, status, headers, config){
		console.log("Error returned: "+data);
		$scope.data = data || "Request failed";
		$scope.status = status;
	});
	$scope.getOwnerSummary = function(){
		console.log("The selected owner is "+$scope.selectedOwner.name);
		$http.get('rest/owners/'+ $scope.selectedOwner.id).
		success(function(data, status, headers, config){
			console.log("Returned data after success: "+data.owner.name);
			console.log("Status: "+status);
			$scope.status = status;
			$scope.ownerDetails = data;
			//To push the ownerDetails to DetailsController
			Owner.setDetails($scope.ownerDetails);
			$location.path("/owner_summary");
		}).
		error(function(data, status, headers, config){
			console.log("Error returned: "+data);
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
	};
}]);

ownerModule.controller('DetailsController', ['$scope', '$http', '$location', 'Owner', 'League', 'Team', function($scope, $http, $location, Owner, League, Team) {
	console.log("Got in the details..");
	$scope.orderby = 'name';
	$scope.reverse = false;
	$scope.ownerDetails = Owner.getDetails();
	//Get the leagues
	$scope.leagues = $scope.ownerDetails.leagues;
	console.log("The leagues size from Owner Service is: "+$scope.leagues.length);
	//Get the teams
	$scope.teams = $scope.ownerDetails.teams;
	console.log("BEFORE:::::The teams size on load is: "+$scope.teams.length);
	for(var i=0; i < $scope.teams.length; i++){
		for(var j=0; j < $scope.leagues.length; j++){
			if($scope.teams[i].leagueId === $scope.leagues[j].id){
				$scope.teams[i].leagueName = $scope.leagues[j].name;
			}
		}
	}
	console.log("AFTER:::::The teams size on load is: "+$scope.teams.length);
	$scope.joinLeague = function(owner){
		Owner.setOwner(owner);
		$http.get('rest/leagues').success(function(data, status) {
			$scope.leagues = data;
			$scope.status = status;
		}).
		error(function(data, status, headers, config){
			console.log("Error returned: "+data);
			$scope.data = data || "Request failed";
			$scope.status = status;
		});
		League.setAllLeagues($scope.leagues);
		$location.path("/join_league");
	};
	
	$scope.createNewLeague = function(owner){
		Owner.setOwner(owner);
		$location.path("/create_league");
	};
	
	$scope.editLeague = function(league){
		//Make a clone of the existing league for editing
		$scope.newLeague = angular.copy(league);
		League.setLeague($scope.newLeague);
		$location.path("/edit_league_details");
	};
	
	$scope.editTeam = function(team){
		//Make a clone of the existing team for editing
		$scope.newTeam = angular.copy(team);
		console.log($scope.newTeam);
		Team.setTeam($scope.newTeam);
		$location.path("/edit_team_details");
	};
	$scope.setOrder = function (orderby) {
        if (orderby === $scope.orderby)
        {
            $scope.reverse = !$scope.reverse;
        }
        $scope.orderby = orderby;
	 };
}]);
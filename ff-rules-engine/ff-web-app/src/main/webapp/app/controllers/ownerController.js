var ownerModule = angular.module('ownerController', ['ngAnimate', 'ngDropdowns', 'ownerService', 'leagueService']);

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

ownerModule.controller('DetailsController', ['$scope', '$http', '$location', 'Owner', 'League', function($scope, $http, $location, Owner, League) {
	console.log("Got in the details..");
	$scope.ownerDetails = Owner.getDetails();
	//Get the leagues
	$scope.leagues = $scope.ownerDetails.leagues;
	$scope.leagueOrder = "name";
	//Get the teams
	$scope.teams = $scope.ownerDetails.teams;
	$scope.teamOrder = "name";
	
	for(var i=0; i < $scope.teams.length; i++){
		for(var j=0; j < $scope.leagues.length; j++){
			if($scope.teams[i].leagueId === $scope.leagues[j].id){
				$scope.teams[i].leagueName = $scope.leagues[j].name;
			}
		}
	}
	
	$scope.joinLeague = function(){
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
	
	$scope.createLeague = function(){
		
	};
	
	$scope.editLeague = function(league){
		$scope.newLeague = angular.copy(league);
		//Pass the old league for editing
		League.setLeague($scope.newLeague);
		$location.path("/edit_league_details");
	};
}]);
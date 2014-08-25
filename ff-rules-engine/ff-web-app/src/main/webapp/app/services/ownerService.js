var module = angular.module('ownerService', []);

module.factory('Owner', function(){
	var details = {};
	var currentOwner = {};
	return {
		getDetails: function(){
			return details;
		},
		setDetails: function(ownerDetails){
			details = ownerDetails;
		},
		getOwner: function(){
			return currentOwner;
		},
		setOwner: function(owner){
			currentOwner = owner;
		}
	};
});

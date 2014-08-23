var module = angular.module('ownerService', []);

module.factory('Owner', function(){
	var details = {};
	return {
		getDetails: function(){
			return details;
		},
		setDetails: function(ownerDetails){
			details = ownerDetails;
		}
	};
});

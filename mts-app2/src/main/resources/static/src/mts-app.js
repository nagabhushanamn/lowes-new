/**
 * 
 */

(function() {

	angular.module('mts-app', [])
		.controller('TxrController', function($scope, $http) {
			$scope.txrRequest = {}
			$scope.doTxr = function() {
				$http.post('api/txr', $scope.txrRequest)
					.then(function(resp) {
						$scope.status = resp.data.message
						$scope.txrRequest = {}
					}, function(reason) {
						$scope.status = resp.data.message
					});
			}
		});

})();
angular.module('sentimentr-client', ['info','ngRoute']).controller(
		'sentiment',
		function($scope, $http) {

			function submit() {
				$http.post('sentiment/', $scope.text).success(
						function(data) {
							$scope.data = data;
						})
			}

			$scope.submit = function() {
				submit();
			};

			$scope.change = function() {
				$scope.data.sentiment = "";
			};

			$scope.init = function() {
				$scope.text = "";
			};

		});

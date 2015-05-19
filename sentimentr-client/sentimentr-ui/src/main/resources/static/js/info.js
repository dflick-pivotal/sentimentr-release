angular.module('info', ['ngResource']).
    factory('AppInfo', function ($resource) {
        return $resource('appinfo');
    }).
    controller('InfoController', function ($scope, AppInfo) {
    	$scope.info = AppInfo.get();
    });

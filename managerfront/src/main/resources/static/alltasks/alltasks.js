angular.module('taskmanager').controller('allTasksController', function ($scope, $http) {
    $scope.loadAllTasks = function () {
        $http({
            url: 'http://localhost:5555/core/tasks/',
            method: 'GET'
        }).then(function (response) {
            $scope.myTasks = response.data;
        });
    };

    $scope.loadMyTasks();
});
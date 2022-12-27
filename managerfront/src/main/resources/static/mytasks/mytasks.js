angular.module('taskmanager').controller('myTasksController', function ($scope, $http) {
    $scope.loadMyTasks = function () {
        $http({
            url: 'http://localhost:5555/core/tasks/my',
            method: 'GET'
        }).then(function (response) {
            $scope.myTasks = response.data;
        });
    };

    $scope.loadMyTasks();
});
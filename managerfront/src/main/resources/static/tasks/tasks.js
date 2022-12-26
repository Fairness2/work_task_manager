angular.module('taskmanager').controller('tasksController', function ($scope, $http) {
    $scope.loadAllTasks = function () {
        $http({
            url: 'http://localhost:5555/manager-core/tasks/all',
            method: 'GET'
        }).then(function (response) {
            $scope.allTasks = response.data;
        });
    };

    $scope.loadAllTasks();
});
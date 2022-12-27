angular.module('taskmanager').controller('allTasksController', function ($scope, $http) {

    $scope.loadTasks = function (pageIndex = 1) {
        $scope.tasksRequest.page = pageIndex;
        $http({
            url: 'http://localhost:5555/core/tasks',
            body: $scope.tasksRequest,
            method: 'GET'
        }).then(function (response) {
            $scope.allTasks = response.data;
            $scope.generatePagesList($scope.allTasks.total);
        });
    };

    $scope.generatePagesList = function (total) {
        out = [];
        for (let i = 0; i < total; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }
    $scope.loadTasks();
});
angular.module('taskmanager').controller('allTasksController', function ($scope, $http, $localStorage) {

    $scope.loadTasks = function (pageIndex = 1) {
        $scope.tasksRequest = {
            page: pageIndex,
            userId: $localStorage.workTaskUser.id
        };
        $http.get('http://localhost:5555/core/tasks', $scope.tasksRequest)
            .then(function (response) {
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
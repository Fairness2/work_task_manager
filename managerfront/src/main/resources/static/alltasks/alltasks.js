angular.module('taskmanager').controller('allTasksController', function ($scope, $http, $localStorage, $location) {

    $scope.loadTasks = function (pageIndex = 1) {

            $http({
                url: 'http://localhost:5555/core/tasks/get-all',
                method: 'GET',
                params: {
                    pageIndex: pageIndex
                }
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

    $scope.loadSingleTask = function (id) {
        $localStorage.currentTaskViewId = id;
        $location.path('/singletask');
    }

    $scope.loadTasks();
});
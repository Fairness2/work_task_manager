angular.module('taskmanager').controller('myTasksController', function ($scope, $http, $localStorage, $location) {
    $scope.loadMyTasks = function (pageIndex = 1) {
        $http({
            url: 'http://localhost:5555/core/tasks',
            method: 'GET',
            params: {
                pageIndex: pageIndex,
                userId: $localStorage.workTaskUser.id
            }
        }).then(function (response) {
            $scope.myTasks = response.data;
            $scope.generatePagesListMyTasks($scope.myTasks.total);
        });
    };
    $scope.generatePagesListMyTasks = function (total) {
        out = [];
        for (let i = 0; i < total; i++) {
            out.push(i + 1);
        }
        $scope.pagesListMyTasks = out;
    }
    $scope.loadMySingleTask = function (id) {
        $localStorage.currentTaskViewId = id;
        $location.path('/singletask');
    }
    $scope.loadMyTasks();
});
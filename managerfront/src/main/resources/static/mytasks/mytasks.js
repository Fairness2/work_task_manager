angular.module('taskmanager').controller('myTasksController', function ($scope, $http) {
    $scope.loadMyTasks = function () {
        $http({
            url: 'http://localhost:5555/core/tasks/my',
            method: 'GET'
        }).then(function (response) {
            $scope.myTasks = response.data;
        });
    };
    //TODO получение по DTO
    //     $scope.tasksRequest = {
    //         page: pageIndex,
    //         userId: $localStorage.workTaskUser.id
    //     };
    //     $http.get('http://localhost:5555/core/tasks', $scope.tasksRequest)
    //         .then(function (response) {
    //         $scope.allTasks = response.data;
    //         $scope.generatePagesList($scope.allTasks.total);
    //     });
    // };
    $scope.loadMyTasks();
});
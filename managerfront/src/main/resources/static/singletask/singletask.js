angular.module('taskmanager').controller('singleTaskController', function ($scope, $http, $localStorage) {

    $scope.loadSingleTask = function () {
            $http.get('http://localhost:5555/core/tasks/' + $localStorage.currentTaskViewId)
                .then(function (response) {
                $scope.singleTask = response.data;
            });
        };

    $scope.createNewComment = function () {

    }

    $scope.loadSingleTask();
});
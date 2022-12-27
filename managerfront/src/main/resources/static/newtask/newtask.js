angular.module('taskmanager').controller('newTaskController', function ($scope, $http) {
    $scope.createNewTask = function () {
        alert($scope.newTask.description)
        $http({
            url: 'http://localhost:5555/core/tasks',
            method: 'POST',
            body: $scope.newTask
        }).then(function (response) {

            });
    };

    $scope.loadUsers = function () {
        $http({
            url: 'http://localhost:5555/auth/users/find-all',
            method: 'GET'
        }).then(function (response) {
            $scope.users = response.data;
        });
    };

    $scope.isFieldsOk = function () {
        if (($scope.newTask.title !== undefined && $scope.newTask.description !== undefined) &&
            ($scope.newTask.title !== '' && $scope.newTask.description !== '') &&
            $scope.newTask.employerId > 0 && $scope.newTask.responsibleUserId > 0) {
            return true;
        } else {
            return false;
        }
    };
    $scope.loadUsers();
});
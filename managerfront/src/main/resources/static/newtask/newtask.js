angular.module('taskmanager').controller('newTaskController', function ($scope, $rootScope, $http) {

    $scope.createNewTask = function () {
        $scope.newTask.workingHours = ($scope.newTask.planEndDate - $scope.newTask.planStartDate) / 3600000;
        $http({
            url: 'http://localhost:5555/core/tasks',
            body: $scope.newTask,
            method: 'POST'
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

    $rootScope.isFieldsNewTaskOk = function () {
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
angular.module('taskmanager').controller('newTaskController', function ($scope, $rootScope, $http) {
    $scope.newTask = {
        title: "",
        description: "",
        employerId: 0,
        authorId: 2,
        responsibleUserId: 0,
        workingHours: 0,
        planStartDate: "2022-12-28T04:49:00.000Z",
        planEndDate: "2022-12-29T04:49:00.000Z"
    };

    $scope.createNewTask = function () {
        $scope.newTask.workingHours = ($scope.newTask.planEndDate - $scope.newTask.planStartDate) / 3600000;
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
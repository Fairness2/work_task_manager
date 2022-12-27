angular.module('taskmanager').controller('newTaskController', function ($scope, $http, $localStorage, $location) {

    $scope.createNewTask = function () {
        $scope.newTask.workingHours = ($scope.newTask.planEndDate - $scope.newTask.planStartDate) / 3600000;
        $scope.newTask.authorId = $localStorage.workTaskUser.id;
        $http.post('http://localhost:5555/core/tasks', $scope.newTask)
            .then(function (response) {
                $location.path('/alltasks');
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

    //TODO доделать проверки заполненности всех полей
    // $scope.isFieldsNewTaskOk = function () {
    //     if (($scope.newTask.title !== '' && $scope.newTask.description !== '') &&
    //         $scope.newTask.employerId > 0 && $scope.newTask.responsibleUserId > 0) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // };
    $scope.loadUsers();
});
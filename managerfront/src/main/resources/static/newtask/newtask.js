angular.module('taskmanager').controller('newTaskController', function ($scope, $http) {
    $scope.createNewTask = function () {
        $http.post('http://localhost:5555/auth/register', $scope.newTask)
            .then(function (response) {
                $location.path('/');
            });
    }

    $rootScope.isFieldsTaskOk = function () {
        if ($scope.newTask.title !== '' && $scope.newTask.description !== '' && $scope.newTask.responsibleUserId !== '' &&
            $scope.newTask.workingHours !== '' && $scope.newTask.planStartDate !=='' && $scope.newTask.planEndDate !=='') {
            return true;
        } else {
            return false;
        }
    };
});
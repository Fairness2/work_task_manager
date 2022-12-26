
angular.module('taskmanager').controller('registerController', function ($scope, $rootScope, $http, $location) {

    $scope.registerNewUser = function () {
        $http.post('http://localhost:5555/auth/register', $scope.newUser)
            .then(function (response) {
                $location.path('/');
            });
    }

    $rootScope.isFieldsOk = function () {
        if (($scope.newUser.password !== '' && $scope.newUser.confirmPassword !== '' && $scope.newUser.username !== '') &&
            ($scope.newUser.password === $scope.newUser.confirmPassword)) {
            return true;
        } else {
            return false;
        }
    };

});
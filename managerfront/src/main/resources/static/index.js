(function () {
    angular
        .module('taskmanager', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/register', {
                templateUrl: 'register/register.html',
                controller: 'registerController'
            })
            .when('/alltasks', {
                templateUrl: 'alltasks/alltasks.html',
                controller: 'allTasksController'
            })
            .when('/mytasks', {
                templateUrl: 'mytasks/mytasks.html',
                controller: 'myTasksController'
            })
            .when('/newtask', {
                templateUrl: 'newtask/newtask.html',
                controller: 'newTaskController'
            })
            .when('/singletask', {
                templateUrl: 'singletask/singletask.html',
                controller: 'singleTaskController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.workTaskUser) {
            try {
                let jwt = $localStorage.workTaskUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.workTaskUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {}
        }

        if ($localStorage.workTaskUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.workTaskUser.token;
        }
    }
})();

angular.module('taskmanager').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.workTaskUser = {username: $scope.user.username,
                                                  token: response.data.token, id: response.data.id};
                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.workTaskUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.workTaskUser) {
            return true;
        } else {
            return false;
        }
    };
});
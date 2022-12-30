angular.module('taskmanager').controller('singleTaskController', function ($scope, $http, $localStorage) {

    $scope.loadSingleTask= function () {
            $http.get('http://localhost:5555/core/tasks/' + $localStorage.currentTaskViewId)
                .then(function (response) {
                $scope.singleTask = response.data;
                $scope.loadAllComments();
            });
        };

    $scope.createNewComment = function () {
        $scope.newComment.authorId = $scope.singleTask.id;
        $scope.newComment.type = 'comment';
        $scope.newComment.taskId = $scope.singleTask.id;
        $scope.sendComment();
    }
    $scope.sendComment = function () {
        $http.post('http://localhost:5555/core/comments', $scope.newComment)
            .then(function (response) {
                $scope.loadAllComments();
                $scope.newComment.text = '';
            });
    };

    $scope.loadAllComments = function () {
        $http({
            url: 'http://localhost:5555/core/comments',
            method: 'GET',
            params: {
                page: 1,
                taskId: $scope.singleTask.id
            }
        })
        .then(function (response) {
                $scope.allComments = response.data;
                $scope.parseAllCommentsData($scope.allComments.comments);
            });
    }

    $scope.parseAllCommentsData = function (data) {
        let s = '';
        for (const item of data) {
            s += '------------------------------------------------------------\nПользователь: ' + item.author.username + ' | Дата отправки: ' + item.createdAt + '\n------------------------------------------------------------\n' + item.text + '\n\n';
        }
        $scope.commentsText = s;
    };


    $scope.loadSingleTask();
});
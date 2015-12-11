/**
 * Created by jcchen on 15-11-28.
 */

angular.module("ngLoginApp", ['base64'])
    .config(function($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = "XMLHttpRequest";
    })
    .controller("LoginController", function($scope, $http, $log, $base64) {
        $scope.user = {};
        $scope.login = function() {
            $log.log($scope.user.username);
            $log.log($scope.user.password);

            var headers = {};
            headers['Content-Type'] = 'application/x-www-form-urlencoded';
            $log.log(headers);

            var data = {
                "username": $scope.user.username,
                "password": $scope.user.password
            };

            $http({
                method: "POST",
                url: "/login",
                data: $.param(data),
                headers: headers
            }).then(function(response) {
                $log.log('success');
                $log.log(response);
                success(response);
            }, function(response) {
                $log.log('failed');
                $log.log(response);
                failed(response);
            });
        }

        $scope.logout = function() {
            $http.post("/logout", {}).then(function(response) {
                $log.log("logout success!!");
                success(response);
            }, function(response) {
                $log.log("logout failed!!");
                failed(response);
            });
        }

        $scope.apiLogin = function() {
            $http.defaults.headers.common = {"Access-Control-Request-Headers": "accept, origin, authorization"}; //you probably don't need this line.  This lets me connect to my server on a different domain
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $base64.encode($scope.user.username + ':' + $scope.user.password);
            $http({
                method: "GET",
                url: "/api/users"
            }).then(function(response) {
                $log.log('success');
                $log.log(response);
                success(response);
            }, function(response) {
                $log.log('failed');
                $log.log(response);
                failed(response);
            });
        }

        var success = function(rsp) {
            $scope.msg = "login success! " + JSON.stringify(rsp.data);
        }
        var failed = function(rsp) {
            var data = rsp.data;
            $scope.msg = "login failed! " + "["+data.status+"]["+data.error+"]["+data.message+"]";
        }

    });
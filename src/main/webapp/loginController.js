app.controller("loginController", function($scope,$http,$rootScope,$state){
 $scope.loginUser=function(login){

            $http({
                          "url" : "/login/authenticate/",
                          "method" : "GET",
                          "params" : {'username':login.username,'password':login.password}
                          }).
                          then(function(response){
                            console.log(response.data);
                            $rootScope.authenticated=true;
                            $state.go('home');

                          },
                          function(error) {
                          $rootScope.authenticated=false;
                              console.log(error.data);
                          });
    }
});

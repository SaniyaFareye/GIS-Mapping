app.controller("signoutController", function($scope,$http,$state){
    $scope.logout=function(){
    $http.delete("/login/logout").then(function(response){
           $state.go('login');
    });}
});
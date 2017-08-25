app.controller("userController", function($scope,$window,$http,$timeout){

    $scope.roleList=["Data Analyst","Approver"];


    $scope.addUser=function(user){

         var url="/user/add";
         $http.post(url,user).then(function(response){
         $window.alert(response.data.message);

         user.username="";
         user.password="";
         user.rePassword="";
         user.role="";

         },
         function(error) {

         $scope.errorMessage=error.data.message;
         $window.alert(error.data.message);

         });
    }
});
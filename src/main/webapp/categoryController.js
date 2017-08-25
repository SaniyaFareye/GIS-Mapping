app.controller("categoryController", function($scope,$http,$window){

    $scope.addCategory=function(category){
         $http.post("/category/add",category).then(function(response){
                 $window.alert(response.data.message);

                category.categoryName=""


                 },
                 function(error) {

                 $scope.errorMessage=error.data.message;
                 $window.alert(error.data.message);

                 });
    }
});
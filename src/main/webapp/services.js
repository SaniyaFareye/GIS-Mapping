app.service('Auth', function ($rootScope,$http,$state) {
return{

             isAuthenticated :function(toState) {
                              $http.get("/login/session")
                              .then(function(response){
                                         $rootScope.userInfo=response.data.role;
                                         $rootScope.authenticated=true;
                                         if((toState.name==='login')  ){
                                         event.preventDefault();
                                       //var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/home");
                                         }

                               },
                              function(error) {
                              $rootScope.userInfo=null;
                                           $rootScope.authenticated=false;
                                           $state.go('login');
                                                        });
//                              .then(function(response){
//                                if(response.data){
//                                     $rootScope.authenticated=true;
//                                     if((toState.name==='login')  ){
//                                                 event.preventDefault();
//                                              //var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/home");
//                                         }
//
//                                }
//                                 else{
//                                 $rootScope.authenticated=false;
//                                 $state.go('login');
//                                }
//                             });
             }

};
});
var app= angular.module("app",['ui.router','ui.router.state.events']);
app.config(function($stateProvider,$urlRouterProvider) {

        $stateProvider
               .state('home', {
                 url: "/home",
                 templateUrl: "home.html",
                 controller: 'homeController'
               })
                .state('user', {
                             url: "/user",
                             templateUrl: "user.html",
                             controller: 'userController'
               })
                .state('login', {
                             url: "/login",
                             templateUrl: "login.html",
                             controller: 'loginController'
               })
            .state('map', {
                                     url: "/map",
                                     templateUrl: "map.html",
                                     controller: 'mapController'
                                })
                .state('category', {
                                     url: "/category",
                                     templateUrl: "category.html",
                                     controller: 'categoryController'
                                });



               $urlRouterProvider.otherwise("/login");
   });

app.run(function($rootScope, $location,Auth){
        $rootScope.$on('$stateChangeStart', function(e, toState, toParams, fromState, fromParams) {
        Auth.isAuthenticated(toState);



    });
});
app.controller("mapController", function($scope,$http,$window){


var init =function(){
          var url="/mapLocation/getLocationData";

          $http.get(url).then(function(response){
          $scope.locationData=response.data;
          },
           function(error) {
                $window.alert(error.data.message);
               });
    }

    init();
         var map = L.map('mapid').setView([28.566013, 77.239622], 13);

            L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoicmlkaGFqdW5lamEiLCJhIjoiY2o1eGl3Zjg5MDRvaDJ3czhmNm01b3N3aCJ9.gHRkamqwZKGVjFGFZcFj_w', {
                attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
                maxZoom: 18,
                id: 'mapbox.streets',
                accessToken: 'your.mapbox.access.token'
            }).addTo(map);



            map.addEventListener('click', function(ev) {
               $scope.latitude = ev.latlng.lat;
               $scope.longitude = ev.latlng.lng;
               console.log($scope.latitude + "," + $scope.longitude);
            });

            $http.get("/category/getCategoryList").then(function(response){
                      $scope.categoryList=response.data;
                });


            $scope.addLocation=function(location){
                               location.latitude=$scope.latitude;
                             location.longitude=$scope.longitude;
                             location.approval_status="Pending";
            $http.post("/mapLocation/add",location).then(function(response){

                 $window.alert(response.data.message);
                             $scope.latitude="";
                             $scope.longitude="";
                             location.locationName="";
                             location.category="";
                 },
                 function(error) {

                // $scope.errorMessage=error.data.message;
                 $window.alert(error.data.message);

                 });
            }



});
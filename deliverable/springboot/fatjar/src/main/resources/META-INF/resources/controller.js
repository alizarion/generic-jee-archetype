angular
.module('itesoft-showcase').controller('HomeCtrl',['$scope','Person', function($scope,Person) {
      $scope.person = {};

         

      $scope.submit = function(form){
        console.log($scope.person);
         Person.save($scope.person).$promise.then(function(){
         Person.query().$promise.then(function(data){
           $scope.persons = data;
           $scope.person = {};
         })
         },function(){
           console.log('error');
         });
      }
}]).factory('Person',['$resource',function($resource){
return $resource('http://localhost:8080/rest/persons/:id', null,
    {
        'update': { method:'PUT' }
    });
}])
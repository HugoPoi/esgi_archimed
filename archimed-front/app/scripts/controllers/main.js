'use strict';

/**
 * @ngdoc function
 * @name archimedApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the archimedApp
 */
angular.module('archimedApp')
  .controller('MainCtrl', function ($scope) {

    $scope.defaultMediator = { 'name' : 'New Mediator', color: 'red' };
    $scope.defaultWrapper = { 'name' : 'New Wrapper' , color: 'blue' };

    $scope.grid = [
      [{},{},{},{},{},{}],
      [{},{},{},{},{},{}],
      [{},{},{},{},{},{}],
      [{},{},{},{},{},{}]
    ];

    $scope.openEditor = function(cell){
      $scope.currentEdit = cell;
      $('#editor-modal').modal();
    };
  });

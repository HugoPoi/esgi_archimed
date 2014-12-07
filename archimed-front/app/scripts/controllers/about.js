'use strict';

/**
 * @ngdoc function
 * @name archimedApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the archimedApp
 */
angular.module('archimedApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });

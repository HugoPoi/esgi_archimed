'use strict';

/**
 * @ngdoc function
 * @name archimedApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the archimedApp
 */
angular.module('archimedApp')
  .controller('MainCtrl', function ($scope, $timeout) {

    /*$timeout(function(){
      $('[data-toggle="tooltip"]').tooltip();
    }, 500);*/

    $scope.defaultMediator = { 'name' : 'New Mediator', color: 'orange', type: 'mediator' };
    $scope.defaultWrapper = { 'name' : 'New Wrapper' , color: 'blue', type: 'wrapper' };

    $scope.grid = Array2D.build(6, 4, null);

    $scope.openEditor = function(cell){
      $scope.currentEdit = cell;
      $('#editor-modal').modal();
    };


    $scope.checkGroups = function(){
      //console.log($scope.grid);
     Array2D.contiguous($scope.grid, function(cell){
       return cell !== null;
     }).forEach(function(group, index){
        var groupCells = [];
        group.forEach(function(cellcoord){
          groupCells.push($scope.grid[cellcoord[0]][cellcoord[1]]);
        });
        //Generate groups and get existing
        var newGroup = { 'name': 'Group '+ index, 'status': checkGroup(groupCells) };
        groupCells.forEach(function(cell){
          cell.group = newGroup;
        });
     });
    };

    var checkGroup = function(group){
      if( _.where(group, { 'type': 'mediator' }).length !== 1 ){ return 'invalid-group'; }
      if( group.length < 2){ return 'invalid-group'; }
      return 'valid-group';
    };

    $scope.genClassItem = function(cell, r, c){
      var classes = [];
      if(cell !== undefined && cell !== null){
        classes.push('have-item');

        var neighbors = Array2D.orthogonals($scope.grid, r, c);
        if(!neighbors[0]){ classes.push('free-top');}
        if(!neighbors[1]){ classes.push('free-left');}
        if(!neighbors[2]){ classes.push('free-right');}
        if(!neighbors[3]){ classes.push('free-bottom');}
        if(cell.group !== undefined){ classes.push(cell.group.status);}
      }
      return classes;
    };

    $scope.startDragging = function(){
      $scope.currentlyDragging = true;
    };
    $scope.stopDragging = function(){
      $scope.currentlyDragging = false;
    };
  });

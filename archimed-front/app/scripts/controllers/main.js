'use strict';

/**
 * @ngdoc function
 * @name archimedApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the archimedApp
 */
angular.module('archimedApp')
  .controller('MainCtrl', function ($scope, $timeout, Config, Wrapper) {

    $scope.configList = Config.query();

    $scope.loadConfig = function(config){
      var configToLoad = Config.get({filename: config.filename});
      configToLoad.$promise.then(function(){
        configToLoad.environnements.group.forEach(function(group){
          group.mediator.type = 'mediator';
          var gridGroup = { name : group.name, active: group.active };
          group.mediator.group = gridGroup;
          $scope.grid[group.mediator.positionX][group.mediator.positionY] = group.mediator;
          group.wrapper.forEach(function(wrapper){
            wrapper.type = 'wrapper';
            wrapper.group = gridGroup;
            $scope.grid[wrapper.positionX][wrapper.positionY] = wrapper;
          });
        });
      });
    };

    $scope.saveConfig = function( filename ){
      var newConfig = { environnements : { group : [] } };
      $scope.checkGroups(function(group, mediator, wrappers){

        var newGroup = { name: mediator.group.name,
          active: mediator.group.active || false,
          mediator : _.omit(mediator,['group', 'type']),
          wrapper: _.map( wrappers, function(v){ return _.omit(v, ['group', 'type'])})
        };

        newConfig.environnements.group.push(newGroup);
      });

      Config.save({filename : filename}, newConfig);
    }

    $scope.defaultMediator = { 'name' : 'New Mediator', type: 'mediator' };
    $scope.defaultWrapper = { 'name' : 'New Wrapper' , type: 'wrapper' };

    $scope.grid = Array2D.build(6, 4, null);

    $scope.openEditor = function(cell){
      $scope.currentEdit = cell;
      $('#editor-modal').modal();
    };

    $scope.deleteCell = function( r, c ){
      $scope.grid[r][c] = null;
      $scope.checkGroups();
    };

    $scope.active = function(cell){
      if(cell.group.active === undefined){
        cell.group.active = true;
      }else{
        cell.group.active = !cell.group.active;
      }
    };

    $scope.checkGroups = function( saveCallback ){
     Array2D.contiguous($scope.grid, function(cell){
       return cell !== null;
     }).forEach(function(group, groupIndex){
       var groupCells = [],
       wrappers = [],
       mediator;

       var newGroup = { 'name': 'Group '+ groupIndex};

       group.forEach(function(cellcoord){
         groupCells.push($scope.grid[cellcoord[0]][cellcoord[1]]);
         $scope.grid[cellcoord[0]][cellcoord[1]].positionX = cellcoord[0];
         $scope.grid[cellcoord[0]][cellcoord[1]].positionY = cellcoord[1];
         if($scope.grid[cellcoord[0]][cellcoord[1]].type === 'mediator'){
           mediator = $scope.grid[cellcoord[0]][cellcoord[1]];
         }else{
           wrappers.push($scope.grid[cellcoord[0]][cellcoord[1]]);
         }
       });
       var groupStatus = getGroupStatus(groupCells);
       if( groupStatus === 'valid-group' && mediator.group){
         newGroup = mediator.group;
       }

       newGroup.status = groupStatus;

       groupCells.forEach(function(e){
         e.group = newGroup;
       });

       if( _.isFunction(saveCallback) ){
         saveCallback( newGroup, mediator, wrappers );
       }
     });
    };

    var getGroupStatus = function(group){
      if( _.where(group, { 'type': 'mediator' }).length !== 1 ){ return 'invalid-group'; }
      if( group.length < 2){ return 'invalid-group'; }
      return 'valid-group';
    };

    $scope.genClassItem = function(cell, r, c){
      var classes = [];
      if(cell !== undefined && cell !== null){
        classes.push('have-item');
        classes.push(cell.type);

        var neighbors = Array2D.orthogonals($scope.grid, r, c);
        if(!neighbors[0]){ classes.push('free-top');}
        if(!neighbors[1]){ classes.push('free-left');}
        if(!neighbors[2]){ classes.push('free-right');}
        if(!neighbors[3]){ classes.push('free-bottom');}
        if(cell.group !== undefined){
          classes.push(cell.group.status);
          if(cell.group.active){ classes.push('group-activated'); }
        }
      }
      return classes;
    };

    $scope.startDragging = function(){
      $scope.currentlyDragging = true;
    };
    $scope.stopDragging = function(){
      $scope.currentlyDragging = false;
    };

    $scope.openRequest = function(){
      $('#request-modal').modal();
    };
    $scope.request = { xpath: '', response: ''};

    $scope.executeRequest = function(){
      $scope.request.response = Wrapper.get({ action : 'ping'});
    };

    $scope.addParam = function(){
      if($scope.currentEdit.param === undefined){
        $scope.currentEdit.param = [];
      }
      $scope.currentEdit.param.push({value:'', name:''});
    };

    $scope.deleteParam = function(param){
      //delete param;
    };

  });

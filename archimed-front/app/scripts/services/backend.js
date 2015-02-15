'use strict';
angular.module('archimed.services.backend', ['ngResource', 'archimed.config'])
.factory('Config', function($resource, ENV) {
return $resource( ENV.apiEndpoint + '/configs/:filename' );
});


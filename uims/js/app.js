/**
 * Created by alex on 20/10/16.
 */

 var app = angular.module('uims',["ngRoute"]);

 app.config(function ($routeProvider, $httpProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'templates/dashboard.html'
    })
    .when('/devices', {
        templateUrl: 'templates/devices.html',
        controller: 'DeviceCtrl'
    })
    .when('/services', {
        templateUrl: 'templates/services.html',
        controller: 'ServiceCtrl'
    })
    .when('/arguments', {
        templateUrl: 'templates/arguments.html',
        controller: 'ArgumentCtrl'
    });


    
    // $httpProvider.defaults.useXDomain = true;
    // delete $httpProvider.defaults.headers.common['X-Requested-With'];
    // // $httpProvider.defaults.headers.commom['Accept'] = '*/*';
    // // $httpProvider.defaults.headers.common['Content-Type'] = 'application/json, text/javascript, text/html';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Methods'] = 'GET, OPTIONS';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = 'Authorization';
})
/**
 * Created by alex on 20/10/16.
 */

 var app = angular.module('patrimonio',["ngRoute"]);

 app.config(function ($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'templates/dashboard.html'
    })
    .when('/produtos', {
        templateUrl: 'templates/produtos.html',
        controller: 'produtosCtrl'
    })
    .when('/product_details/:id', {
        templateUrl: 'templates/product_details.html',
        controller: 'productDetailsCtrl'
    })

})
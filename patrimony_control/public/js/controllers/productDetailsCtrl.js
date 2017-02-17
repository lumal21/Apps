/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller("productDetailsCtrl", function ($scope, $http,$routeParams		) {

	$http.get('../view_controller/product/details_prod.view.php?id='+$routeParams.id)
		 .success(function(ret){
		 	
		 	for(i in ret){
		 		$scope.prod_details = ret[i];	
		 	}
		 })
		 .error(function(error){
		 	console.log('error');console.log(error);
		 })

	$http.get('../view_controller/product/history_prod.view.php?id='+$routeParams.id)
		 .success(function(ret){
		 	console.log(ret)
		 		
		 		
		 })
		 .error(function(error){
		 	console.log('error');console.log(error);
		 })

});

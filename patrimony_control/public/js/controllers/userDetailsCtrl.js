/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller("userDetailsCtrl", function ($scope, $http,$routeParams) {

	$http.get('../view_controller/user/details_user.view.php?id='+$routeParams.id)
		 .success(function(ret){
		 	for(i in ret){
		 		$scope.usr_details = ret[i];
		 	}
		 })
		 .error(function(error){
		 	console.log('error');console.log(error);
		 })

	$http.get('../view_controller/user/user_with_prod.view.php?id='+$routeParams.id)
		 .success(function(ret){
		 	$scope.user_prod = ret;
		 })
		 .error(function(error){
		 	console.log('error');console.log(error);
		 })

});

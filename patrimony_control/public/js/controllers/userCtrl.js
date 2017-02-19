/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller("userCtrl", function ($scope, $http) {

 		$http.get('../view_controller/user/select_all_user.view.php')
 		.success(function (ret) {
 			console.log(ret);
 			$scope.users = ret;
 			$scope.teste = 'asdada';
 		})
 		.error(function (error){
 			console.log('error');
 			console.log(error);
 		})



 	$scope.newUser = function(novoUsrForm) {
 		$http.post('../view_controller/user/new_user.view.php', novoUsrForm)
 		.success(function (ret) {
 			console.log(ret);
 			swal("Sucesso!", "Produto cadastrado com sucesso!", "success");
 		})
 		.error(function (error){
 			swal("Erro!", "Algo deu errado, por favor tente novamente!", "error");
 		})
 	};

 	$scope.deleteUsr = function(usrId) {
 		$http.get('../view_controller/user/delete_user.view.php?id='+usrId)
 		.success(function (ret) {
 			if(ret = 1){
 				swal("Sucesso!", "Usuário excluído com sucesso!", "success");
 			
 			}else{
 				swal("Erro!", "Algo deu errado, por favor tente novamente!", "error");	
 			}

 			
 		})
 		.error(function (error){
 			swal("Erro!", "Algo deu errado, por favor tente novamente!", "error");
 		})
 	}
});

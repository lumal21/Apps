/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller("produtosCtrl", function ($scope, $http) {

 		$http.get('../view_controller/product/select_all_prod.view.php')
 		.success(function (ret) {
 			$scope.produtos = ret;
 		})
 		.error(function (error){
 			console.log('error');
 			console.log(error);
 		})



 	$scope.novoProduto = function(novoProdForm) {
 		$http.post('../view_controller/product_ctrl.view.php', novoProdForm)
 		.success(function (ret) {
 			swal("Sucesso!", "Produto Cadastrado com sucesso!", "success");
 		})
 		.error(function (error){
 			swal("Erro!", "Algo deu errado, por favor tente novamente!", "error");
 		})
 	};

});

/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller('ArgumentCtrl', function ($rootScope, $scope, Request, $http){
 	
 	$http.get('server-side/argumentRequest.php')
 	.success(function(argument_return){
 		$scope.qtdArguments = argument_return.length;
 		$scope.argument_table = argument_return;
 	})
 	.error(function(error_return){
 		console.log('aconteceu um erro na comunicação com o argumento');
 	});

 	$scope.exportArgumentData = function () {
        var blob = new Blob([document.getElementById('argument-table').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "ArgumentsReport.xls");
    };
 });
/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller('ServiceCtrl', function ($rootScope, $scope, Request, $http){

 	$http.get('server-side/serviceRequest.php')
 	.success(function(service_return){
 		$scope.qtdServices = service_return.length;
 		$scope.service_table = service_return;
 	})
 	.error(function(error_return){

 	});


 	$scope.exportServiceData = function () {
        var blob = new Blob([document.getElementById('service-table').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "ServiceReport.xls");
    };
 });
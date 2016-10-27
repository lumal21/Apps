/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 22/10/16.
 */
 app.controller("DeviceCtrl", function ($scope, Request) {
 
  	
    Request.getDevices()
    .success(function(devices_return){		
        $scope.qtdDevices = devices_return.length;
        $scope.device_table = devices_return;
  		
  	}).error(function(data){
  		  console.log('DEU ERRO na conexão com o device');
  		  console.log(data);
  	});


    $scope.exportDeviceData = function () {
        var blob = new Blob([document.getElementById('device-table').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "DeviceReport.xls");
    };
 	
 });

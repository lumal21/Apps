/**
 * Created by alex on 07/08/16.
 * Alex Alexandre
 * alex.alexandre@redes.unb.br
 * alexalexandre@gmail.com
 */


/*
*  IP para comunicacao com o servidor é o raise.uiot.org
* */


app.controller('IoTAccessNetWorkCtrl', function ($scope, $stateParams) {

    $scope.sendAccessNW = function (accessNetWork) {
        var ip  = accessNetWork.ip;
        var gps = accessNetWork.gps;

        console.log(ip + gps);


    }
});

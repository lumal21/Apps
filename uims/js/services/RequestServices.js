/**
 * Created by Alex Alexandre
 * <alex.alexandre@redes.unb.br>  
 * on 21/10/16.
 */

 app.service('Request', ['$http', function ($http) {
    this.getDevices = function () {
        return $http.get('requests/deviceRequest.php');
    };

    this.getServices = function(){
        return $http.get('requests/serviceRequest.php');
    };

    this.getArguments = function(){
        return $http.get('requests/argumentRequest.php');
    };
}]);
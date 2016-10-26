/**
 * Created by Alex Alexandre
 * <alex.alexandre@redes.unb.br>  
 * on 21/10/16.
 */

 app.value('Config', {
    url: 'http://raise.uiot.org/',
    urlDevice: 'http://raise.uiot.org/devices',
    urlService: 'http://raise.uiot.org/services',
    urlArgument: 'http://raise.uiot.org/arguments'

});

 app.value('DeviceParam', {
    name: 'UIMSbeta',
    chipset_id: '1234',
    processor_id: '1234',
    mac: '78:2b:cb:ed:37:0d',
    serial: '1234'
});

 app.service('Request', ['$http', 'Config', '$rootScope', 'DeviceParam', function ($http, Config, $rootScope, DeviceParam) {
    this.getDevices = function (token) {
        var urlRequestDevice = '?token=' + token;
        return $http.get(Config.urlDevice + urlRequestDevice);
    };

    this.getServices = function(token){
        var urlRequestService = '?token=' + token;
        return $http.get(Config.urlService + urlRequestService);
    };

    this.getArguments = function(token){
        var urlRequestArgument = '?token=' + token;
        return $http.get(Config.urlArgument + urlRequestArgument);
    };
}]);
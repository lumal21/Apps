/**
 * Created by alex on 17/08/16.
 * Alex Alexandre
 * alex.alexandre@redes.unb.br
 */


app.value('Config', {
    url: 'http://104.210.8.88/',
    urlDevice: 'http://104.210.8.88/devices',
    urlService: 'http://104.210.8.88/services',
    urlArgument: 'http://104.210.8.88/arguments'

});

app.service('Request', ['$http', 'Config', function ($http, Config) {
    this.postDevice = function (deviceParam) {
        var urlRequest = '?name=' + deviceParam.name + '&chipset_id=' + deviceParam.chipset_id + '&processor_id=' + deviceParam.processor_id + '&mac=' + deviceParam.mac + '&serial=' + deviceParam.serial;
        return $http({
            method: 'POST',
            url: Config.urlDevice + urlRequest
        })
    };

    this.postServices = function (servicesParam) {
        var urlRequestService = '?token=' + servicesParam.token + '&name=' + servicesParam.name + '&type=' + servicesParam.type + '&scpdurl=' + servicesParam.scpdurl + '&control_url=' + servicesParam.control_url + '&event_sud_url=' + servicesParam.event_sud_url + '&refresh_rate=' + servicesParam.refresh_rate;
        return $http({
            method: 'POST',
            url: Config.urlService + urlRequestService
        });
    };

    this.postArguments = function (argumentsParam) {
        var urlRequestArgument = '?token=' + argumentsParam.token + '&action_id=' + argumentsParam.action_id + '&name=' + argumentsParam.name + '&default_value=' + argumentsParam.default_value + '&return_value=' + argumentsParam.return_value;
        return $http({
            method: 'POST',
            url: Config.urlArgument + urlRequestArgument
        });
    };


    this.putArguments = function (argumentsParam) {
        var urlRequestArgument = '?token=' + argumentsParam.token + '&id=' + argumentsParam.item_id + '&return_value=' + argumentsParam.return_value;
        return $http({
            method: 'PUT',
            url: Config.urlArgument + urlRequestArgument
        });
    };

}]);
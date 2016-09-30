/**
 *EN: Register an array of services - Method POST
 *PT: Registra um array de serviços - Método POST
 *ES: Registra uno array de servicios - Método POST
 */
function serviceRegister() {
    var _services = new Array();
    //exemplo de serviço a ser adicionado 
    //O serviço tem um array de paramentros
    var _params = new Array();

    var Parameter = {
        name: _paramName,
        type: _paramType
    };

    _params.push(Parameter);

    var _service = {
        name: _serviceName,
        return :_return,
        params: _params
    };

    _services.push(_service);

    $.post("/service/register/", {
        token: _token,
        services: _services,
        type: _type
    }).done(function (data) {
        if (data.Code === '200') {

        } else {
            showErrorMessage(data.ErrorResponse);
        }

    });

}

/**
 *EN: Send service temporal information - Method POST
 *PT: Envia informações temporais do serviço - Método POST
 *ES: Envia información temporales - Método POST
 */
function serviceSendValues() {

	var _services = new Array();
	//exemplo serviceval
	var params = new Array();
	var Parameter = { name: _paramName, type: _paramType};
	params.push(Parameter);
	var ServiceVal = { service_id : _service_id, params : _params};
	_services.push(_services);

	 $.post("/service/send_values/", {
        token: _token,
        services: _services,
        type: _type
    }).done(function (data) {
        if (data.Code === '200') {

        } else {
            showErrorMessage(data.ErrorResponse);
        }

    });



}


/**
 *EN: Lists services accessible by the device - Method GET
 *PT: Lista os serviços acessado pelo dispositivo - Método GET
 *ES: Enumera los servicios accesibles por el dispositivo - Método GET
 */
function serviceList() {
	$.get("/service/list/", {
        token: _token
    }).done(function (data) {
        if (data.Code === '200') {

        } else {
            showErrorMessage(data.ErrorResponse);
        }

    });


}
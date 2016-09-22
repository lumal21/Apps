/**
 *EN: Returns a list containing the headers log stored on the server- Method GET
 *PT: Retorna uma lista contendo os cabeçalhos de históricos armazenados no servidor - Método GET
 *ES: Devuelve una lista que contiene las cabeceras de registro almacenados en el servidor - Método GET
 */
function serviceList() {
	$.get("/service/list/", {
        token : _token,
        start_date : _start_date,
        end_date : _end_date,
        limit : _limit,
        order : _order
    }).done(function (data) {
        if (data.Code === '200') {

        } else {
            showErrorMessage(data.ErrorResponse);
        }

    });


}
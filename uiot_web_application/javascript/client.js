 /**
  *EN: Get registered clients - Method GET
  *PT: Retorna clientes registrados - Método GET 
  *ES: Devuelve clientes registrados - Método GET
  */
function getClientList() {

     $.get("/client/list/", {
         name: _name,
         processor: _processor,
         channel: _channel,
         host: _host,
         tag: _tag
     }).done(function (data) {
         if (data.Code === '200') {

         } else {
             showErrorMessage(data.ErrorResponse);
         }

     })
     }


/**
*EN: Get registered clients - Method GET
*PT: Retorna clientes registrados - Método GET 
*ES: Devuelve clientes registrados - Método GET 
*/
function getClientListServiceValues() {
         $.get("/client/list_service_values/", {
             token: _token,
             start_date: _start_date,
             end_date: _end_date,
             limit: _limit,
             order: _order,
             name: _name,
             processor: _processor,
             channel: _channel,
             host: _host,
             tag: _tag
         }).done(function (data) {
             if (data.Code === '200') {

             } else {
                 showErrorMessage(data.ErrorResponse);
             }
         })
         }


/**
*EN: Register a client - Method POST
*PT: Registra um cliente - Método POST
*ES: Registra uno cliente - Método POST
*/
function clientRegister() {
             var _cliente = {
                 name: _name,
                 chipset: _chipset,
                 mac: _mac,
                 serial: _serial,
                 processor: _processor,
                 channel: _channel
             };
             $.post("/client/register/", {
                 cliente: _cliente
             }).done(function (data) {
                 if (data.Code === '200') {

                 } else {
                     showErrorMessage(data.ErrorResponse);
                 }
             })
     }
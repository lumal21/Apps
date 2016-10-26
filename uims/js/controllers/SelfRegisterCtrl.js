/**
 * Created by Alex Alexandre 
 * <alex.alexandre@redes.unb.br> 
 * on 20/10/16.
 */

app.controller('SelfRegisterCtrl', function ($rootScope, Request) {
   $rootScope.TOKEN = 'UIMS-MANAGEMENT-SYSTEM';

});


   /* if($rootScope.TOKEN == '')
    {
        Request.postDevice(deviceParam)
        .success(function(device_return){
            $rootScope.TOKEN = device_return.token;


            if(device_return.code == 500)
            {
                console.log('o token atualizado:'  + UpdateToken.putToken());
                $rootScope.TOKEN = UpdateToken.putToken();
                
            }

        
        })
        .error(function(device_error){
            console.log('entrou no erro do device:' + device_error);
        });
    } */

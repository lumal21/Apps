/**
 * Created by alex on 07/08/16.
 * Alex Alexandre
 * alex.alexandre@redes.unb.br
 */


app.controller('IoTAccessNetWorkCtrl', ['$scope', '$ionicPopup', 'Request', 'NameGenerate', function ($scope, $ionicPopup, Request, NameGenerate) {
    // const TOKEN = '';
    var TOKEN = '';

    $scope.sendAccessNW = function (accessNetWork) {

        var deviceParameters = {
            name: NameGenerate.nameGenerate(),
            chipset_id: 123,
            processor_id: 123,
            mac: 0000111,
            serial: 123
        };

        Request.postDevice(deviceParameters)
            .success(function (device_data) {

                if (device_data.code == 200) {

                    TOKEN = device_data.token;

                    var serviceParameters = {
                        token: TOKEN,
                        name: NameGenerate.nameGenerate(),
                        type: 'GPS',
                        scpdurl: '192.1.1.1',
                        control_url: '192.1.1.1',
                        event_sud_url: '192.1.1.1',
                        refresh_rate: 5.0
                    };

                        console.log('device_data: ');
                        console.log(device_data);

                    Request.postServices(serviceParameters)
                        .success(function (service_data) {

                            console.log('service_data');
                            console.log(service_data);

                            var argumentsParameters = {
                                token: TOKEN,
                                action_id: service_data.action_id,
                                name: NameGenerate.nameGenerate(),
                                default_value: 3,
                                return_value: 2
                            };

                            var tempo = serviceParameters.refresh_rate * 1000;


                            console.log('argumentsParametersPost: ');
                            console.log(argumentsParameters);


                            Request.postArguments(argumentsParameters)
                                .success(function (arguments_data) {

                                    console.log('arguments_data POST: ');
                                    console.log(arguments_data);


                                    var argumentsParametersPut = {
                                        token: TOKEN,
                                        return_value: 60,
                                        item_id: arguments_data.item_id
                                    };

                                    console.log('argumentsParametersPut: ');
                                    console.log(argumentsParametersPut);


                                    var put = function () {
                                        Request.putArguments(argumentsParametersPut)
                                            .success(function (arguments_put_data) {

                                                console.log('arguments_put_data: ');
                                                console.log(arguments_put_data);


                                            })
                                            // ERROR PUT ARGUMENT
                                            .error(function (arguments_error) {
                                                var alertPopup = $ionicPopup.alert({
                                                    title: 'Falha!',
                                                    template: 'Um erro ocorreu durante o cadastro, tente novamente!'
                                                });
                                            })
                                    };


                                    setInterval(put, tempo);

                                })
                                // ERROR POST ARGUMENTS
                                .error(function (arguments_error) {
                                    var alertPopup = $ionicPopup.alert({
                                        title: 'Falha!',
                                        template: 'Um erro ocorreu durante o cadastro, tente novamente!'
                                    });
                                })


                        })
                        // ERRO DO SERVICE
                        .error(function (service_error) {
                            var alertPopup = $ionicPopup.alert({
                                title: 'Falha!',
                                template: 'Um erro ocorreu durante o cadastro, tente novamente!'
                            });
                        })

                }else{
                    var alertPopup = $ionicPopup.alert({
                        title: 'Falha!',
                        template: 'Um erro ocorreu durante o cadastro, tente novamente!'
                    });
                }



            })
            // ERRO DO DEVICE
            .error(function (error) {
                var alertPopup = $ionicPopup.alert({
                    title: 'Falha!',
                    template: 'Um erro ocorreu durante o cadastro, tente novamente!'
                });
            })
    };
}]);

// code 500 = Invalid or experid token
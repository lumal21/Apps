/**
 * Created by alex on 17/08/16.
 * Alex Alexandre
 * alex.alexandre@redes.unb.br
 */

app.service('NameGenerate', [function () {
    this.nameGenerate = function () {
        var letras = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz';
        var aleatorio = '';
        for (var i = 0; i < 8; i++) {
            var rnum = Math.floor(Math.random() * letras.length);
            aleatorio += letras.substring(rnum, rnum + 1);
        }
        return aleatorio;
    };
}]);
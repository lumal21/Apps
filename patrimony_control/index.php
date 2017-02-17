
<!DOCTYPE html>
<html class="ls-theme-light-green">
<head>
  <meta charset="utf-8">
  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <title>Tela de Login</title>
  <meta name="description" content="" />
  <meta name="keywords" content="" />
  <link href="lib/locaweb_style/stylesheets/locastyle.css" rel="stylesheet" type="text/css">
</head>

<body class="documentacao documentacao_exemplos documentacao_exemplos_login-screen documentacao_exemplos_login-screen_index">

  <div class="ls-login-parent">
    <div class="ls-login-inner">
      <div class="ls-login-container">
        <div class="ls-login-box">
        <h1 class="ls-login-logo">Controle de Patrimônio</h1>
          <form role="form" class="ls-form ls-login-form" action="view_controller/login_ctrl.view.php" method="POST">
            <fieldset>

              <label class="ls-label">
                <input class="ls-login-bg-user ls-field-lg" type="text" placeholder="Usuário" required autofocus 
                       name="username">
              </label>

              <label class="ls-label">
                <div class="ls-prefix-group ls-field-lg">
                  <input id="password_field" class="ls-login-bg-password" type="password" placeholder="Senha" required
                         name="password">
                  <a class="ls-label-text-prefix ls-toggle-pass ls-ico-eye" data-toggle-class="ls-ico-eye, ls-ico-eye-blocked" data-target="#password_field" href="#"></a>
                </div>
              </label>

              <p><a class="ls-login-forgot" href="forgot-password">Esqueci minha senha</a></p>

              <input type="submit" value="Entrar" class="ls-btn-primary ls-btn-block ls-btn-lg">

              <p class="ls-txt-center ls-login-signup">
                Precisa de um cadastro?<br><a href="#">Peça ao administrador do sistema</a>
              </p>
            </fieldset>
          </form>
        </div>

        <div class="ls-login-adv"><img title="Exemplo banner" src="assets/img/uiot_poweredby.png" /></div>

      </div>
    </div>
  </div>

  <script src="lib/jquery/jquery-3.1.1.min.js" type="text/javascript"></script>
  <script src="lib/locaweb_style/javascripts/locastyle.js" type="text/javascript"></script>

</body>
</html>

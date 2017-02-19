<!DOCTYPE html>
<html class="ls-theme-light-red">
<head>
    <title>Patrimônio UIOT</title>

    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="description" content="Insira aqui a descrição da página.">
    <link href="../lib/locaweb_style/stylesheets/locastyle.css" rel="stylesheet" type="text/css">
    <link href="../lib/sweet_alert/sweetalert.css" rel="stylesheet" type="text/css">

    <!-- We recommended use jQuery 1.10 or up -->
    <script src="http://code.jquery.com/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="/lib/locaweb_style/javascripts/locastyle.js" type="text/javascript"></script>   
    <script src="/lib/sweet_alert/sweetalert.min.js" type="text/javascript"></script>


<script type="text/javascript">
   (function() {
    // Inicia a função de Modal manualmente
    locastyle.init();
  })();
  
</script>

    <!-- Angular imports -->
    <script src="../lib/angular-1.5.8/angular.js"></script>
    <script src="../lib/angular-1.5.8/angular-route.js"></script>
    <script src="js/app.js"></script>
    <script src="js/controllers/produtosCtrl.js"></script>
    <script src="js/controllers/productDetailsCtrl.js"></script>
    <script src="js/controllers/userCtrl.js"></script>
    <script src="js/controllers/userDetailsCtrl.js"></script>
</head>
<body ng-app="patrimonio">
    <div class="ls-topbar">

        <!-- Barra de Notificações -->
        <div class="ls-notification-topbar">

            <!-- Links de apoio -->
            <div class="ls-alerts-list">
                <a href="#" class="ls-ico-bullhorn" data-ls-module="topbarCurtain" data-target="#ls-help-curtain"><span>Ajuda</span></a>
            </div>

            <!-- Dropdown com detalhes da conta de usuário -->
            <div data-ls-module="dropdown" class="ls-dropdown ls-user-account">
                <a href="#" class="ls-ico-user">
                    <!-- <img src="../assets/img/avatar.png" alt="" /> -->
                    <span class="ls-name">João Kennedy</span>
                    (johnkennedy)
                </a>

                <nav class="ls-dropdown-nav ls-user-menu">
                    <ul>
                        <li><a href="#">Meus dados</a></li>
                        <li><a href="#">Faturas</a></li>
                        <li><a href="#">Planos</a></li>
                        <li><a href="#">Sair</a></li>
                    </ul>
                </nav>
            </div>
        </div>

        <span class="ls-show-sidebar ls-ico-menu"></span>

        <a href="javascript:window.history.go(-1)"  class="ls-go-next">
            <span class="ls-text">Voltar à página anterior</span>
        </a>

        <!-- Nome do produto/marca com sidebar -->
        <h1 class="ls-brand-name">
            <a href="#/" class="ls-ico-lamp">
                <small>UIoT produções</small>Controle de patrimônio
            </a>
        </h1>

        <!-- Nome do produto/marca sem sidebar quando for o pre-painel  -->
    </div>


    <aside class="ls-sidebar">

        <div class="ls-sidebar-inner">
            <a href="/locawebstyle/documentacao/exemplos//pre-painel"  class="ls-go-prev"><span class="ls-text">Voltar à lista de serviços</span></a>

            <nav class="ls-menu">
                <ul>
                    <li>
                        <a href="#/" class="ls-ico-dashboard" title="Administração de Dashboard">Dashboard</a>
                    </li>
                    <li>
                        <a href="#produtos" class="ls-ico-ftp" title="Administração de Produtos">Produtos</a>
                    </li>
                    <li>
                        <a href="#usuarios" class="ls-ico-users" title="Administração de Usuários">Usuários</a>
                    </li>
                    <li>
                        <a href="#usuarios" class="ls-ico-history">Gerenciar Empréstimos</a>
                    </li>
                </ul>
            </nav>


        </div>
    </aside>


    <main class="ls-main ">
        <div class="container-fluid">

            <ng-view>

            </ng-view>

        </div>
    </main>

    <aside class="ls-notification">
        <nav class="ls-notification-list" id="ls-help-curtain" style="left: 1756px;">
            <h3 class="ls-title-2">Feedback</h3>
            <ul>
                <li><a href="#">&gt; quo fugiat facilis nulla perspiciatis consequatur</a></li>
                <li><a href="#">&gt; enim et labore repellat enim debitis</a></li>
            </ul>
        </nav>
    </aside>
</body>
</html>
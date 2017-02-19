<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 15/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/controller/user_ctrl.php";



$userCtrl = new UserController();
$result = $userCtrl->selectAllUser();
echo json_encode($result);
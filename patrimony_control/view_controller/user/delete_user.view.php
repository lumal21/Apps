<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 17/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/controller/user_ctrl.php";

$user_id = $_GET["id"];

$userCtrl = new UserController();
$result = $userCtrl->deleteUser($user_id);
echo $result;
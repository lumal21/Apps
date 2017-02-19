<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 14/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/controller/user_ctrl.php";


$postdata = file_get_contents("php://input");
$userParam = json_decode($postdata);

$userCtrl = new UserController();
$result = $userCtrl->newUser($userParam);
echo $result;
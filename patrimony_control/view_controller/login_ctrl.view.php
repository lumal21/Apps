<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 13/02/2016
 */
session_start();
include_once $_SERVER['DOCUMENT_ROOT']."/controller/user_ctrl.php";


$userControl = new UserController();
$userInfo = array('username' => $_POST['username'], 'userpass' => $_POST['password']);

$result = $userControl->login($userInfo);

if(!empty($result))
{
	$_SESSION["user_name"] = $userInfo["nme_usuario"];
	header("Location: ../public/home.php");
}else{
	echo 'caiu no if';
	header("Location: ../index.php");
}
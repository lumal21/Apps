<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 14/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/controller/product_ctrl.php";


$postdata = file_get_contents("php://input");
$prodsParam = json_decode($postdata);
// $email = $request->test;


$prodCtrl = new ProductController();
$result = $prodCtrl->newProduct($prodsParam);
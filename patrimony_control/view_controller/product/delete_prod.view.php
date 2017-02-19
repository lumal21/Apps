<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 17/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/controller/product_ctrl.php";

$prod_id = $_GET["id"];

$prodCtrl = new ProductController();
$result = $prodCtrl->deleteProduct($prod_id);
echo $result;
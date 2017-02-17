<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 15/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/controller/product_ctrl.php";



$prodCtrl = new ProductController();
$result = $prodCtrl->selectAllProduct();
echo json_encode($result);
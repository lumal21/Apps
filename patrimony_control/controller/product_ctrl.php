<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 13/02/2016
 */
include_once $_SERVER['DOCUMENT_ROOT']."/database/create_connection_db.php";
include_once $_SERVER['DOCUMENT_ROOT']."/database/product_db.php";

class ProductController
{

	var $getConnection;
	var $prodDb;
	
	function __construct()
	{
		$this->getConnection = new GetDBConn();
		$this->prodDb = new ProductDB();
	}

	public function selectAllProduct()
	{
		return $this->prodDb->selectAllProduct($this->getConnection->get_connection());
	}

	public function selectProduct($prodId)
	{
		return $this->prodDb->selectProduct($this->getConnection->get_connection(), $prodId);
	}

	public function newProduct($prodP)
	{	
		return $this->prodDb->insertProduct($this->getConnection->get_connection(), $prodP, $this->getCurrentTime());
	}


	public function historyProduct($prodId)
	{
		return $this->prodDb->historyProduct($this->getConnection->get_connection(), $prodId);	
	}


	/**
	 *  Function to get the current date
	 */
	public function getCurrentTime()
	{
		date_default_timezone_set('America/Sao_Paulo');
		$date = date('Y-m-d');
		return $date;
	}

}
<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 13/02/2016
 */

require_once $_SERVER['DOCUMENT_ROOT']."/database/create_connection_db.php";
require_once $_SERVER['DOCUMENT_ROOT']."/database/user_db.php";

class UserController
{

	var $getConnection;
	var $userDb;
	
	function __construct()
	{
		$this->getConnection = new GetDBConn();
		$this->userDb = new UserDB();
	}


	public function login($userP)
	{
		return $this->userDb->login($this->getConnection->get_connection(), $userP);
	}

}
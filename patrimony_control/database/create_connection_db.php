<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 13/02/2016
 */
 include_once "connection_db.php";

 class GetDBConn
 {

 	var $db_connection;
 	
 	function __construct()
 	{
 		self::create_db_connection();
 	}


 	private function create_db_connection(){
        global $database;
        $this->db_connection = new DatabaseConnector($database);
    }

    public function get_connection(){
        return $this->db_connection->get_connection();
    }
 }
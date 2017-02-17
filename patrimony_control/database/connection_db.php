<?php
/**
 * User: Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Date: 14/01/17
 * Time: 00:12
 */

require_once "config_db.php";

class DatabaseConnector{

    var $user;
    var $pswd;
    var $host;
    var $port;
    var $db_name;
    var $db_type;

    public function __construct($database){
        self::set_user($database['user']);
        self::set_pswd($database['pswd']);
        self::set_host($database['host']);
        self::set_port($database['port']);
        self::set_name($database['name']);
        self::set_type($database['typeUser']);
    }

    private function set_user($user){
        $this->user = (isset($user) ? $user : NULL);
    }

    private function set_pswd($pswd){
        $this->pswd = (isset($pswd) ? $pswd : NULL);
    }

    private function set_host($host){
        $this->host = (isset($host) ? $host : NULL);
    }

    private function set_port($port){
        $this->port = (isset($port) ? $port : NULL);
    }

    private function set_type($type){
        $this->type = (isset($type) ? $type : NULL);
    }

    private function set_name($name){
        $this->name = (isset($name) ? $name : NULL);
    }

    private function get_type(){
        return $this->type;
    }

    private function get_name(){
        return $this->type;
    }

    public function get_connection(){
        try{
            $conn = null;
            switch(self::get_type())
            {
                case 'mysql':
                    $conn = self::create_mysql_connection();
                    break;
            }
        }catch(Exception $e) {
            $e->getMessage();
        }

        return $conn;
    }

    private function create_mysql_connection(){
        $conn = new PDO("mysql:host={$this->host}; port={$this->port}; dbname={$this->db_name}", $this->user, $this->pswd,
            array(PDO::ATTR_ERRMODE => PDO::ERRMODE_WARNING));
        return $conn;
    }
}
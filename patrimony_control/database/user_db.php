<?php 
/**
 * Created by Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Data 13/02/2016
 */

class UserDB
{
	

	public function login($conn, $userParam)
	{

		$encode = array();
		$user = $userParam["username"];
		$psw = $userParam["userpass"];

		$SQL = "SELECT nme_usuario, email_usuario FROM patrimonio_uiot.tb_login INNER JOIN patrimonio_uiot.tb_usuario 		WHERE usr_login = '$user' AND psw_login = '$psw'";

		$result = $conn->query($SQL);

		if($result->rowCount() > 0)
		{
			while($row = $result->fetch())
			{
				$encode[] = $row;
			}
			return $encode[0];
		}else{
			return '';
		}

		

	}
}
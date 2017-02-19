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


	public function selectAllUser($conn)
	{
		$encode = array();

		$SQL = "SELECT idt_usuario, nme_usuario, nme_perfil, tel_usuario, camada_usuario, email_usuario 
				FROM patrimonio_uiot.tb_usuario INNER JOIN patrimonio_uiot.tb_perfil ON idt_perfil = cod_perfil;";

		$result = $conn->query($SQL);

		if($result->rowCount() > 0)
		{
			while($row = $result->fetch())
			{
				$encode[] = $row;
			}
			return $encode;
		}else{
			return '';
		}
	}

	public function selectUser($conn, $userId)
	{
		$encode = array();

		$SQL = "SELECT idt_usuario, nme_usuario, nme_perfil, tel_usuario, camada_usuario, email_usuario 
				FROM patrimonio_uiot.tb_usuario
				INNER JOIN patrimonio_uiot.tb_perfil ON idt_perfil = cod_perfil
				WHERE idt_usuario = $userId;";

		$result = $conn->query($SQL);


		if($result->rowCount() > 0)
		{
			while($row = $result->fetch())
			{
				$encode[] = $row;
			}
			return $encode;
		}else{
			return '';
		}		
	}

	public function insertUser($conn, $userdId)
	{

		$SQL = "INSERT INTO patrimonio_uiot.tb_usuario (cod_perfil,nme_usuario,tel_usuario,
			   camada_usuario,email_usuario) VALUES ('$userdId->cod_perfil','$userdId->nme_usuario',
			   '$userdId->tel_usuario','$userdId->camada_usuario','$userdId->email_usuario');";

		$stmt = $conn->prepare($SQL);
		$stmt->execute();

		if($stmt == true){
			return 1;
		}else{
			return 0;
		}

		$stmt = null;
		$connection = null;
	}


	public function deleteUser($conn, $userdId)
	{
		$SQL = "DELETE FROM patrimonio_uiot.tb_usuario WHERE idt_usuario = $userdId";
		$stmt = $conn->prepare($SQL);
        $stmt->execute();

        if($stmt == true){
            return 1;
        }else{
            return 0;
        }
	}

	public function selectProdsUser($conn, $userId)
	{
		$encode = array();

		$SQL = "SELECT  nme_produto,prod.cod_produto,qtd_retirada_saida,dta_saida,usr_cad_saida 
				FROM patrimonio_uiot.tb_saida AS saida
				INNER JOIN patrimonio_uiot.tb_produto AS prod ON idt_produto = saida.cod_produto
				INNER JOIN patrimonio_uiot.tb_usuario ON idt_usuario = saida.cod_usuario
				WHERE idt_usuario = $userId;";

		$result = $conn->query($SQL);

		if($result->rowCount() > 0)
		{
			while($row = $result->fetch())
			{
				$encode[] = $row;
			}
			return $encode;
		}else{
			return '';
		}
	
	}

}
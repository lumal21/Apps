<?php
/**
 * User: Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Date: 14/01/17
 * Time: 18:00
 */

class ProductDB
{

	public function selectAllProduct($conn)
	{
		$encode = array();

		$SQL = "SELECT idt_produto, nme_produto, marca_produto, nme_status_prod, caixa_produto, armario_produto, cod_produto, qtd_produto FROM patrimonio_uiot.tb_produto INNER JOIN patrimonio_uiot.tb_status_prod ON idt_status_prod = cod_status_prod;";

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

	public function selectProduct($conn, $prodId)
	{
		$encode = array();

		$SQL = "SELECT idt_produto, nme_produto, marca_produto, nme_status_prod, caixa_produto, armario_produto, 
				cod_produto, qtd_produto, dta_cad_produto, usr_cad_produto FROM patrimonio_uiot.tb_produto 
				INNER JOIN patrimonio_uiot.tb_status_prod ON idt_status_prod = cod_status_prod 
				WHERE idt_produto = $prodId;";

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



	public function insertProduct($conn, $prodP, $currentDate)
	{

		$SQL = "INSERT INTO patrimonio_uiot.tb_produto (cod_status_prod, nme_produto, marca_produto, dta_cad_produto,
		usr_cad_produto,caixa_produto, armario_produto, cod_produto, qtd_produto) VALUES (1, '$prodP->nome', 
		'$prodP->marca','$currentDate', 'Alex Alexandre', '$prodP->caixa', '$prodP->armario', '$prodP->codigo', '2')";

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


	public function deleteProduct($conn, $prodId)
	{
		$SQL_SAIDA = "DELETE FROM patrimonio_uiot.tb_saida WHERE cod_produto = $prodId";
		$stmt = $conn->prepare($SQL_SAIDA);
        $stmt->execute();

		$SQL = "DELETE FROM patrimonio_uiot.tb_produto WHERE idt_produto = $prodId";
		$stmt = $conn->prepare($SQL);
        $stmt->execute();

        if($stmt == true){
            return 1;
        }else{
            return 0;
        }
	}


	public function historyProduct($conn, $prodId)
	{
	
		$encode = array();

		$SQL = "SELECT nme_usuario,camada_usuario AS usr_layer,email_usuario,usr_cad_saida,qtd_retirada_saida, dta_saida, tel_usuario 
				FROM patrimonio_uiot.tb_produto AS prod
				INNER JOIN patrimonio_uiot.tb_status_prod ON idt_status_prod = cod_status_prod
				INNER JOIN patrimonio_uiot.tb_saida AS saida ON prod.idt_produto = saida.cod_produto
				INNER JOIN patrimonio_uiot.tb_usuario ON idt_usuario = cod_usuario
				WHERE idt_produto = $prodId;";

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
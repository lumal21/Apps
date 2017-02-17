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

	public function historyProduct($conn, $prodId)
	{
		$encode = array();

		$SQL = "SELECT nme_usuario,camada_usuario,email_usuario,usr_cad_saida,qtd_retirada_saida, dta_saida 
				FROM patrimonio_uiot.tb_produto as prod
				inner join patrimonio_uiot.tb_status_prod on idt_status_prod = cod_status_prod
				inner join patrimonio_uiot.tb_saida as saida on prod.idt_produto = saida.cod_produto
				INNER JOIN patrimonio_uiot.tb_usuario on idt_usuario = cod_usuario
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
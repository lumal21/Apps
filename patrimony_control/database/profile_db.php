<?php
/**
 * User: Alex Alexandre
 * E-mail: alex.alexandre@uiot.org
 * Date: 14/01/17
 * Time: 00:24
 */

class ProfileDataBase{

    public function select_all_profile($conn){
        $sql = "SELECT * FROM tb_perfil ORDER BY nme_profile";
        $result = $conn->query($sql);

        if($result->rowCount() > 0){
            while($row = $result->fetch()){
                $encode[]= $row;
            }
        }
        return $encode;
    }

    public function insert_profile($conn, $param){
        $stmt = $conn->prepare("INSERT INTO tb_perfil (nme_perfil) VALUE  (:nme_profile)");
        $stmt->bindParam(':nme_profile', $param['nme_profile']);
        $stmt->execute();

        if($stmt == true){
            return true;
        }else{
            return false;
        }

        $stmt = null;
        $connection = null;
    }

    public function edit_profile(){

    }

    public function delete_profile($conn, $idt){
        $stmt = $conn->prepare("DELETE FROM db_simple_school_bus.tb_school WHERE idt_school = :idt");
        $stmt->bindParam(':idt', $idt);
        $stmt->execute();

        if($stmt == true){
            return true;
        }else{
            return false;
        }
    }
}
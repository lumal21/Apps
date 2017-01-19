-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema patrimonio_uiot
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema patrimonio_uiot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `patrimonio_uiot` DEFAULT CHARACTER SET utf8 ;
USE `patrimonio_uiot` ;

-- -----------------------------------------------------
-- Table `patrimonio_uiot`.`tb_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `patrimonio_uiot`.`tb_perfil` (
  `idt_perfil` INT NOT NULL AUTO_INCREMENT,
  `nme_perfil` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idt_perfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `patrimonio_uiot`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `patrimonio_uiot`.`tb_usuario` (
  `idt_usuario` INT NOT NULL AUTO_INCREMENT,
  `cod_perfil` INT NOT NULL,
  `nme_usuario` VARCHAR(60) NOT NULL,
  `tel_usuario` VARCHAR(15) NOT NULL,
  `camada_usuario` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idt_usuario`),
  INDEX `fk_tb_usuario_tb_perfil1_idx` (`cod_perfil` ASC),
  CONSTRAINT `fk_tb_usuario_tb_perfil1`
    FOREIGN KEY (`cod_perfil`)
    REFERENCES `patrimonio_uiot`.`tb_perfil` (`idt_perfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `patrimonio_uiot`.`tb_login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `patrimonio_uiot`.`tb_login` (
  `idt_login` INT NOT NULL AUTO_INCREMENT,
  `usr_login` VARCHAR(45) NOT NULL,
  `psw_login` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idt_login`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `patrimonio_uiot`.`tb_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `patrimonio_uiot`.`tb_produto` (
  `idt_produto` INT NOT NULL AUTO_INCREMENT,
  `nme_produto` VARCHAR(60) NOT NULL,
  `marca_produto` VARCHAR(60) NOT NULL,
  `dta_cad_produto` DATE NOT NULL,
  `usr_cad_produto` VARCHAR(60) NOT NULL,
  `caixa_produto` VARCHAR(45) NOT NULL,
  `armario_produto` VARCHAR(45) NOT NULL,
  `cod_produto` VARCHAR(45) NOT NULL,
  `qtd_produto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idt_produto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `patrimonio_uiot`.`tb_estragado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `patrimonio_uiot`.`tb_estragado` (
  `idt_estragado` INT NOT NULL AUTO_INCREMENT,
  `cod_produto` INT NOT NULL,
  `cod_usuario` INT NOT NULL,
  `qtd_estragado` INT NOT NULL,
  `dta_estragado` DATE NOT NULL,
  `motivo_estragado` VARCHAR(500) NOT NULL,
  `usr_cad_estragado` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idt_estragado`),
  INDEX `fk_tb_estragado_tb_produto1_idx` (`cod_produto` ASC),
  INDEX `fk_tb_estragado_tb_usuario1_idx` (`cod_usuario` ASC),
  CONSTRAINT `fk_tb_estragado_tb_produto1`
    FOREIGN KEY (`cod_produto`)
    REFERENCES `patrimonio_uiot`.`tb_produto` (`idt_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_estragado_tb_usuario1`
    FOREIGN KEY (`cod_usuario`)
    REFERENCES `patrimonio_uiot`.`tb_usuario` (`idt_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `patrimonio_uiot`.`tb_saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `patrimonio_uiot`.`tb_saida` (
  `idt_saida` INT NOT NULL AUTO_INCREMENT,
  `cod_produto` INT NOT NULL,
  `cod_usuario` INT NOT NULL,
  `usr_cad_saida` VARCHAR(60) NOT NULL,
  `qtd_retirada_saida` INT NOT NULL,
  PRIMARY KEY (`idt_saida`),
  INDEX `fk_tb_saida_tb_produto1_idx` (`cod_produto` ASC),
  INDEX `fk_tb_saida_tb_usuario1_idx` (`cod_usuario` ASC),
  CONSTRAINT `fk_tb_saida_tb_produto1`
    FOREIGN KEY (`cod_produto`)
    REFERENCES `patrimonio_uiot`.`tb_produto` (`idt_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_saida_tb_usuario1`
    FOREIGN KEY (`cod_usuario`)
    REFERENCES `patrimonio_uiot`.`tb_usuario` (`idt_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

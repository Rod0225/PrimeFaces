-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TAREA1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TAREA1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TAREA1` DEFAULT CHARACTER SET utf8 ;
USE `TAREA1` ;

-- -----------------------------------------------------
-- Table `TAREA1`.`TBL_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`TBL_USER` (
  `User` VARCHAR(50) NOT NULL,
  `Nombre` VARCHAR(100) NOT NULL,
  `Correo` VARCHAR(100) NOT NULL,
  `Password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`User`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAREA1`.`TBL_ROL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`TBL_ROL` (
  `ROL` VARCHAR(50) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ROL`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAREA1`.`TBL_USER_has_TBL_ROL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAREA1`.`TBL_USER_has_TBL_ROL` (
  `TBL_USER_User` VARCHAR(50) NOT NULL,
  `TBL_ROL_ROL` VARCHAR(50) NOT NULL,
  `BEGIN` DATETIME NOT NULL,
  `END` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TBL_USER_User`, `TBL_ROL_ROL`),
  CONSTRAINT `fk_TBL_USER_has_TBL_ROL_TBL_USER`
    FOREIGN KEY (`TBL_USER_User`)
    REFERENCES `TAREA1`.`TBL_USER` (`User`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TBL_USER_has_TBL_ROL_TBL_ROL1`
    FOREIGN KEY (`TBL_ROL_ROL`)
    REFERENCES `TAREA1`.`TBL_ROL` (`ROL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_TBL_USER_has_TBL_ROL_TBL_ROL1_idx` ON `TAREA1`.`TBL_USER_has_TBL_ROL` (`TBL_ROL_ROL` ASC) VISIBLE;

CREATE INDEX `fk_TBL_USER_has_TBL_ROL_TBL_USER_idx` ON `TAREA1`.`TBL_USER_has_TBL_ROL` (`TBL_USER_User` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

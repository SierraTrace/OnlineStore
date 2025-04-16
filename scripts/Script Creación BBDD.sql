
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `onlinestore` ;

-- -----------------------------------------------------
-- BBDD onlinestore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `onlinestore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `onlinestore` ;

-- -----------------------------------------------------
-- Table `onlinestore`.`articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onlinestore`.`articulo` ;

CREATE TABLE IF NOT EXISTS `onlinestore`.`articulo` (
  `idArticulo` INT NOT NULL AUTO_INCREMENT,
  `codigoArticulo` VARCHAR(20) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `precioVenta` FLOAT NOT NULL,
  `gastosEnvio` FLOAT NOT NULL,
  `tiempoPreparacion` INT NOT NULL,
  PRIMARY KEY (`codigoArticulo`),
  UNIQUE KEY `idArticulo_UNIQUE` (`idArticulo`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `onlinestore`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onlinestore`.`cliente` ;

CREATE TABLE IF NOT EXISTS `onlinestore`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(30) NOT NULL,
  `nif` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(70) NOT NULL,
  `domicilio` VARCHAR(100) NOT NULL,
  `tipoCliente` ENUM('PREMIUM', 'ESTANDAR') NOT NULL,
  `descuento` INT NULL DEFAULT NULL,
  `cuotaAnual` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `email` ON `onlinestore`.`cliente` (`email` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `onlinestore`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onlinestore`.`pedido` ;

CREATE TABLE IF NOT EXISTS `onlinestore`.`pedido` (
  `numeroPedido` INT NOT NULL AUTO_INCREMENT,
  `codigoArticulo` VARCHAR(20) NOT NULL,
  `emailCliente` VARCHAR(30) NOT NULL,
  `cantidadArticulos` INT NOT NULL,
  `precioTotal` FLOAT NOT NULL,
  `fechaPedido` DATETIME NOT NULL,
  `estado` ENUM('ENVIADO', 'PENDIENTE') NOT NULL,
  PRIMARY KEY (`numeroPedido`),
  CONSTRAINT `pedido_ibfk_1`
    FOREIGN KEY (`codigoArticulo`)
    REFERENCES `onlinestore`.`articulo` (`codigoArticulo`),
  CONSTRAINT `pedido_ibfk_2`
    FOREIGN KEY (`emailCliente`)
    REFERENCES `onlinestore`.`cliente` (`email`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `codigoArticulo` ON `onlinestore`.`pedido` (`codigoArticulo` ASC) VISIBLE;
CREATE INDEX `emailCliente` ON `onlinestore`.`pedido` (`emailCliente` ASC);


DROP DATABASE `restaurantes`;
CREATE DATABASE `restaurantes`;
USE `restaurantes` ;


CREATE TABLE IF NOT EXISTS `restaurantes`.`Cliente` (
  `CPF` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CPF`)
  );



CREATE TABLE IF NOT EXISTS `restaurantes`.`Restaurante` (
  `CNPJ` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Endereco` VARCHAR(45) NOT NULL,
  `qtdFuncionarios` INT NULL,
  PRIMARY KEY (`CNPJ`)
  );



CREATE TABLE IF NOT EXISTS `restaurantes`.`Atendente` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `Restaurante_CNPJ` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `fk_Atendente_Restaurante1`
    FOREIGN KEY (`Restaurante_CNPJ`)
    REFERENCES `restaurantes`.`Restaurante` (`CNPJ`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
    );




CREATE TABLE IF NOT EXISTS `restaurantes`.`Cozinheiro` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `Restaurante_CNPJ` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `fk_Cozinheiro_Restaurante1`
    FOREIGN KEY (`Restaurante_CNPJ`)
    REFERENCES `restaurantes`.`Restaurante` (`CNPJ`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );


CREATE TABLE IF NOT EXISTS `restaurantes`.`Cardapio` (
  `idOpcao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `valor` DOUBLE NOT NULL,
  PRIMARY KEY (`idOpcao`)
  );



CREATE TABLE IF NOT EXISTS `restaurantes`.`Mesa` (
  `idMesa` INT NOT NULL AUTO_INCREMENT,
  `qtdLugares` INT NOT NULL,
  `vazia` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`idMesa`)
);


CREATE TABLE IF NOT EXISTS `restaurantes`.`Reserva` (
  `Cliente_CPF` INT NOT NULL,
  `Mesa_idMesa` INT NOT NULL,
  `data` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`Cliente_CPF`, `Mesa_idMesa`),
  CONSTRAINT `fk_Cliente_has_Mesa_Cliente`
    FOREIGN KEY (`Cliente_CPF`)
    REFERENCES `restaurantes`.`Cliente` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Cliente_has_Mesa_Mesa1`
    FOREIGN KEY (`Mesa_idMesa`)
    REFERENCES `restaurantes`.`Mesa` (`idMesa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);



CREATE TABLE IF NOT EXISTS `restaurantes`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NOT NULL,
  `Mesa_idMesa` INT NOT NULL,
  `Cardapio_idOpcao` INT NOT NULL,
  PRIMARY KEY (`idPedido`),
  CONSTRAINT `fk_Pedido_Mesa1`
    FOREIGN KEY (`Mesa_idMesa`)
    REFERENCES `restaurantes`.`Mesa` (`idMesa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Pedido_Cardapio1`
    FOREIGN KEY (`Cardapio_idOpcao`)
    REFERENCES `restaurantes`.`Cardapio` (`idOpcao`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
 );



CREATE TABLE IF NOT EXISTS `restaurantes`.`Conta` (
  `idConta` INT NOT NULL AUTO_INCREMENT,
  `valorTotal` VARCHAR(45) NOT NULL,
  `Mesa_idMesa` INT NOT NULL,
  PRIMARY KEY (`idConta`),
  CONSTRAINT `fk_Conta_Mesa1`
    FOREIGN KEY (`Mesa_idMesa`)
    REFERENCES `restaurantes`.`Mesa` (`idMesa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
  );

INSERT INTO Restaurante (CNPJ,nome,endereco) values(555,"Restaurante Inatel", "Rua 2");
INSERT INTO Cardapio VALUES (idOpcao, 'Filé Mignon com Risoto', 15.99),
(idOpcao, 'Frango à Parmegiana', 12.99),
(idOpcao, 'Bife à Milanesa', 14.99),
(idOpcao, 'Salmão Grelhado', 16.99),
(idOpcao, 'Espaguete à Bolonhesa', 13.99),
(idOpcao, 'Risoto de Cogumelos', 11.99),
(idOpcao, 'Strogonoff de Carne', 17.99),
(idOpcao, 'Lasanha à Bolonhesa', 18.99),
(idOpcao, 'Picadinho de Carne', 10.99),
(idOpcao, 'Peixe ao Molho de Maracujá', 9.99),
(idOpcao, 'Suco de Laranja', 4.99),
(idOpcao, 'Refrigerante', 3.99),
(idOpcao, 'Água Mineral', 2.99),
(idOpcao, 'Cerveja', 5.99),
(idOpcao, 'Vinho Tinto', 6.99),
(idOpcao, 'Pudim de Leite', 7.99),
(idOpcao, 'Sorvete de Chocolate', 8.99),
(idOpcao, 'Torta de Limão', 6.99),
(idOpcao, 'Mousse de Maracujá', 5.99),
(idOpcao, 'Brigadeiro', 9.99);

INSERT INTO `restaurantes`.`Mesa` (`idMesa`, `qtdLugares`, `vazia`) VALUES
(idMesa, 4, 'Sim'),
(idMesa, 6, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 8, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 6, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 6, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 8, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 4, 'Sim'),
(idMesa, 6, 'Sim'),
(idMesa, 2, 'Sim'),
(idMesa, 4, 'Sim');

#função para calcular o valor dos pedidos
DELIMITER $$
CREATE FUNCTION calcularTotalPedidoPorMesa(idMesa INT) RETURNS DOUBLE
DETERMINISTIC
BEGIN
  DECLARE total DOUBLE;

  SELECT SUM(valor) INTO total
  FROM Pedido
  WHERE Mesa_idMesa = idMesa;

  RETURN total;
END $$
DELIMITER ;


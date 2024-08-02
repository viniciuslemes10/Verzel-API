CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `nome_completo` VARCHAR(255) DEFAULT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `conta_nao_expirada` BIT(1) DEFAULT TRUE,
  `conta_nao_bloqueada` BIT(1) DEFAULT TRUE,
  `credenciais_nao_expiradas` BIT(1) DEFAULT TRUE,
  `habilitado` BIT(1) DEFAULT TRUE,
  `codigo_recuperacao` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB;

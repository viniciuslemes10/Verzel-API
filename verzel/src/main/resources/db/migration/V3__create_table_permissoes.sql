CREATE TABLE IF NOT EXISTS `permissoes` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_descricao` (`descricao`)
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS `user_permission` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `id_user` INT NOT NULL,
  `id_permission` INT NOT NULL,
  PRIMARY KEY (`id_user`,`id_permission`),
  KEY `id` (`id`),
  KEY `id_permission` (`id_permission`),
  CONSTRAINT `fk_user_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_permission_id_permission` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
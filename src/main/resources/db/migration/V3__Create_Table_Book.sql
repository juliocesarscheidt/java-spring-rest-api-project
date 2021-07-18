CREATE TABLE `book` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `author` longtext,
  `launch_date` datetime(6) NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `title` longtext,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated_at` datetime NULL,
  `deleted_at` datetime NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

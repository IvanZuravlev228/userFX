CREATE DATABASE `store`;

CREATE TABLE `store`.`users` (
                                 `id` BIGINT(11) NOT NULL,
                                 `login` VARCHAR(255) NOT NULL,
                                 `password` VARCHAR(255) NOT NULL,
                                 PRIMARY KEY (`id`));

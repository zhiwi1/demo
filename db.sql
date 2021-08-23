
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `first_project` DEFAULT CHARACTER SET utf8 ;
USE `first_project` ;

CREATE TABLE IF NOT EXISTS `first_project`.`users` (
  FULLTEXT KEY `ft1` (`login`,`email`),
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(100) NOT NULL ,
  `email` VARCHAR(255) NOT NULL,
  `count_of_solve` INT NOT NULL DEFAULT 0,
  `rates_of_solve` ENUM('NEWBIE', 'STUDENT', 'HARDWORKER', 'PROFESSIONAL') NULL DEFAULT 'NEWBIE',
  `role` ENUM('ADMIN', 'USER') NOT NULL,
  `password_hash` VARCHAR(100) NOT NULL,
  `salt` VARCHAR(100) NOT NULL,
  `status` ENUM('NORMAL', 'BLOCKED') NOT NULL,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  PRIMARY KEY (`id`))

ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `first_project`.`tasks` (
  FULLTEXT KEY `ft2` (`title`,`content`),
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `user_id` BIGINT NOT NULL,
  `complexity` INT NOT NULL,
  `count_for_solve` INT NOT NULL DEFAULT 0,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  CONSTRAINT `user_foreign_key`
    FOREIGN KEY (`user_id`)
    REFERENCES `first_project`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `first_project`.`answers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `task_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `answer_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_key`
    FOREIGN KEY (`user_id`)
    REFERENCES `first_project`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `task_key`
    FOREIGN KEY (`task_id`)
    REFERENCES `first_project`.`tasks` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `first_project`.`comments` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `comment` TEXT NULL,
  `task_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `task_comment_key`
    FOREIGN KEY (`task_id`)
    REFERENCES `first_project`.`tasks` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_comment_key`
    FOREIGN KEY (`user_id`)
    REFERENCES `first_project`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `first_project` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


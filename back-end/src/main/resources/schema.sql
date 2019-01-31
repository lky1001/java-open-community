CREATE DATABASE `comm-dev` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

CREATE TABLE IF NOT EXISTS `comm-dev`.`users` (
`id` INT NOT NULL AUTO_INCREMENT,
`user_id` VARCHAR(100) NULL,
`user_pw` VARCHAR(1000) NULL,
`nickname` VARCHAR(10) NULL,
`profile_image` VARCHAR(1000) NULL,
`point` INT NULL,
`level` INT NULL,
`type` VARCHAR(45) NULL,
`created` DATETIME NULL,
`updated` DATETIME NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comm-dev`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comm-dev`.`categories` (
`id` INT NOT NULL AUTO_INCREMENT,
`category` VARCHAR(45) NULL,
`created` DATETIME NULL,
`updated` DATETIME NULL,
`deleted` DATETIME NULL,
PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comm-dev`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comm-dev`.`posts` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`title` VARCHAR(100) NULL,
`content` TEXT NULL,
`votes` INT NULL,
`answers` INT NULL,
`views` INT NULL,
`created` DATETIME NULL,
`updated` DATETIME NULL,
`user_id` INT NOT NULL,
`category_id` INT NOT NULL,
PRIMARY KEY (`id`),
INDEX `fk_posts_users_idx` (`user_id` ASC),
INDEX `fk_posts_categories1_idx` (`category_id` ASC),
CONSTRAINT `fk_posts_users`
FOREIGN KEY (`user_id`)
REFERENCES `comm-dev`.`users` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `fk_posts_categories1`
FOREIGN KEY (`category_id`)
REFERENCES `comm-dev`.`categories` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comm-dev`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comm-dev`.`answers` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`answer` TEXT NULL,
`likes` INT NULL,
`adopt` TINYINT(1) NULL,
`created` DATETIME NULL,
`updated` DATETIME NULL,
`post_id` BIGINT NOT NULL,
`user_id` INT NOT NULL,
PRIMARY KEY (`id`),
INDEX `fk_answers_posts1_idx` (`post_id` ASC),
INDEX `fk_answers_users1_idx` (`user_id` ASC),
CONSTRAINT `fk_answers_posts1`
FOREIGN KEY (`post_id`)
REFERENCES `comm-dev`.`posts` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `fk_answers_users1`
FOREIGN KEY (`user_id`)
REFERENCES `comm-dev`.`users` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comm-dev`.`post_votes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comm-dev`.`post_votes` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`post_id` BIGINT NOT NULL,
`user_id` INT NOT NULL,
`created` DATETIME NULL,
PRIMARY KEY (`id`),
INDEX `fk_post_votes_posts1_idx` (`post_id` ASC),
INDEX `fk_post_votes_users1_idx` (`user_id` ASC),
CONSTRAINT `fk_post_votes_posts1`
 FOREIGN KEY (`post_id`)
   REFERENCES `comm-dev`.`posts` (`id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
CONSTRAINT `fk_post_votes_users1`
 FOREIGN KEY (`user_id`)
   REFERENCES `comm-dev`.`users` (`id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comm-dev`.`answer_likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comm-dev`.`answer_likes` (
 `id` BIGINT NOT NULL AUTO_INCREMENT,
 `answer_id` BIGINT NOT NULL,
 `user_id` INT NOT NULL,
 PRIMARY KEY (`id`),
 INDEX `fk_answer_likes_answers1_idx` (`answer_id` ASC),
 INDEX `fk_answer_likes_users1_idx` (`user_id` ASC),
 CONSTRAINT `fk_answer_likes_answers1`
   FOREIGN KEY (`answer_id`)
     REFERENCES `comm-dev`.`answers` (`id`)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION,
 CONSTRAINT `fk_answer_likes_users1`
   FOREIGN KEY (`user_id`)
     REFERENCES `comm-dev`.`users` (`id`)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION)
ENGINE = InnoDB;

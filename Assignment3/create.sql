DROP SCHEMA IF EXISTS `cs5200_spring2020_chauhan`;

CREATE SCHEMA `cs5200_spring2020_chauhan`;

CREATE TABLE `cs5200_spring2020_chauhan`.`person` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `firstname` VARCHAR(40) NOT NULL,
 `lastname` VARCHAR(40) NOT NULL,
 `username` VARCHAR(30) NOT NULL,
 `password` VARCHAR(40) NOT NULL,
 `email` VARCHAR(50) NOT NULL,
 `dob` DATE,
 PRIMARY KEY (`id`));
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`user` (
 `id` INT NOT NULL,
 `user_agreement` BOOLEAN,
 `approved_user` BOOLEAN,
 `person_id` INT NOT NULL,
  PRIMARY KEY(`id`),
  CONSTRAINT user_person_generalization FOREIGN KEY (`person_id`)
			REFERENCES person(id) ON DELETE CASCADE
	);

CREATE TABLE `cs5200_spring2020_chauhan`.`developer` (
 `id` INT NOT NULL,
 `developer_key` VARCHAR(20),
 `person_id` INT NOT NULL,
  PRIMARY KEY(`id`),
  CONSTRAINT developer_person_generalization FOREIGN KEY (`person_id`)
			REFERENCES person(id) ON DELETE CASCADE
	);
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`phone` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `phone` VARCHAR(14) NOT NULL,
 `primary` BOOLEAN,
 `pid` INT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT uc_person_phone UNIQUE( `phone`, `pid`),
  CONSTRAINT phone_person FOREIGN KEY (`pid`)
			REFERENCES person(id) ON DELETE CASCADE
	);

 CREATE TABLE `cs5200_spring2020_chauhan`.`address` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `street1` VARCHAR(80) NOT NULL,
 `street2` VARCHAR(80),
 `city` VARCHAR(50) NOT NULL,
 `state` VARCHAR(50),
 `zip` VARCHAR(10) NOT NULL,
 `primary` BOOLEAN,
 `pid` INT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT uc_person_address UNIQUE( `street1`, `pid`),
  CONSTRAINT address_person FOREIGN KEY (`pid`)
			REFERENCES person(id) ON DELETE CASCADE
	);
	

	
CREATE TABLE `cs5200_spring2020_chauhan`.`website` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(80) NOT NULL,
 `description` VARCHAR(200),
 `created` DATE NOT NULL,
 `updated` DATE,
 `visits` INT,
 `dev_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT web_dev FOREIGN KEY (`dev_id`)
			REFERENCES developer(`id`) ON DELETE CASCADE
 );
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`page` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `title` VARCHAR(80) NOT NULL,
 `description` VARCHAR(200),
 `created` DATE NOT NULL,
 `updated` DATE,
 `views` INT,
 `website_id` INT, 
  PRIMARY KEY (`id`),
  CONSTRAINT page_website FOREIGN KEY (`website_id`)
			REFERENCES website(`id`) ON DELETE CASCADE
 );

  CREATE TABLE `cs5200_spring2020_chauhan`.`module` (
 `module_name` VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY(`module_name`)
 );

CREATE TABLE `cs5200_spring2020_chauhan`.`widget` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(80) NOT NULL,
 `width` INT,
 `height` INT,
 `cssClass` VARCHAR(50),
 `cssStyle` VARCHAR(50),
 `text` VARCHAR(100),
 `order` INT,
 `page_id` INT, 
 `dtype` VARCHAR(80),
 `url` VARCHAR(100),
 `shareable` BOOLEAN,
 `expandable` BOOLEAN,
 `src` VARCHAR(100),
 `html` VARCHAR(100),
  `size` INT DEFAULT 2,
  `posted_by` INT,
  `posted_on` DATE,
  `correct_ans` BOOLEAN,
  `up_votes` INT,
  `down_votes` INT,
  `asked_by` INT,
  `length` INT,
  `views` INT,
  `endorsed_by_instructor` BOOLEAN,
  `module` VARCHAR(20),
  `question_id` int,
  PRIMARY KEY (`id`),
  CONSTRAINT widget_page FOREIGN KEY (`page_id`) REFERENCES page(`id`) ON DELETE CASCADE, 
  CONSTRAINT check_dtype CHECK (dtype IN ('YouTube', 'Html', 'Widget', 'Heading', 'Image', 'Answer', 'Question')),
  CONSTRAINT widget_page_q FOREIGN KEY (`posted_by`) REFERENCES user(`id`) ON DELETE CASCADE, 
  CONSTRAINT widget_page_a FOREIGN KEY (`asked_by`) REFERENCES user(`id`) ON DELETE CASCADE, 
  CONSTRAINT widget_page_module FOREIGN KEY (`module`) REFERENCES module(`module_name`) ON DELETE CASCADE,
  CONSTRAINT widget_q FOREIGN KEY (`question_id`) REFERENCES widget(`id`) ON DELETE CASCADE
 
 );
 

 
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Project1');
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Project2');
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Assignment1');
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Assignment2');
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Quiz1');
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Exam');
 INSERT INTO `cs5200_spring2020_chauhan`.`module`(`module_name`) values ('Logistics');
 
 
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`priviledge` (
 `priviledge_name` VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY(`priviledge_name`)
 );
 
 INSERT INTO `cs5200_spring2020_chauhan`.`priviledge`(`priviledge_name`) values ('create');
 INSERT INTO `cs5200_spring2020_chauhan`.`priviledge`(`priviledge_name`) values ('read');
 INSERT INTO `cs5200_spring2020_chauhan`.`priviledge`(`priviledge_name`) values ('update');
 INSERT INTO `cs5200_spring2020_chauhan`.`priviledge`(`priviledge_name`) values ('delete');
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`role` (
 `role_name` VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY(`role_name`)
 );
 
 INSERT INTO `cs5200_spring2020_chauhan`.`role`(`role_name`) values ('owner');
 INSERT INTO `cs5200_spring2020_chauhan`.`role`(`role_name`) values ('admin');
 INSERT INTO `cs5200_spring2020_chauhan`.`role`(`role_name`) values ('writer');
 INSERT INTO `cs5200_spring2020_chauhan`.`role`(`role_name`) values ('editor');
 INSERT INTO `cs5200_spring2020_chauhan`.`role`(`role_name`) values ('reviewer');
 
 
 
 
 
 

 
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`website_priviledge` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `priviledge` VARCHAR(80) NOT NULL,
 `dev_id` INT NOT NULL,
 `website_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT dev_priviledge FOREIGN KEY (`dev_id`)
			REFERENCES developer(`id`) ON DELETE CASCADE,
  CONSTRAINT website_priviledge FOREIGN KEY (`website_id`)
			REFERENCES website(`id`) ON DELETE CASCADE,
  CONSTRAINT priviledge_name FOREIGN KEY (`priviledge`)
			REFERENCES priviledge(`priviledge_name`) ON DELETE CASCADE
 );
 
 CREATE TABLE `cs5200_spring2020_chauhan`.`website_role` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `role` VARCHAR(80) NOT NULL,
 `dev_id` INT NOT NULL,
 `website_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT dev_role FOREIGN KEY (`dev_id`)
			REFERENCES developer(`id`) ON DELETE CASCADE,
  CONSTRAINT website_role FOREIGN KEY (`website_id`)
			REFERENCES website(`id`) ON DELETE CASCADE,
  CONSTRAINT role_name FOREIGN KEY (`role`)
			REFERENCES role(`role_name`) ON DELETE CASCADE,
  CONSTRAINT uc_dev_website
				UNIQUE (`dev_id`, `website_id`)
			
 );
 
   CREATE TABLE `cs5200_spring2020_chauhan`.`page_role` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `role` VARCHAR(80) NOT NULL,
 `dev_id` INT NOT NULL,
 `page_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT dev_page_role FOREIGN KEY (`dev_id`)
			REFERENCES developer(`id`) ON DELETE CASCADE,
  CONSTRAINT page_role FOREIGN KEY (`page_id`)
			REFERENCES page(`id`) ON DELETE CASCADE,
  CONSTRAINT role_in_page_name FOREIGN KEY (`role`)
			REFERENCES role(`role_name`) ON DELETE CASCADE,
  CONSTRAINT uc_dev_page
			UNIQUE (`dev_id`, `page_id`)
 );

CREATE TABLE `cs5200_spring2020_chauhan`.`page_priviledge` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `priviledge` VARCHAR(80) NOT NULL,
 `dev_id` INT NOT NULL,
 `page_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT dev_page_priviledge FOREIGN KEY (`dev_id`)
			REFERENCES developer(`id`) ON DELETE CASCADE,
  CONSTRAINT page_priviledge FOREIGN KEY (`page_id`)
			REFERENCES page(`id`) ON DELETE CASCADE,
  CONSTRAINT priviledge_in_page_name FOREIGN KEY (`priviledge`)
			REFERENCES priviledge(`priviledge_name`)
 );
 
 




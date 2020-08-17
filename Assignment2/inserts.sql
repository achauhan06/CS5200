INSERT INTO `cs5200`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)
VALUES (12,'Alice','Wonder','alice','alice','alice@wonder.com',null);
INSERT INTO `cs5200`.`developer`(`id`,`developer_key`,`person_id`)
VALUES (12,'4321rewq',12);
INSERT INTO `cs5200`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)
VALUES (23,'Bob','Marley','bob','bob','bob@marley.com',null);
INSERT INTO `cs5200`.`developer`(`id`,`developer_key`,`person_id`)
VALUES (23,'5432trew',23);
INSERT INTO `cs5200`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)
VALUES (34,'Charles','Garcia','charlie','charlie','chuch@garcia.com',null);
INSERT INTO `cs5200`.`developer`(`id`,`developer_key`,`person_id`)
VALUES (34,'6543ytre',34);
INSERT INTO `cs5200`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)
VALUES (45,'Dan','Martin','dan','dan','dan@martin.com',null);
INSERT INTO `cs5200`.`user`(`id`,`user_agreement`,`person_id`)
VALUES (45,null,45);
INSERT INTO `cs5200`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)
VALUES (56,'Ed','Karaz','ed','ed','ed@kar.com',null);
INSERT INTO `cs5200`.`user`(`id`,`user_agreement`,`person_id`)
VALUES (56,null,56);
INSERT INTO `cs5200`.`phone`(`pid`,`phone`,`primary`)
VALUES (12, '123-234-3456' ,true);
INSERT INTO `cs5200`.`phone`(`pid`,`phone`,`primary`)
VALUES (12, '234-345-4566' ,false);
INSERT INTO `cs5200`.`phone`(`pid`,`phone`,`primary`)
VALUES (23, '345-456-5677' ,true);
INSERT INTO `cs5200`.`phone`(`pid`,`phone`,`primary`)
VALUES (34, '321-432-5435' ,true);
INSERT INTO `cs5200`.`phone`(`pid`,`phone`,`primary`)
VALUES (34, '432-432-5433' ,false);
INSERT INTO `cs5200`.`phone`(`pid`,`phone`,`primary`)
VALUES (34, '543-543-6544' ,false);
INSERT INTO `cs5200`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)
VALUES (12, '123 Adam St.', null, 'Alton', null, '01234' ,true);
INSERT INTO `cs5200`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)
VALUES (12, '234 Birch St.', null, 'Boston', null, '02345' ,false);
INSERT INTO `cs5200`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)
VALUES (23, '345 Charles St.', null, 'Chelms', null, '03455' ,true);
INSERT INTO `cs5200`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)
VALUES (23, '456 Down St.', null, 'Dalton', null, '04566' ,false);
INSERT INTO `cs5200`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)
VALUES (23, '543 East St.', null, 'Everett', null, '01112' ,true);
INSERT INTO `cs5200`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)
VALUES (34, '654 Frank St.', null, 'Foulton', null, '04322' ,true);
INSERT INTO `cs5200`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)
VALUES(123, 'Facebook','an online social media and social networking service',CURDATE(),CURDATE(),1234234,12); 
INSERT INTO `cs5200`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)
VALUES(234, 'Twitter','an online news and social networking service',CURDATE(),CURDATE(),4321543,23); 
INSERT INTO `cs5200`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)
VALUES(345, 'Wikipedia','a free online encyclopedia',CURDATE(),CURDATE(),3456654,34); 
INSERT INTO `cs5200`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)
VALUES(456, 'CNN','an American basic cable and satellite television news channel',CURDATE(),CURDATE(),6543345,12); 
INSERT INTO `cs5200`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)
VALUES(567, 'CNET','an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics',CURDATE(),CURDATE(),5433455,23); 
INSERT INTO `cs5200`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)
VALUES(678, 'Gizmodo','a design, technology, science and science fiction website that also writes articles on politics',CURDATE(),CURDATE(),4322345,34); 
INSERT INTO `cs5200`.`page`(`id`,`title`,`description`,`created`,`updated`,`views`,`website_id`)
VALUES(123, 'Home','Landing page',"2020-01-06",CURDATE(),123434,567); 
INSERT INTO `cs5200`.`page`(`id`,`title`,`description`,`created`,`updated`,`views`,`website_id`)
VALUES(234, 'About','Website description',"2020-01-06",CURDATE(),234545,678); 
INSERT INTO `cs5200`.`page`(`id`,`title`,`description`,`created`,`updated`,`views`,`website_id`)
VALUES(345, 'Contact','Addresses, phones, and contact info',"2020-01-06",CURDATE(),345656,345); 
INSERT INTO `cs5200`.`page`(`id`,`title`,`description`,`created`,`updated`,`views`,`website_id`)
VALUES(456, 'Preferences','Where users can configure their preferences',"2020-01-06",CURDATE(),456776,456); 
INSERT INTO `cs5200`.`page`(`id`,`title`,`description`,`created`,`updated`,`views`,`website_id`)
VALUES(567, 'Profile','Users can configure their personal information',"2020-01-06",CURDATE(),567878,567); 
INSERT INTO `cs5200`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`width`,`height`,`url`,`page_id`)
VALUES(123, 'head123', 'Heading', 'Welcome', 0, null, null, null, 123 );
INSERT INTO `cs5200`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`width`,`height`,`url`,`page_id`)
VALUES(234, 'post234', 'Html', '<p>Lorem</p>', 0, null, null, null, 234 );
INSERT INTO `cs5200`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`width`,`height`,`url`,`page_id`)
VALUES(345, 'head345', 'Heading', 'Hi', 1, null, null, null, 345 );
INSERT INTO `cs5200`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`width`,`height`,`url`,`page_id`)
VALUES(456, 'intro456', 'Html', '<h1>Hi</h1>', 2, null, null, null, 345 );
INSERT INTO `cs5200`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`width`,`height`,`url`,`page_id`)
VALUES(567, 'image345', 'Image', null, 3, 50, 100, '/img/567.png', 345 );
INSERT INTO `cs5200`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`width`,`height`,`url`,`page_id`)
VALUES(678, 'video456', 'YouTube', null, 0, 400, 300, 'https://youtu.be/h67VX51QXiQ', 456 );
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('owner', 12, 123);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('editor', 23, 123);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('admin', 34, 123);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('owner', 23, 234);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('editor', 34, 234);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('admin', 12, 234);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('owner', 34, 345);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('editor', 12, 345);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('admin', 23, 345);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('owner', 12, 456);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('editor', 23, 456);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('admin', 34, 456);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('owner', 23, 567);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('editor', 34, 567);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('admin', 12, 567);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('owner', 34, 678);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('editor', 12, 678);
INSERT INTO `cs5200`.`website_role`(`role`,`dev_id`,`website_id`) 
VALUES('admin', 23, 678);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('editor', 12, 123);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('reviewer', 23, 123);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('writer', 34, 123);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('editor', 23, 234);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('reviewer', 34, 234);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('writer', 12, 234);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('editor', 34, 345);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('reviewer', 12, 345);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('writer', 23, 345);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('editor', 12, 456);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('reviewer', 23, 456);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('writer', 34, 456);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('editor', 23, 567);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('reviewer', 34, 567);
INSERT INTO `cs5200`.`page_role`(`role`,`dev_id`,`page_id`) 
VALUES('writer', 12, 567);












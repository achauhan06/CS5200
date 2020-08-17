DELIMITER $$
CREATE TRIGGER `cs5200_spring2020_chauhan`.`after_website_role_insert`
AFTER INSERT ON `cs5200_spring2020_chauhan`.`website_role`  
FOR EACH ROW
BEGIN
   IF NEW.role = "owner" OR NEW.role = "admin" THEN 
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "create",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "delete",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
	ELSEIF NEW.role = "writer" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "create",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
	ELSEIF NEW.role = "editor" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
	ELSE
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
	END IF;
END$$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER `cs5200_spring2020_chauhan`.`after_website_role_update`
AFTER UPDATE ON `cs5200_spring2020_chauhan`.`website_role`  
FOR EACH ROW
BEGIN 
   DELETE FROM `cs5200_spring2020_chauhan`.`website_priviledge`  
   WHERE dev_id = OLD.dev_id AND website_id = OLD.website_id ;
   
   IF NEW.role = "owner" OR NEW.role = "admin" THEN 
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "create",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "update",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "delete",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
	ELSEIF NEW.role = "writer" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "create",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "update",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
	ELSEIF NEW.role = "editor" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       website_id = NEW.website_id ;
	ELSE
	   INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       website_id = OLD.website_id ;
	END IF;
END$$

DELIMITER ;


DELIMITER $$
CREATE TRIGGER `cs5200_spring2020_chauhan`.`after_website_role_delete`
AFTER DELETE ON `cs5200_spring2020_chauhan`.`website_role`  
FOR EACH ROW
BEGIN 
   DELETE FROM `cs5200_spring2020_chauhan`.`website_priviledge`  
   WHERE dev_id = OLD.dev_id AND website_id = OLD.website_id ;
   
END$$

DELIMITER ;




DELIMITER $$
CREATE TRIGGER `cs5200_spring2020_chauhan`.`after_page_role_delete`
AFTER DELETE ON `cs5200_spring2020_chauhan`.`page_role`  
FOR EACH ROW
BEGIN 
   DELETE FROM `cs5200_spring2020_chauhan`.`page_priviledge`  
   WHERE dev_id = OLD.dev_id AND page_id = OLD.page_id ;
   
END$$

DELIMITER ;


DELIMITER $$
CREATE TRIGGER `cs5200_spring2020_chauhan`.`after_page_role_update`
AFTER UPDATE ON `cs5200_spring2020_chauhan`.`page_role`  
FOR EACH ROW
BEGIN 
   DELETE FROM `cs5200_spring2020_chauhan`.`page_priviledge`  
   WHERE dev_id = OLD.dev_id AND page_id = OLD.page_id ;
   
   IF NEW.role = "owner" OR NEW.role = "admin" THEN 
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "create",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "update",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "delete",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
	ELSEIF NEW.role = "writer" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "create",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "update",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
	ELSEIF NEW.role = "editor" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
	ELSE
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = OLD.dev_id,
       page_id = OLD.page_id ;
	END IF;
END$$

DELIMITER ;


DELIMITER $$
CREATE TRIGGER `cs5200_spring2020_chauhan`.`after_page_role_insert`
AFTER INSERT ON `cs5200_spring2020_chauhan`.`page_role`  
FOR EACH ROW
BEGIN 
   
   IF NEW.role = "owner" OR NEW.role = "admin" THEN 
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "create",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "delete",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
	ELSEIF NEW.role = "writer" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "create",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
	ELSEIF NEW.role = "editor" THEN
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
       
       INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "update",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
	ELSE
	   INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`
	   set priviledge = "read",
       dev_id = NEW.dev_id,
       page_id = NEW.page_id ;
	END IF;
END$$

DELIMITER ;
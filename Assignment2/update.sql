UPDATE `cs5200`.`phone` set phone.phone = "333-444-5555" WHERE 
phone.primary = true AND pid = (
	Select p.id from (
		Select id from `cs5200`.`person` where person.username = "alice"
    ) as p
) ;

update `cs5200`.`widget` set widget.order = CASE `name`
 when "head345" then 3 
 when "intro456" then 2
 when "image345" then 1
 END WHERE widget.id > -1;
 

UPDATE `cs5200`.`page` set page.title = CONCAT( 'CNET - ', page.title) where page.website_id = (
 SELECT website.id from `cs5200`.`website`where website.name = "CNET" 
);


UPDATE `cs5200`.`page_role` role1, `cs5200`.`page_role` role2
SET role1.role = (@temp:=role1.role), role1.role  = role2.role, 
role2.role = @temp 
WHERE role1.page_id = 
(Select distinct p.id from (SELECT page_role.page_id as id from `cs5200`.`page` 
join `cs5200`.`website` on website.id = page.website_id 
JOIN `cs5200`.`page_role` on page_role.page_id = page.id
WHERE website.name = "CNET" AND page.title = "CNET - Home"
 ) as p)
AND role1.dev_id = 
(Select p.id from (SELECT person.id as id  from `cs5200`.`person` 
join `cs5200`.`developer` on person.id = developer.person_id
WHERE person.username="bob"
 ) as p) 
 AND role2.page_id = 
 (Select distinct p.id from (SELECT page_role.page_id as id from `cs5200`.`page` 
join `cs5200`.`website` on website.id = page.website_id 
JOIN `cs5200`.`page_role` on page_role.page_id = page.id
WHERE website.name = "CNET" AND page.title = "CNET - Home" 
 ) as p)
AND role2.dev_id = 
(Select p.id from (SELECT person.id as id  from `cs5200`.`person` 
join `cs5200`.`developer` on person.id = developer.person_id
WHERE person.username="charlie"
 ) as p); 
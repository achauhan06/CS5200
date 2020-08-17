SELECT * FROM `cs5200`.`developer`, `cs5200`.person  WHERE developer.person_id = person.id ;

SELECT * FROM `cs5200`.`developer`, `cs5200`.`person` WHERE developer.person_id = person.id AND developer.id = 34;

Select * from `cs5200`.`person` join `cs5200`.`developer` on person.id = developer.id 
Where developer.id in (    
Select website_role.dev_id from `cs5200`.`website` 
join `cs5200`.`website_role` on website.id = website_role.website_id where website.name = "Twitter" 
AND website_role.role <> "owner");

Select * from `cs5200`.`person` join `cs5200`.`developer` on person.id = developer.id 
Where developer.id in (select page_role.dev_id from `cs5200`.`page` join `cs5200`.`page_role` on 
page.id = page_role.page_id where page.views < 300000 AND role = "reviewer");

Select * from `cs5200`.`person` join `cs5200`.`developer` on person.id = developer.id 
join `cs5200`.`page_role` on page_role.dev_id = developer.id 
where page_role.role = "writer" AND page_role.page_id in
(Select page.id from `cs5200`.`website` join`cs5200`.`page` on page.website_id = website.id 
join `cs5200`.`widget` on widget.page_id = page.id 
where website.name = "CNET" AND widget.dtype = "Heading"); 

Select * from `cs5200`.`website` where website.visits = (
Select min(website.visits) from  `cs5200`.`website`);

Select website.name from `cs5200`.`website` where website.id = 678;

 Select website.name from 
 `cs5200`.`website` join  `cs5200`.`page` on
 page.website_id = website.id join  `cs5200`.`widget`  on widget.page_id = page.id join 
 `cs5200`.`page_role` on page.id = page_role.page_id  
 where widget.dtype = "YouTube" AND role = "reviewer" AND page_role.dev_id = (
 Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "bob"  
 );
 
 Select website.name from `cs5200`.`website` join `cs5200`.`website_role`
 on website.id = website_role.website_id where website_role.role = "owner" 
 AND website_role.dev_id = (
 Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "alice"
 );
 
 Select website.name from `cs5200`.`website` join `cs5200`.`website_role`
 on website.id = website_role.website_id where website_role.role = "admin" 
 AND website.visits > 6000000 AND website_role.dev_id = (
 Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "charlie"
 );
 
 
Select page.title from `cs5200`.`page` where page.views = (
Select max(views) from `cs5200`.`page`);

Select page.title from `cs5200`.`page` where page.id = 234;

Select page.title from `cs5200`.`page` join `cs5200`.`page_role` on 
page.id = page_role.page_id where page_role.role = "editor" and page_role.dev_id = (
  Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "alice");
 
Select sum(views) as TotalPageViews from `cs5200`.`page` join `cs5200`.`website` 
on website.id = page.website_id where website.name = "CNET";
 
Select avg(views) as AveragePageViews from `cs5200`.`page` join `cs5200`.`website` 
on website.id = page.website_id where website.name = "Wikipedia";

Select widget.name from `cs5200`.`widget` join `cs5200`.`page` on page.id = widget.page_id 
join `cs5200`.`website` on website.id = page.website_id where website.name = "CNET" and page.title="Home";

Select widget.name, page.title from `cs5200`.`widget` join `cs5200`.`page` on page.id = widget.page_id 
join `cs5200`.`website` on website.id = page.website_id where 
widget.dtype = "YouTube" and website.name = "CNN";

Select widget.name, page.title from `cs5200`.`widget` join `cs5200`.`page` on page.id = widget.page_id 
join `cs5200`.`website` on website.id = page.website_id join `cs5200`.`page_role` on 
page_role.page_id = page.id where dtype="Image" and page_role.role = "reviewer" AND page_role.dev_id = (
Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "alice"
);

Select count(widget.name) as NumberOfWidgets from `cs5200`.`widget` join `cs5200`.`page` on page.id = widget.page_id 
join `cs5200`.`website` on website.id = page.website_id where website.name = "Wikipedia";
 
 Select website.name from `cs5200`.`website` join `cs5200`.`website_priviledge` 
 on website.id = website_priviledge.website_id where website_priviledge.priviledge = "delete" AND 
 website_priviledge.dev_id = (
	Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "bob"
 );
 
 Select page.title from `cs5200`.`page` join `cs5200`.`page_priviledge` 
 on page.id = page_priviledge.page_id where page_priviledge.priviledge = "create" AND 
 page_priviledge.dev_id = (
	Select developer.id from `cs5200`.`person` join `cs5200`.`developer`
 on person.id = developer.person_id where person.username = "charlie"
 );
 
 


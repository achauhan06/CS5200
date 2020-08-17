CREATE VIEW `cs5200`.`deleveloper_roles_and_privileges` AS
Select distinct person.firstName as FirstName, person.lastName as LastName,  
person.username as Username, person.email as Email, website.name as WebsiteName, 
website.visits as WebsiteVisits, website.updated as WebsiteUpdatedDate, 
website_role.role as DeveloperRole, website_priviledge.priviledge as DeveloperPriviledge,
page.title as PageTitle, page.views as PageViews, page.updated as PageLastUpdated, page_role.role as PageRole,
page_priviledge.priviledge as PagePriviledge 
from `cs5200`.`person` join `cs5200`.`developer` 
on developer.person_id = person.id
join `cs5200`.`website_role` 
on developer.id = website_role.dev_id 
join `cs5200`.`website` on website.id = website_role.website_id and website_role.dev_id = developer.id
join `cs5200`.`page` on page.website_id = website.id 
join `cs5200`.`website_priviledge` 
on website_priviledge.website_id = website.id 
and website_priviledge.dev_id = developer.id 
join `cs5200`.`page_role` on page_role.page_id = page.id 
and page_role.dev_id = developer.id
join `cs5200`.`page_priviledge` 
on page.id = page_priviledge.page_id and page_priviledge.dev_id = developer.id
Delete from `cs5200`.`address` where address.primary = true AND pid = (
 Select person.id from  `cs5200`.`person` where person.username = "alice"
);


Delete from `cs5200`.`widget` where widget.id = (
	Select w.id from 
     ( 
       select widget.id from `cs5200`.`widget` 
	   join `cs5200`.`page` on widget.page_id = page.id
       where page.title = "Contact" 
       ORDER BY widget.order DESC LIMIT 1) as w
);



Delete from `cs5200`.`page` where page.id > -1 AND page.id in (
	select p.id from 
    (
      select page.id from `cs5200`.`page` 
	   join `cs5200`.`website` on page.website_id = website.id 
       where website.name = "Wikipedia" ORDER BY page.updated DESC LIMIT 1
       
    ) as p
);

Delete from `cs5200`.`website` where website.id = (
	select w.id from (
     Select website.id from `cs5200`.`website` where website.name = "CNET"
    ) as w
)




    
    
    

    
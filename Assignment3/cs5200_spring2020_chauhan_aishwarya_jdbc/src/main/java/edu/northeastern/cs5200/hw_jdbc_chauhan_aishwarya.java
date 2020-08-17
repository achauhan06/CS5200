package edu.northeastern.cs5200;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class hw_jdbc_chauhan_aishwarya {


    private static DeveloperDao developerDao;
    private static WebsiteDao websiteDao;
    private static PageDao pageDao;
    private static WidgetDao widgetDao;
    private static RoleDao roleDao;
    private static UserDao userDao;
    private static AddressDao addressDao;
    private static StoredProceduresDao storedProceduresDao;

    public static void main(String[] args) {
        createDevelopers();
        createUsers();
        createWebsites();
        createPages();
        createWidgets();
        createRolesForWebsite();
        createRolesForPages();
        updatePhone();
        updatePage();
        updateRoles();
        updateWidgets();
        deleteAddress();
        deleteWidget();
        deleteWebsite();
        deletePage();
        getUnansweredQuestions();
        endorsedUsersForWeek();
    }



    private static void createUsers() {

        userDao = new UserImpl();
        User user1 = new User(45,"Martin","Dan");
        user1.setUsername("dan");
        user1.setPassword("dan");
        user1.setEmail("dan@martin.com");
        user1.setUserId(45);
        userDao.createUser(user1);

        User user2 = new User(56,"Karaz","Ed");
        user2.setUsername("ed");
        user2.setPassword("ed");
        user2.setEmail("ed@kar.com");
        user2.setUserId(56);
        userDao.createUser(user2);

    }


    public static void createDevelopers() {

        developerDao =  new DeveloperImpl();

        Collection<Phone> phones1 = new ArrayList<>();
        Phone phone1 = new Phone("123-234-3456", true);
        phones1.add(phone1);
        phone1 = new Phone("234-345-4566", false);
        phones1.add(phone1);
        Collection<Address> addresses1 = new ArrayList<>();
        Address address1 = new Address("123 Adam St.", null, "Alton", null, "01234" ,true);
        addresses1.add(address1);
        address1 = new Address("234 Birch St.", null, "Boston", null, "02345" ,false);
        addresses1.add(address1);
        Developer developer1 = new Developer("4321rewq", 12,
                "Wonder", "Alice", "alice", "alice", "alice@wonder.com", null,
                addresses1,phones1);
        developer1.setDeveloperId(12);
        developerDao.createDeveloper(developer1);

        Collection<Phone> phones2 = new ArrayList<>();
        Phone phone2 = new Phone("345-456-5677", true);
        phones2.add(phone2);
        Collection<Address> addresses2 = new ArrayList<>();
        Address address2 = new Address("345 Charles St.", null, "Chelms", null, "03455" ,true);
        addresses2.add(address2);
        address2 = new Address("456 Down St.", null, "Dalton", null, "04566" ,false);
        addresses2.add(address2);
        address2 = new Address("543 East St.", null, "Everett", null, "01112" ,false);
        addresses2.add(address2);
        Developer developer2 = new Developer("5432trew", 23, "Marley", "Bob", "bob",
                "bob", "bob@marley.com", null, addresses2, phones2);
        developer2.setDeveloperId(23);
        developerDao.createDeveloper(developer2);

        Collection<Phone> phones3 = new ArrayList<>();
        Phone phone3 = new Phone("321-432-5435", true);
        phones3.add(phone3);
        phone3 = new Phone("432-432-5433", false);
        phones3.add(phone3);
        phone3 = new Phone("543-543-6544", false);
        phones3.add(phone3);

        Collection<Address> addresses3 = new ArrayList<>();
        Address address3 = new Address("654 Frank St.", null, "Foulton", null,  "04322", true);
        addresses3.add(address3);
        Developer developer3 = new Developer("6543ytre", 34, "Garcia", "Charles",
                "charlie", "charlie", "chuch@garcia.com", null, addresses3, phones3);
        developer3.setDeveloperId(34);
        developerDao.createDeveloper(developer3);


    }



    private static void createWebsites() {
        websiteDao = new WebsiteImpl();
        java.util.Date created = new java.util.Date();
        java.util.Date updated = new java.util.Date();
        Website website1 = new Website(123, "Facebook", "an online social media " +
                "and social networking service", new Date(created.getTime()),new Date(updated.getTime()), 1234234);
        websiteDao.createWebsiteForDeveloper(12, website1);

        Website website2 = new Website(234, "Twitter", "an online news " +
                "and social networking service", new Date(created.getTime()),new Date(updated.getTime()), 4321543);
        websiteDao.createWebsiteForDeveloper(23, website2);

        Website website3= new Website(345, "Wikipedia","a free online " +
                "encyclopedia",new Date(created.getTime()),new Date(updated.getTime()),3456654);
        websiteDao.createWebsiteForDeveloper(34, website3);

        Website website4= new Website(456, "CNN","an American basic cable " +
                "and satellite television news channel",new Date(created.getTime()),new Date(updated.getTime()),6543345);
        websiteDao.createWebsiteForDeveloper(12, website4);

        Website website5= new Website(567, "CNET","an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology " +
                "and consumer electronics",new Date(created.getTime()),new Date(updated.getTime()),5433455);
        websiteDao.createWebsiteForDeveloper(23, website5);

        Website website6= new Website(678, "Gizmodo","a design, technology, science and science fiction website " +
                "that also writes articles on politics",new Date(created.getTime()),new Date(updated.getTime()),4322345);
        websiteDao.createWebsiteForDeveloper(34, website6);

    }

    private static void createPages() {

        pageDao = new PageImpl();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,0,6,0,0,0);
        java.util.Date created = calendar.getTime();
        java.util.Date updated = new java.util.Date();
        Page page1 = new Page(123, "Home", "Landing Page", new Date(created.getTime()),new Date(updated.getTime()), 123434);
        pageDao.createPageForWebsite(567, page1);

        Page page2 = new Page(234, "About", "Website Description", new Date(created.getTime()),new Date(updated.getTime()), 234545);
        pageDao.createPageForWebsite(678, page2);

        Page page3 = new Page(345, "Contact", "Addresses, phones, and contact info", new Date(created.getTime()),new Date(updated.getTime()), 345656);
        pageDao.createPageForWebsite(345, page3);

        Page page4 = new Page(456, "Preferences", "Where users can configure their preference", new Date(created.getTime()),new Date(updated.getTime()), 456776);
        pageDao.createPageForWebsite(456, page4);

        Page page5 = new Page(567, "Profile", "Users can configure their personal information", new Date(created.getTime()),new Date(updated.getTime()), 567878);
        pageDao.createPageForWebsite(567, page5);
    }

    private static void createWidgets() {
        widgetDao = new WidgetImpl();
        Widget widget1 = new Widget(null, "head123", null, null, null, null, "Welcome",
                0, null, null, null, null, null, null, Type.Heading);
        widgetDao.createWidgetForPage(123, widget1);

        Widget widget2 = new Widget(null, "post234", null, null, null, null, "<p>Lorem</p>",
                0, null, null, null, null, null, null, Type.Html);
        widgetDao.createWidgetForPage(234, widget2);

        Widget widget3 = new Widget(null, "head345", null, null, null, null, "Hi",
                1, null, null, null, null, null, null, Type.Heading);
        widgetDao.createWidgetForPage(345, widget3);

        Widget widget4 = new Widget(null, "intro456", null, null, null, null, "<h1>Hi</h1>",
                2, null, null, null, null, null, null, Type.Html);
        widgetDao.createWidgetForPage(345, widget4);

        Widget widget5 = new Widget(null, "image345", 50, 100, null, null, null,
                3, "/img/567.png", null, null, null, null, null, Type.Image);
        widgetDao.createWidgetForPage(345, widget5);

        Widget widget6 = new Widget(null, "video456", 400, 300, null, null, null,
                0, "https://youtu.be/h67VX51QXiQ", null, null, null, null, null, Type.YouTube);
        widgetDao.createWidgetForPage(456, widget6);

    }

    private static void createRolesForWebsite() {
        //owner(0), admin(1), writer(2), editor(3), reviewer(4);
        roleDao = new RoleImpl();
        roleDao.assignWebsiteRole(12, 123, 0);
        roleDao.assignWebsiteRole(23, 123, 3);
        roleDao.assignWebsiteRole(34, 123, 1);

        roleDao.assignWebsiteRole(23, 234, 0);
        roleDao.assignWebsiteRole(34, 234, 3);
        roleDao.assignWebsiteRole(12, 234, 1);

        roleDao.assignWebsiteRole(34, 345, 0);
        roleDao.assignWebsiteRole(12, 345, 3);
        roleDao.assignWebsiteRole(23, 345, 1);

        roleDao.assignWebsiteRole(12, 456, 0);
        roleDao.assignWebsiteRole(23, 456, 3);
        roleDao.assignWebsiteRole(34, 456, 1);

        roleDao.assignWebsiteRole(23, 567, 0);
        roleDao.assignWebsiteRole(34, 567, 3);
        roleDao.assignWebsiteRole(12, 567, 1);

        roleDao.assignWebsiteRole(34, 678, 0);
        roleDao.assignWebsiteRole(12, 678, 3);
        roleDao.assignWebsiteRole(23, 678, 1);

    }

    private static void createRolesForPages() {

        roleDao = new RoleImpl();
        roleDao.assignPageRole(12,123,3);
        roleDao.assignPageRole(23,123,4);
        roleDao.assignPageRole(34,123,2);

        roleDao.assignPageRole(23,234,3);
        roleDao.assignPageRole(34,234,4);
        roleDao.assignPageRole(12,234,2);

        roleDao.assignPageRole(34,345,3);
        roleDao.assignPageRole(12,345,4);
        roleDao.assignPageRole(23,345,2);

        roleDao.assignPageRole(12,456,3);
        roleDao.assignPageRole(23,456,4);
        roleDao.assignPageRole(34,456,2);

        roleDao.assignPageRole(23,567,3);
        roleDao.assignPageRole(34,567,4);
        roleDao.assignPageRole(12,567,2);
    }

    private static void updatePhone() {
        developerDao = new DeveloperImpl();
        Developer developer = developerDao.findDeveloperByUsername("charlie");
        Collection<Phone> phones = developer.getPhone();
        Collection<Phone> outputPhone = new ArrayList<>();
        if(phones!=null){
            Iterator phIter = phones.iterator();
            while(phIter.hasNext()){
                Phone phone = (Phone)phIter.next();
                if(phone.getPrimary()){
                    phone.setPhone("333-444-5555");
                }
                outputPhone.add(phone);
            }
        }
        developer.setPhone(outputPhone);
        developerDao.updateDeveloper(developer.getDeveloperId(),developer);
    }

    private static void updatePage() {
        websiteDao = new WebsiteImpl();
        Collection<Website> websites = websiteDao.findAllWebsites();
        Iterator webIter = websites.iterator();
        int websiteId = -1;
        while(webIter.hasNext()){
            Website website = (Website) webIter.next();
            if(website.getName().equals("CNET")){
                websiteId = website.getId();
                break;
            }
        }
        if(websiteId!=-1){
            pageDao = new PageImpl();
            Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
            Iterator pageIter = pages.iterator();
            while (pageIter.hasNext()){
                Page page = (Page)pageIter.next();
                String pageTitle = page.getTitle();
                pageTitle = "CNET - ".concat(pageTitle);
                page.setTitle(pageTitle);
                pageDao.updatePage(page.getId(), page);
            }
        }

    }

    private static void updateWidgets(){
        widgetDao = new WidgetImpl();
        Collection<Widget> widgets = widgetDao.findAllWidgets();
        Iterator widgetIter = widgets.iterator();
        int pageId = -1;
        while (widgetIter.hasNext()){
            Widget widget = (Widget)widgetIter.next();
            if(widget.getName().equals("head345")){
                pageId = widget.getPage().getId();
                break;
            }

        }
        if (pageId>-1){
            widgets = widgetDao.findWidgetsForPage(pageId);
            widgetIter = widgets.iterator();
            int order = -1;
            while (widgetIter.hasNext()){
                Widget widget = (Widget)widgetIter.next();

                if(widget.getName().equals("head345")){
                    //order = widget.getOrder();
                    widget.setOrder(3);
                    widgetDao.updateWidget(widget.getId(), widget);
                }else if(widget.getName().equals("intro456")){
                    //order = widget.getOrder();
                    widget.setOrder(1);
                    widgetDao.updateWidget(widget.getId(), widget);
                }else if(widget.getName().equals("image345")){
                  //  order = widget.getOrder();
                    widget.setOrder(2);
                    widgetDao.updateWidget(widget.getId(), widget);
                }
            }

        }
    }

    private static void updateRoles() {

        developerDao = new DeveloperImpl();
        Developer dev1 = developerDao.findDeveloperByUsername("charlie");
        Developer dev2 = developerDao.findDeveloperByUsername("bob");
        websiteDao = new WebsiteImpl();
        Collection<Website> websites = websiteDao.findAllWebsites();
        Iterator webIter = websites.iterator();
        int websiteId = -1;
        while(webIter.hasNext()){
            Website website = (Website) webIter.next();
            if(website.getName().equals("CNET")){
                websiteId = website.getId();
                break;
            }
        }
        pageDao = new PageImpl();
        Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
        Iterator pageIter = pages.iterator();
        int pageId = -1;
        while (pageIter.hasNext()){
            Page page = (Page)pageIter.next();
            if(page.getTitle().equals("Home")){
                pageId = page.getId();
            }
        }

        roleDao = new RoleImpl();
        String role1 = roleDao.findPageRoles(dev1.getDeveloperId(), pageId);
        String role2 = roleDao.findPageRoles(dev2.getDeveloperId(), pageId);

        roleDao.updatePageRole(dev2.getDeveloperId(),pageId,role1);
        roleDao.updatePageRole(dev1.getDeveloperId(), pageId, role2);


    }


    private static void deleteWidget() {
        widgetDao = new WidgetImpl();
        pageDao = new PageImpl();
        Collection<Page> pages = pageDao.findAllPages();
        Iterator pageIter = pages.iterator();
        int pageId = -1;
        while(pageIter.hasNext()){
            Page page = (Page) pageIter.next();
            if(page.getTitle().equals("Contact")){
                pageId = page.getId();
                break;
            }
        }

        Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
        Iterator widgetIter = widgets.iterator();
        int order = -1;
        int widgetId = -1;
        while (widgetIter.hasNext()){
            Widget widget = (Widget)widgetIter.next();
            if(widget.getOrder() > order){
                order = widget.getOrder();
                widgetId = widget.getId();
            }
        }
//order fetched. now checking for the id where order is max
        /*widgetIter = widgets.iterator();
        int widgetId = -1;
        while (widgetIter.hasNext()){
            Widget widget = (Widget)widgetIter.next();
            if(widget.getOrder() == order){
                widgetId = widget.getId();
            }
        }*/

        if(widgetId>-1)
            widgetDao.deleteWidget(widgetId);



    }


    private static void deleteWebsite() {
        websiteDao = new WebsiteImpl();
        Collection<Website> websites = websiteDao.findAllWebsites();
        Iterator webIter = websites.iterator();
        int websiteId = -1;
        while (webIter.hasNext()){
            Website website = (Website)webIter.next();
            if(website.getName().equals("CNET")){
                websiteId = website.getId();
            }
        }

        websiteDao.deleteWebsite(websiteId);
    }


    private static void deletePage() {

        pageDao = new PageImpl();
        websiteDao = new WebsiteImpl();
        Collection<Website> websites = websiteDao.findAllWebsites();
        Iterator webIter = websites.iterator();
        int websiteId = -1;
        while (webIter.hasNext()){
            Website website = (Website)webIter.next();
            if(website.getName().equals("Wikipedia")){
                websiteId = website.getId();
            }
        }

        Collection<Page> pages = pageDao.findPagesForWebsite(websiteId);
        Iterator pageIter = pages.iterator();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970,0,1,0,0,0);
        java.util.Date date = calendar.getTime();
        Date sqlDate = new Date(date.getTime());
        int pageId = -1;
        while (pageIter.hasNext()){
            Page page = (Page)pageIter.next();
            if(page.getUpdated().after(sqlDate))
                sqlDate = page.getUpdated();
                pageId = page.getId();
        }

        if(pageId>-1){
            pageDao.deletePage(pageId);
        }


    }

    private static void deleteAddress() {
        developerDao = new DeveloperImpl();
        addressDao = new AddressImpl();
        Developer developer = developerDao.findDeveloperByUsername("alice");
        Collection<Address> addresses = developer.getAddress();
        int addressId = -1;
        if(addresses!=null){
            Iterator addIter = addresses.iterator();
            while(addIter.hasNext()){
                Address address = (Address)addIter.next();
                if(address.getPrimary()){
                    addressId = address.getId();
                }
            }
        }
        if(addressId>-1){
            addressDao.deleteAddress(addressId);
        }

    }

    private static void getUnansweredQuestions() {
        storedProceduresDao = new StoredProceduresImpl();
        storedProceduresDao.getUnansweredQuestions();
    }

    private static void endorsedUsersForWeek() {
        storedProceduresDao = new StoredProceduresImpl();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020,9,6,0,0,0);
        java.util.Date startDate = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020,9,24,0,0,0);
        java.util.Date endDate = calendar2.getTime();
        storedProceduresDao.endorsedUsersForWeek(new Date(startDate.getTime()), new Date(endDate.getTime()));
    }




}

package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.util.Iterator;

public class DeveloperImpl implements DeveloperDao {
    Connection conn;
    PreparedStatement pStatement;

    private static String CREATE_PERSON = "INSERT INTO `cs5200_spring2020_chauhan`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)" +
                                          "VALUES (?,?,?,?,?,?,?)";
    private static String CREATE_DEVELOPER = "INSERT INTO `cs5200_spring2020_chauhan`.`developer`(`id`,`developer_key`,`person_id`)" +
                                             "VALUES (?,?,?)";
    private static String CREATE_PHONES = "INSERT INTO `cs5200_spring2020_chauhan`.`phone`(`pid`,`phone`,`primary`)\n" +
                                         "VALUES (?, ?, ?)";
    private static String CREATE_ADDRESSES = "INSERT INTO `cs5200_spring2020_chauhan`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)\n" +
            "VALUES (?,?, ?, ?, ?, ?, ?);";
    private static String FIND_ALL_DEVELOPERS = "SELECT * FROM `cs5200_spring2020_chauhan`.`developer`, `cs5200_spring2020_chauhan`.person  " +
                                                "WHERE developer.person_id = person.id" ;
    private static String FIND_DEVELOPER_BY_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`developer`, `cs5200_spring2020_chauhan`.`person` " +
                                                 "WHERE developer.person_id = person.id AND developer.id = ?";
    private static String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM `cs5200_spring2020_chauhan`.`developer`, `cs5200_spring2020_chauhan`.`person` " +
                                                        "WHERE developer.person_id = person.id AND person.username = ?";
    private static String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM `cs5200_spring2020_chauhan`.`developer`, `cs5200_spring2020_chauhan`.`person` " +
            "WHERE developer.person_id = person.id AND person.username = ? AND person.password = ?";

    private static String FIND_PHONES = "SELECT * FROM `cs5200_spring2020_chauhan`.`phone` WHERE phone.pid = ?";
    private static String FIND_ADDRESSES = "SELECT * FROM `cs5200_spring2020_chauhan`.`address` WHERE address.pid = ?";


    private static String DELETE_PERSON_AND_DEVELOPER = "DELETE FROM `cs5200_spring2020_chauhan`.`person` WHERE person.id = ( " +
            "SELECT developer.person_id FROM `cs5200_spring2020_chauhan`.`developer` WHERE developer.id = ?)";


    private static String UPDATE_DEVELOPER = "UPDATE `cs5200_spring2020_chauhan`.`developer`" +
                                "SET developer.developer_key = ? WHERE developer.id = ?";

    private static String UPDATE_PERSON = "UPDATE `cs5200_spring2020_chauhan`.`person`" +
            "SET person.firstname = ?, person.lastname = ?, person.username = ?, person.password = ?, " +
            "person.email = ?, person.dob = ? WHERE person.id = ( " +
            "SELECT developer.person_id FROM `cs5200_spring2020_chauhan`.`developer` WHERE developer.id = ?)";

    private static String UPDATE_PHONE = "UPDATE `cs5200_spring2020_chauhan`.`phone`" +
            "SET phone.primary = ?, phone.phone = ?, phone.pid = ? WHERE phone.id = ?";

    private static String UPDATE_ADDRESS = "UPDATE `cs5200_spring2020_chauhan`.`address`" +
            "SET address.street1 = ?, address.street2 = ?, address.city = ?, address.state = ?, " +
            "address.zip = ?, address.primary = ?, address.pid = ? WHERE address.id = ?";


    @Override
    public void createDeveloper(Developer developer) {
        try {
            conn = DBConnection.getConnection();

            //insert into person
            pStatement = conn.prepareStatement(CREATE_PERSON,Statement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1,developer.getId());
            pStatement.setString(2,developer.getFirstName());
            pStatement.setString(3,developer.getLastName());
            pStatement.setString(4,developer.getUsername());
            pStatement.setString(5,developer.getPassword());
            pStatement.setString(6,developer.getEmail());
            pStatement.setDate(7,developer.getDob());
            pStatement.executeUpdate();
            ResultSet pk = pStatement.getGeneratedKeys();

            if(pk != null && pk.next()){
                //insert into developer
                int personId = pk.getInt(1);
                pStatement = conn.prepareStatement(CREATE_DEVELOPER);
                pStatement.setInt(1,developer.getDeveloperId());
                pStatement.setString(2,developer.getDeveloperKey());
                pStatement.setInt(3,personId);
                pStatement.executeUpdate();

                //insert into phone

                Collection<Phone> phones = developer.getPhone();
                if(phones!=null){
                    Iterator phoneIterator = phones.iterator();
                    while (phoneIterator.hasNext()){
                        Phone phone = (Phone) phoneIterator.next();
                        pStatement = conn.prepareStatement(CREATE_PHONES);
                        pStatement.setInt(1, personId);
                        pStatement.setString(2, phone.getPhone());
                        if(phone.getPrimary()!=null)
                            pStatement.setBoolean(3, phone.getPrimary());
                        else
                            pStatement.setNull(3, Types.BOOLEAN);
                        pStatement.executeUpdate();
                    }
                }


                Collection<Address> addresses = developer.getAddress();
                if(addresses!=null){
                    Iterator addressIterator = addresses.iterator();
                    while (addressIterator.hasNext()){
                        Address address = (Address) addressIterator.next();
                        pStatement = conn.prepareStatement(CREATE_ADDRESSES);
                        pStatement.setInt(1, personId);
                        pStatement.setString(2, address.getStreet1());
                        pStatement.setString(3, address.getStreet2());
                        pStatement.setString(4, address.getCity());
                        pStatement.setString(5, address.getState());
                        pStatement.setString(6, address.getZip());
                        if(address.getPrimary()!=null)
                            pStatement.setBoolean(7, address.getPrimary());
                        else
                            pStatement.setNull(7, Types.BOOLEAN);
                        pStatement.executeUpdate();
                    }
                }

            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Collection<Developer> findAllDevelopers() {
        Collection<Developer> devList = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_ALL_DEVELOPERS);
            ResultSet results = pStatement.executeQuery();
            devList = new ArrayList<>();
            while (results.next()) {
                String developerKey = results.getString("developer_key");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String username = results.getString("username");
                String password = results.getString("password");
                int developerId = results.getInt("id");
                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    int phoneId = resPhone.getInt("id");
                    Phone phone = new Phone(phoneNum, primary);
                    phone.setId(phoneId);
                    phones.add(phone);
                }

                pStatement = conn.prepareStatement(FIND_ADDRESSES);
                pStatement.setInt(1,id);
                ResultSet resAdd = pStatement.executeQuery();
                Collection<Address> addresses = new ArrayList<>();
                while (resAdd.next()){
                    String street1 = resAdd.getString("street1");
                    String street2 = resAdd.getString("street2");
                    String city = resAdd.getString("city");
                    String state = resAdd.getString("state");
                    String zip = resAdd.getString("zip");
                    Boolean primary = resAdd.getBoolean("primary");
                    int addressId = resAdd.getInt("id");
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    address.setId(addressId);
                    addresses.add(address);
                }
                Developer developer = new Developer(developerKey, id, lastName, firstName, username,
                        password, email, dob, addresses, phones);
                developer.setDeveloperId(developerId);
                devList.add(developer);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return devList;
    }

    @Override
    public Developer findDeveloperById(int developerId) {
        Developer developer = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_DEVELOPER_BY_ID);
            pStatement.setInt(1, developerId);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                String developerKey = results.getString("developer_key");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String username = results.getString("username");
                String password = results.getString("password");
                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    int phoneId = resPhone.getInt("id");
                    Phone phone = new Phone(phoneNum, primary);
                    phone.setId(phoneId);
                    phones.add(phone);
                }

                pStatement = conn.prepareStatement(FIND_ADDRESSES);
                pStatement.setInt(1,id);
                ResultSet resAdd = pStatement.executeQuery();
                Collection<Address> addresses = new ArrayList<>();
                while (resAdd.next()){
                    String street1 = resAdd.getString("street1");
                    String street2 = resAdd.getString("street2");
                    String city = resAdd.getString("city");
                    String state = resAdd.getString("state");
                    String zip = resAdd.getString("zip");
                    Boolean primary = resAdd.getBoolean("primary");
                    int addId = resAdd.getInt("id");
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    address.setId(addId);
                    addresses.add(address);
                }
                developer = new Developer(developerKey, id, lastName, firstName, username, password,
                        email, dob, addresses, phones);
                developer.setDeveloperId(developerId);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return developer;
    }

    @Override
    public Developer findDeveloperByUsername(String username) {
        Developer developer = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
            pStatement.setString(1, username);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                String developerKey = results.getString("developer_key");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("firstname");
                String lastName = results.getString("lastname");
                String password = results.getString("password");
                int developerId = results.getInt("id");

                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    int phId = resPhone.getInt("id");
                    Phone phone = new Phone(phoneNum, primary);
                    phone.setId(phId);
                    phones.add(phone);
                }

                pStatement = conn.prepareStatement(FIND_ADDRESSES);
                pStatement.setInt(1,id);
                ResultSet resAdd = pStatement.executeQuery();
                Collection<Address> addresses = new ArrayList<>();
                while (resAdd.next()){
                    String street1 = resAdd.getString("street1");
                    String street2 = resAdd.getString("street2");
                    String city = resAdd.getString("city");
                    String state = resAdd.getString("state");
                    String zip = resAdd.getString("zip");
                    Boolean primary = resAdd.getBoolean("primary");
                    int addId = resAdd.getInt("id");
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    address.setId(addId);
                    addresses.add(address);
                }
                developer = new Developer(developerKey, id, lastName, firstName, username, password,
                        email, dob, addresses, phones);
                developer.setDeveloperId(developerId);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return developer;
    }

    @Override
    public Developer findDeveloperByCredentials(String username, String password) {
        Developer developer = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
            pStatement.setString(1, username);
            pStatement.setString(1, password);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                String developerKey = results.getString("developer_key");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                int developerId = results.getInt("id");
                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    int phId = resPhone.getInt("id");
                    Phone phone = new Phone(phoneNum, primary);
                    phone.setId(phId);
                    phones.add(phone);
                }

                pStatement = conn.prepareStatement(FIND_ADDRESSES);
                pStatement.setInt(1,id);
                ResultSet resAdd = pStatement.executeQuery();
                Collection<Address> addresses = new ArrayList<>();
                while (resAdd.next()){
                    String street1 = resAdd.getString("street1");
                    String street2 = resAdd.getString("street2");
                    String city = resAdd.getString("city");
                    String state = resAdd.getString("state");
                    String zip = resAdd.getString("zip");
                    Boolean primary = resAdd.getBoolean("primary");
                    int addId = resAdd.getInt("id");
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    address.setId(addId);
                    addresses.add(address);
                }
                developer = new Developer(developerKey, id, lastName, firstName, username, password,
                        email, dob, addresses, phones);
                developer.setDeveloperId(developerId);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return developer;
    }

    @Override
    public int updateDeveloper(int developerId, Developer developer) {
        int rows = 0;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_DEVELOPER);
            pStatement.setString(1, developer.getDeveloperKey());
            pStatement.setInt(2,developerId);
            rows =  pStatement.executeUpdate();

            pStatement = conn.prepareStatement(UPDATE_PERSON);
            pStatement.setString(1, developer.getFirstName());
            pStatement.setString(2, developer.getLastName());
            pStatement.setString(3, developer.getUsername());
            pStatement.setString(4, developer.getPassword());
            pStatement.setString(5, developer.getEmail());
            pStatement.setDate(6, developer.getDob());
            pStatement.setInt(7, developerId);
            rows =  pStatement.executeUpdate();

            if(developer.getPhone()!=null){
                Collection<Phone> phones = developer.getPhone();
                Iterator phoneIterator = phones.iterator();
                while (phoneIterator.hasNext()){
                    Phone phone = (Phone) phoneIterator.next();
                    pStatement = conn.prepareStatement(UPDATE_PHONE);
                    if(null!=phone.getPrimary())
                        pStatement.setBoolean(1, phone.getPrimary());
                    else
                        pStatement.setNull(1, Types.BOOLEAN);
                    pStatement.setString(2, phone.getPhone());
                    pStatement.setInt(3, developer.getId());
                    pStatement.setInt(4, phone.getId());
                    pStatement.executeUpdate();
                }

            }

            if(developer.getAddress()!=null){
                Collection<Address> addresses = developer.getAddress();
                Iterator addressIterator = addresses.iterator();
                while (addressIterator.hasNext()){
                    Address address = (Address) addressIterator.next();
                    pStatement = conn.prepareStatement(UPDATE_ADDRESS);
                    pStatement.setString(1,address.getStreet1());
                    pStatement.setString(2, address.getStreet2());
                    pStatement.setString(3, address.getCity());
                    pStatement.setString(4, address.getState());
                    pStatement.setString(5, address.getZip());
                    if(null!=address.getPrimary())
                        pStatement.setBoolean(6, address.getPrimary());
                    else
                        pStatement.setNull(6, Types.BOOLEAN);
                    pStatement.setInt(7, developer.getId());
                    pStatement.setInt(8, address.getId());
                    pStatement.executeUpdate();
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rows;


    }

    @Override
    public int deleteDeveloper(int developerId) {


        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_PERSON_AND_DEVELOPER);
            pStatement.setInt(1, developerId);
            rows  = pStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    DBConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rows;

    }
}

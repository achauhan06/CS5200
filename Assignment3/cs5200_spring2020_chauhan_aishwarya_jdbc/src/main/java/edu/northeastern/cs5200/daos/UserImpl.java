package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;

import java.util.Collection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.util.Iterator;

public class UserImpl implements UserDao {

    Connection conn;
    PreparedStatement pStatement;

    private static String CREATE_PERSON = "INSERT INTO `cs5200_spring2020_chauhan`.`person`(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)" +
            "VALUES (?,?,?,?,?,?,?)";

    private static String CREATE_USER = "INSERT INTO `cs5200_spring2020_chauhan`.`user`(`id`,`user_agreement`,`person_id`)" +
            "VALUES (?,?,?)";

    private static String CREATE_PHONES = "INSERT INTO `cs5200_spring2020_chauhan`.`phone`(`pid`,`phone`,`primary`)\n" +
            "VALUES (?, ?, ?)";
    private static String CREATE_ADDRESSES = "INSERT INTO `cs5200_spring2020_chauhan`.`address`(`pid`,`street1`,`street2`,`city`,`state`,`zip`,`primary`)\n" +
            "VALUES (?,?, ?, ?, ?, ?, ?);";

    private static String FIND_ALL_USERS= "SELECT * FROM `cs5200_spring2020_chauhan`.`user`, `cs5200_spring2020_chauhan`.person  " +
            "WHERE user.person_id = person.id" ;

    private static String FIND_PHONES = "SELECT * FROM `cs5200_spring2020_chauhan`.`phone` WHERE phone.pid = ?";
    private static String FIND_ADDRESSES = "SELECT * FROM `cs5200_spring2020_chauhan`.`address` WHERE address.pid = ?";

    private static String FIND_USER_BY_USERNAME = "SELECT * FROM `cs5200_spring2020_chauhan`.`user`, `cs5200_spring2020_chauhan`.`person` " +
            "WHERE user.person_id = person.id AND person.username = ?";
    private static String FIND_USER_BY_CREDENTIALS = "SELECT * FROM `cs5200_spring2020_chauhan`.`user`, `cs5200_spring2020_chauhan`.`person` " +
            "WHERE user.person_id = person.id AND person.username = ? AND person.password = ?";

    private static String FIND_USER_BY_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`user`, `cs5200_spring2020_chauhan`.`person` " +
            "WHERE user.person_id = person.id AND user.id = ?";

    private static String DELETE_PERSON_AND_USER = "DELETE FROM `cs5200_spring2020_chauhan`.`person` WHERE person.id = ( " +
            "SELECT user.person_id FROM `cs5200_spring2020_chauhan`.`user` WHERE user.id = ?)";


    private static String UPDATE_USER = "UPDATE `cs5200_spring2020_chauhan`.`user`" +
            "SET user.user_agreement = ? WHERE user.id = ?";

    private static String UPDATE_PERSON = "UPDATE `cs5200_spring2020_chauhan`.`person`" +
            "SET person.firstname = ?, person.lastname = ?, person.username = ?, person.password = ?, " +
            "person.email = ?, person.dob = ? WHERE person.id = ( " +
            "SELECT user.person_id FROM `cs5200_spring2020_chauhan`.`user` WHERE user.id = ?)";

    private static String UPDATE_PHONE = "UPDATE `cs5200_spring2020_chauhan`.`phone`" +
            "SET phone.primary = ?, phone.phone = ? WHERE phone.pid =  ( " +
            "SELECT user.person_id FROM `cs5200_spring2020_chauhan`.`user` WHERE user.id = ?)";

    private static String UPDATE_ADDRESS = "UPDATE `cs5200_spring2020_chauhan`.`address`" +
            "SET address.street1 = ?, address.street2 = ?, address.city = ?, address.state = ?, " +
            "address.zip = ?, address.primary = ? WHERE address.pid = ( " +
            "SELECT user.person_id FROM `cs5200_spring2020_chauhan`.`user` WHERE user.id = ?)";

    @Override
    public void createUser(User user) {
        try {
            conn = DBConnection.getConnection();

            //insert into person
            pStatement = conn.prepareStatement(CREATE_PERSON,Statement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1,user.getId());
            pStatement.setString(2,user.getFirstName());
            pStatement.setString(3,user.getLastName());
            pStatement.setString(4,user.getUsername());
            pStatement.setString(5,user.getPassword());
            pStatement.setString(6,user.getEmail());
            pStatement.setDate(7,user.getDob());
            pStatement.executeUpdate();
            ResultSet pk = pStatement.getGeneratedKeys();

            if(pk != null && pk.next()){
                //insert into user
                int personId = pk.getInt(1);
                pStatement = conn.prepareStatement(CREATE_USER);
                pStatement.setInt(1,user.getUserId());
                if(user.getUserAgreement()!=null)
                    pStatement.setBoolean(2,user.getUserAgreement());
                else
                    pStatement.setNull(2, Types.BOOLEAN);
                pStatement.setInt(3,personId);
                pStatement.executeUpdate();

                //insert into phone

                Collection<Phone> phones = user.getPhone();
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


                Collection<Address> addresses = user.getAddress();
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
    public Collection<User> findAllUsers() {

        Collection<User> userList = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_ALL_USERS);
            ResultSet results = pStatement.executeQuery();
            userList = new ArrayList<>();
            while (results.next()) {
                Boolean userAgreement = results.getBoolean("user_agreement");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String username = results.getString("username");
                String password = results.getString("password");
                int userId = results.getInt("id");
                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    Phone phone = new Phone(phoneNum, primary);
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
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    addresses.add(address);
                }
                User user = new User(id, lastName, firstName);
                user.setUserAgreement(userAgreement);
                user.setUserId(userId);
                user.setId(id);
                user.setAddress(addresses);
                user.setEmail(email);
                user.setDob(dob);
                user.setPassword(password);
                user.setPhone(phones);
                user.setUsername(username);
                userList.add(user);
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
        return userList;
    }

    @Override
    public User findUserById(int userId) {

        User user = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_USER_BY_ID);
            pStatement.setInt(1, userId);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                Boolean userAgreement = results.getBoolean("user_agreement");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("firstname");
                String lastName = results.getString("lastname");
                String password = results.getString("password");
                String username = results.getString("username");

                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    Phone phone = new Phone(phoneNum, primary);
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
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    addresses.add(address);
                }
                user = new User(id, lastName, firstName);
                user.setUserAgreement(userAgreement);
                user.setUserId(userId);
                user.setId(id);
                user.setAddress(addresses);
                user.setEmail(email);
                user.setDob(dob);
                user.setPassword(password);
                user.setPhone(phones);
                user.setUsername(username);

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
        return user;
    }

    @Override
    public User findUserByUsername(String username) {

        User user = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_USER_BY_USERNAME);
            pStatement.setString(1, username);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                Boolean userAgreement = results.getBoolean("user_agreement");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("firstname");
                String lastName = results.getString("lastname");
                String password = results.getString("password");
                int userId = results.getInt("id");

                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    Phone phone = new Phone(phoneNum, primary);
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
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    addresses.add(address);
                }
                user = new User(id, lastName, firstName);
                user.setUserAgreement(userAgreement);
                user.setUserId(userId);
                user.setId(id);
                user.setAddress(addresses);
                user.setEmail(email);
                user.setDob(dob);
                user.setPassword(password);
                user.setPhone(phones);
                user.setUsername(username);

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
        return user;
    }

    @Override
    public User findUserByCredentials(String username, String password) {
        User user = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_USER_BY_CREDENTIALS);
            pStatement.setString(1, username);
            pStatement.setString(1, password);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                Boolean userAgreement = results.getBoolean("user_agreement");
                int id = results.getInt("person_id");
                Date dob = results.getDate("dob");
                String email = results.getString("email");
                String firstName = results.getString("firstname");
                String lastName = results.getString("lastname");
                int userId = results.getInt("id");

                pStatement = conn.prepareStatement(FIND_PHONES);
                pStatement.setInt(1,id);
                ResultSet resPhone = pStatement.executeQuery();
                Collection<Phone> phones = new ArrayList<>();
                while (resPhone.next()){
                    String phoneNum = resPhone.getString("phone");
                    Boolean primary = resPhone.getBoolean("primary");
                    Phone phone = new Phone(phoneNum, primary);
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
                    Address address = new Address(street1, street2, city, state, zip, primary);
                    addresses.add(address);
                }
                user = new User(id, lastName, firstName);
                user.setUserAgreement(userAgreement);
                user.setUserId(userId);
                user.setId(id);
                user.setAddress(addresses);
                user.setEmail(email);
                user.setDob(dob);
                user.setPassword(password);
                user.setPhone(phones);
                user.setUsername(username);

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
        return user;
    }

    @Override
    public int updateUser(int userId, User user) {

        int rows = 0;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_USER);
            if(user.getUserAgreement()!=null)
                pStatement.setBoolean(1, user.getUserAgreement());
            else
                pStatement.setNull(1, Types.BOOLEAN);
            pStatement.setInt(2,userId);
            rows =  pStatement.executeUpdate();

            pStatement = conn.prepareStatement(UPDATE_PERSON);
            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setString(3, user.getUsername());
            pStatement.setString(4, user.getPassword());
            pStatement.setString(5, user.getEmail());
            pStatement.setDate(6, user.getDob());
            pStatement.setInt(7, userId);
            rows =  pStatement.executeUpdate();

            if(user.getPhone()!=null){
                Collection<Phone> phones = user.getPhone();
                Iterator phoneIterator = phones.iterator();
                while (phoneIterator.hasNext()){
                    Phone phone = (Phone) phoneIterator.next();
                    pStatement = conn.prepareStatement(UPDATE_PHONE);
                    if(null!=phone.getPrimary())
                        pStatement.setBoolean(1, phone.getPrimary());
                    else
                        pStatement.setNull(1, Types.BOOLEAN);
                    pStatement.setString(2, phone.getPhone());
                    pStatement.setInt(3, userId);
                    pStatement.executeUpdate();
                }

            }

            if(user.getAddress()!=null){
                Collection<Address> addresses = user.getAddress();
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
                    pStatement.setInt(7, userId);
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
    public int deleteUser(int userId) {



        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_PERSON_AND_USER);
            pStatement.setInt(1, userId);
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

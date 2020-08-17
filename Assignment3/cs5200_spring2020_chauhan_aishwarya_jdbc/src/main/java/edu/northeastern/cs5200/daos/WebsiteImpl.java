package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;

import edu.northeastern.cs5200.models.Website;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WebsiteImpl implements WebsiteDao{

    Connection conn;
    PreparedStatement pStatement;
    private static String CREATE_WEBSITE = "INSERT INTO `cs5200_spring2020_chauhan`.`website`(`id`,`name`,`description`,`created`,`updated`,`visits`,`dev_id`)\n" +
            "VALUES(?, ?,?,?,?,?,?) ";
    private static String FIND_ALL_WEBSITES = "SELECT * FROM `cs5200_spring2020_chauhan`.`website`";
    private static String FIND_WEBSITE_BY_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`website`" +
                                                "where website.id = ?";

    private static String DELETE_WEBSITE = "DELETE FROM `cs5200_spring2020_chauhan`.`website` where website.id = ?";

    private static String FIND_WEBSITE_FOR_DEVELOPER = "SELECT * FROM `cs5200_spring2020_chauhan`.`website` " +
                                                    "WHERE website.developer_id = ?";

    private static String UPDATE_WEBSITE = "UDPATE `cs5200_spring2020_chauhan`.`website` SET website.name = ?, website.description = ?, " +
            "website.created = ?, website.updated = ?, website.visits = ? WHERE website.id = ?";

    @Override
    public void createWebsiteForDeveloper(int developerId, Website website) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(CREATE_WEBSITE);
            pStatement.setInt(1, website.getId());
            pStatement.setString(2,website.getName());
            pStatement.setString(3,website.getDescription());
            pStatement.setDate(4,website.getCreated());
            pStatement.setDate(5,website.getUpdated());
            pStatement.setInt(6,website.getVisits());
            pStatement.setInt(7,developerId);
            pStatement.executeUpdate();

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
    public Collection<Website> findAllWebsites() {
        Collection<Website> websiteList = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_ALL_WEBSITES);
            ResultSet results = pStatement.executeQuery();
            websiteList = new ArrayList<>();
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int visits = results.getInt("visits");
                Website website = new Website(id, name, description, created, updated, visits);
                websiteList.add(website);
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
        return websiteList;

    }

    @Override
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        Collection<Website> websiteList = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_WEBSITE_FOR_DEVELOPER);
            pStatement.setInt(1, developerId);
            ResultSet results = pStatement.executeQuery();
            websiteList = new ArrayList<>();
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int visits = results.getInt("visits");
                Website website = new Website(id, name, description, created, updated, visits);
                websiteList.add(website);
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
        return websiteList;


    }

    @Override
    public Website findWebsiteById(int websiteId) {
        Website website = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_WEBSITE_BY_ID);
            pStatement.setInt(1, websiteId);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                String name = results.getString("name");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int visits = results.getInt("visits");
                website = new Website(websiteId, name, description, created, updated, visits);

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
        return website;
    }

    @Override
    public int updateWebsite(int websiteId, Website website) {

        int rows = 0;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_WEBSITE);
            pStatement.setString(1, website.getName());
            pStatement.setString(2, website.getDescription());
            pStatement.setDate(3, website.getCreated());
            pStatement.setDate(4, website.getUpdated());
            pStatement.setInt(5, website.getVisits());
            pStatement.setInt(6, websiteId);
            rows =  pStatement.executeUpdate();

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
    public int deleteWebsite(int websiteId) {
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_WEBSITE);
            pStatement.setInt(1, websiteId);
            rows = pStatement.executeUpdate();
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

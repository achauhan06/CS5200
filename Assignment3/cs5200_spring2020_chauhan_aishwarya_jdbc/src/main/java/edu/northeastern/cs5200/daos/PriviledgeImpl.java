package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PriviledgeImpl implements PriviledgeDao{

    Connection conn;
    PreparedStatement pStatement;
    private String CREATE_WEBSITE_PRIVILEDGE = "INSERT INTO `cs5200_spring2020_chauhan`.`website_priviledge`(`role`,`dev_id`,`website_id`) \n" +
            "VALUES(?, ?, ?)";

    private String CREATE_PAGE_PRIVILEDGE = "INSERT INTO `cs5200_spring2020_chauhan`.`page_priviledge`(`role`,`dev_id`,`page_id`) \n" +
            "VALUES(?, ?, ?)";

    private String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM `cs5200_spring2020_chauhan`.`page_priviledge` " +
            "WHERE page_priviledge.developer_id = ? and page_priviledge.page_id = ?";

    private String DELETE_PAGE_PRIVILEDGE = "DELETE FROM `cs5200_spring2020_chauhan`.`website_priviledge` " +
            "WHERE website_priviledge.developer_id = ? and website_priviledge.website_id = ?";
    @Override
    public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(CREATE_WEBSITE_PRIVILEDGE);
            pStatement.setString(1, priviledge);
            pStatement.setInt(2,developerId);
            pStatement.setInt(3,websiteId);
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
    public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(CREATE_PAGE_PRIVILEDGE);
            pStatement.setString(1, priviledge);
            pStatement.setInt(2,developerId);
            pStatement.setInt(3,pageId);
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
    public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
            pStatement.setInt(1, developerId);
            pStatement.setInt(2, websiteId);
            pStatement.executeUpdate();
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
    }

    @Override
    public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_PAGE_PRIVILEDGE);
            pStatement.setInt(1, developerId);
            pStatement.setInt(2, pageId);
            pStatement.executeUpdate();
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
    }

}

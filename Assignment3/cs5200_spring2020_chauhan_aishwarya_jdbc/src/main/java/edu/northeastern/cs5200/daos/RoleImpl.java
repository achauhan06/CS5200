package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Role;

import java.sql.*;


public class RoleImpl implements RoleDao {

    Connection conn;
    PreparedStatement pStatement;
    private String CREATE_WEBSITE_ROLE = "INSERT INTO `cs5200_spring2020_chauhan`.`website_role`(`role`,`dev_id`,`website_id`) \n" +
            "VALUES(?, ?, ?)";

    private String CREATE_PAGE_ROLE = "INSERT INTO `cs5200_spring2020_chauhan`.`page_role`(`role`,`dev_id`,`page_id`) \n" +
            "VALUES(?, ?, ?)";
    private String FIND_PAGE_ROLE = "SELECT * FROM `cs5200_spring2020_chauhan`.`page_role`" +
                                        "WHERE page_role.dev_id = ? AND page_role.page_id = ?";;
    private String FIND_WEBSITE_ROLE = "SELECT * FROM `cs5200_spring2020_chauhan`.`website_role` " +
                                    "WHERE website_role.dev_id = ? AND website_role.website_id = ?";

    private String DELETE_WEBSITE_ROLE = "DELETE FROM `cs5200_spring2020_chauhan`.`page_role` " +
            "WHERE page_role.developer_id = ? and page_role.page_id = ? and website.role = ?" ;

    private String DELETE_PAGE_ROLE = "DELETE FROM `cs5200_spring2020_chauhan`.`website_role` " +
            "WHERE website_role.developer_id = ? and website_role.website_id = ? and website.role = ? ";

    private String UPDATE_PAGE_ROLE = "UPDATE `cs5200_spring2020_chauhan`.`page_role` SET page_role.role = ?" +
                                        " WHERE page_role.dev_id = ? AND page_role.page_id = ?";

    private String UPDATE_WEBSITE_ROLE = "UPDATE `cs5200_spring2020_chauhan`.`website_role` SET " +
                        " website_role.role = ? WHERE website_role.dev_id = ? AND website_role.website_id = ?";

    @Override
    public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(CREATE_WEBSITE_ROLE);
            pStatement.setString(1, Role.getRole(roleId));
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
    public void assignPageRole(int developerId, int pageId, int roleId) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(CREATE_PAGE_ROLE);
            pStatement.setString(1, Role.getRole(roleId));
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
    public String findWebsiteRoles(int developerId, int websiteId){

        String role = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_WEBSITE_ROLE);
            pStatement.setInt(1, developerId);
            pStatement.setInt(2, websiteId);
            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                role = results.getString("role");
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
        return role;

    }

    @Override
    public String findPageRoles(int developerId, int pageId){
        String role = null;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_PAGE_ROLE);
            pStatement.setInt(1, developerId);
            pStatement.setInt(2, pageId);
            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                role = results.getString("role");
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
        return role;
    }

    @Override
    public int updateWebsiteRole(int developerId, int websiteId, String role) {
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_WEBSITE_ROLE);
            pStatement.setString(1, role);
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);


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
    public int updatePageRole(int developerId, int pageId, String role) {
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_PAGE_ROLE);
            pStatement.setString(1, role);
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);


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
    public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_WEBSITE_ROLE);
            pStatement.setInt(1, developerId);
            pStatement.setInt(2, websiteId);
            pStatement.setString(3, Role.getRole(roleId));
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
    public void deletePageRole(int developerId, int pageId, int roleId) {
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_PAGE_ROLE);
            pStatement.setInt(1, developerId);
            pStatement.setInt(2, pageId);
            pStatement.setString(3, Role.getRole(roleId));
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

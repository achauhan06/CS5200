package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PageImpl implements PageDao {

    Connection conn;
    PreparedStatement pStatement;

    private static String CREATE_PAGE = "INSERT INTO `cs5200_spring2020_chauhan`.`page`(`id`,`title`,`description`,`created`,`updated`,`views`,`website_id`)" +
                                        "VALUES(?,?,?,?,?,?,?)";

    private static String FIND_ALL_PAGES = "SELECT * FROM `cs5200_spring2020_chauhan`.`page`";

    private static String FIND_PAGE_BY_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`page`" +
            "where page.id = ?";
    private static String FIND_PAGES_BY_WEBSITE_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`page`" +
            "where page.website_id = ?";

    private static String UPDATE_PAGE = "UPDATE `cs5200_spring2020_chauhan`.`page` SET page.title = ?, page.description = ?, " +
            "page.created = ?, page.updated = ?, page.views = ? WHERE page.id = ?";

    private static String DELETE_PAGE = "DELETE FROM `cs5200_spring2020_chauhan`.`page` where page.id = ?";

    @Override
    public void createPageForWebsite(int websiteId, Page page) {

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(CREATE_PAGE);
            pStatement.setInt(1, page.getId());
            pStatement.setString(2,page.getTitle());
            pStatement.setString(3,page.getDescription());
            pStatement.setDate(4,page.getCreated());
            pStatement.setDate(5,page.getUpdated());
            pStatement.setInt(6,page.getViews());
            pStatement.setInt(7,websiteId);
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
    public Collection<Page> findAllPages() {
        Collection<Page> pageList = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_ALL_PAGES);
            ResultSet results = pStatement.executeQuery();
            pageList = new ArrayList<>();
            while (results.next()) {
                int id = results.getInt("id");
                String title = results.getString("title");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int views = results.getInt("views");
                Page page = new Page(id, title , description, created, updated, views);
                pageList.add(page);
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
        return pageList;

    }

    @Override
    public Page findPageById(int pageId) {
        Page page = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_PAGE_BY_ID);
            pStatement.setInt(1, pageId);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                String title = results.getString("title");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int views = results.getInt("views");
                page = new Page(pageId, title, description, created, updated, views);

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
        return page;
    }

    @Override
    public Collection<Page> findPagesForWebsite(int websiteId) {

        Collection<Page> pageList = null;

        try {
            pageList = new ArrayList<>();
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_PAGES_BY_WEBSITE_ID);
            pStatement.setInt(1, websiteId);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {
                int id = results.getInt("id");
                String title = results.getString("title");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int views = results.getInt("views");

                Page page = new Page(id, title, description, created, updated, views);
                pageList.add(page);

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
        return pageList;

    }

    @Override
    public int updatePage(int pageId, Page page) {

        int rows = 0;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_PAGE);
            pStatement.setString(1, page.getTitle());
            pStatement.setString(2, page.getDescription());
            pStatement.setDate(3, page.getCreated());
            pStatement.setDate(4, page.getUpdated());
            pStatement.setInt(5, page.getViews());
            pStatement.setInt(6, pageId);
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
    public int deletePage(int pageId) {
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_PAGE);
            pStatement.setInt(1, pageId);
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

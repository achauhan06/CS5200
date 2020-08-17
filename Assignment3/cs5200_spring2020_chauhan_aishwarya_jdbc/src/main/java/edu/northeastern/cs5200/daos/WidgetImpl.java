package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WidgetImpl implements WidgetDao {


    Connection conn;
    PreparedStatement pStatement;

    private static String CREATE_WIDGET = "INSERT INTO `cs5200_spring2020_chauhan`.`widget`(`id`,`name`,`dtype`,`order`," +
            "`width`,`height`, `url`, `page_id`, `shareable`, `expandable`, `size`, `html`, `src`,`cssClass`, `cssStyle`,`text`)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static String FIND_ALL_WIDGETS = "SELECT * FROM `cs5200_spring2020_chauhan`.`widget`";
    private static String FIND_WIDGET_BY_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`widget` where widget.id = ?";
    private static String FIND_WIDGET_BY_PAGE_ID = "SELECT * FROM `cs5200_spring2020_chauhan`.`widget` where widget.page_id = ?";

    private static String UPDATE_WIDGET = "UPDATE `cs5200_spring2020_chauhan`.`widget` SET widget.name = ?, widget.width = ?, " +
            "widget.height = ?, widget.cssClass = ?, widget.cssStyle = ?,  widget.text = ?,  widget.order = ?," +
            " widget.url = ?,  widget.shareable = ?,  widget.expandable = ?,  widget.size = ?," +
            " widget.html = ?,  widget.src = ?,  widget.dtype = ? WHERE widget.id = ?";

    private static String DELETE_WIDGET = "DELETE FROM `cs5200_spring2020_chauhan`.`widget` where widget.id = ?";

    @Override
    public void createWidgetForPage(int pageId, Widget widget) {
        try {
            conn = DBConnection.getConnection();
            //insert into widget
            pStatement = conn.prepareStatement(CREATE_WIDGET);
            if(null!=widget.getId())
                pStatement.setInt(1, widget.getId());
            else
                pStatement.setNull(1,Types.INTEGER);
            pStatement.setString(2,widget.getName());
            pStatement.setString(3,widget.getType().name());
            pStatement.setInt(4,widget.getOrder());
            if(null!=widget.getWidth())
                pStatement.setInt(5,widget.getWidth());
            else
                pStatement.setNull(5,Types.INTEGER);
            if(null!=widget.getHeight())
                pStatement.setInt(6,widget.getHeight());
            else
                pStatement.setNull(6,Types.INTEGER);
            pStatement.setString(7,widget.getUrl());
            pStatement.setInt(8,pageId);
            if(null!=widget.getShareable())
                pStatement.setBoolean(9,widget.getShareable());
            else
                pStatement.setNull(9,Types.BOOLEAN);
            if(null!=widget.getExpandable())
                pStatement.setBoolean(10,widget.getExpandable());
            else
                pStatement.setNull(10,Types.BOOLEAN);
            if(null!=widget.getSize())
                pStatement.setInt(11,widget.getSize());
            else
                pStatement.setNull(11,Types.INTEGER);
            pStatement.setString(12,widget.getHtml());
            pStatement.setString(13,widget.getSrc());
            pStatement.setString(14,widget.getCssClass());
            pStatement.setString(15,widget.getCssStyle());
            pStatement.setString(16, widget.getText());
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
    public Collection<Widget> findAllWidgets() {
        Collection<Widget> widgetList = null;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_ALL_WIDGETS);
            ResultSet results = pStatement.executeQuery();
            widgetList = new ArrayList<>();
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                String url = results.getString("url");
                Boolean shareable = results.getBoolean("shareable");
                Boolean expandable = results.getBoolean("expandable");
                int size = results.getInt("size");
                String html = results.getString("html");
                String src = results.getString("src");
                String type = results.getString("dtype");
                int pageId = results.getInt("page_id");
                Page page = new Page();
                page.setId(pageId);

                Widget widget = new Widget(id, name , width, height, cssClass, cssStyle, text, order, url, shareable, expandable, size, html, src,
                        Type.valueOf(type));
                widget.setPage(page);
                widgetList.add(widget);
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
        return widgetList;


    }

    @Override
    public Widget findWidgetById(int widgetId) {

        Widget widget = null;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_WIDGET_BY_ID);
            pStatement.setInt(1, widgetId);
            ResultSet results = pStatement.executeQuery();

            int id = results.getInt("id");
            String name = results.getString("name");
            int width = results.getInt("width");
            int height = results.getInt("height");
            String cssClass = results.getString("cssClass");
            String cssStyle = results.getString("cssStyle");
            String text = results.getString("text");
            int order = results.getInt("order");
            String url = results.getString("url");
            Boolean shareable = results.getBoolean("shareable");
            Boolean expandable = results.getBoolean("expandable");
            int size = results.getInt("size");
            String html = results.getString("html");
            String src = results.getString("src");
            String type = results.getString("dtype");
            int pageId = results.getInt("page_id");
            Page page = new Page();
            page.setId(pageId);

            widget = new Widget(id, name , width, height, cssClass, cssStyle, text, order, url, shareable, expandable, size, html, src,
                    Type.valueOf(type));
            widget.setPage(page);
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
        return widget;
    }

    @Override
    public Collection<Widget> findWidgetsForPage(int pageId) {

        Collection<Widget> widgetList = null;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(FIND_WIDGET_BY_PAGE_ID);
            pStatement.setInt(1, pageId);
            ResultSet results = pStatement.executeQuery();
            widgetList = new ArrayList<>();
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                String url = results.getString("url");
                Boolean shareable = results.getBoolean("shareable");
                Boolean expandable = results.getBoolean("expandable");
                int size = results.getInt("size");
                String html = results.getString("html");
                String src = results.getString("src");
                String type = results.getString("dtype");

                Widget widget = new Widget(id, name , width, height, cssClass, cssStyle, text, order, url, shareable, expandable, size, html, src,
                        Type.valueOf(type));
                widgetList.add(widget);
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
        return widgetList;
    }


    @Override
    public int updateWidget(int widgetId, Widget widget) {

        int rows = 0;

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(UPDATE_WIDGET);
            pStatement.setString(1, widget.getName());
            pStatement.setInt(2, widget.getWidth());
            pStatement.setInt(3, widget.getHeight());
            pStatement.setString(4, widget.getCssClass());
            pStatement.setString(5, widget.getCssStyle());
            pStatement.setString(6, widget.getText());
            pStatement.setInt(7, widget.getOrder());
            pStatement.setString(8, widget.getUrl());
            pStatement.setBoolean(9, widget.getShareable());
            pStatement.setBoolean(10, widget.getExpandable());
            pStatement.setInt(11, widget.getSize());
            pStatement.setString(12, widget.getHtml());
            pStatement.setString(13, widget.getSrc());
            pStatement.setString(14, widget.getType().name());
            pStatement.setInt(15, widgetId);
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
    public int deleteWidget(int widgetId) {
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_WIDGET);
            pStatement.setInt(1, widgetId);
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

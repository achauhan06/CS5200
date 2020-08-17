package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;

import java.sql.*;

public class StoredProceduresImpl implements StoredProceduresDao {

    Connection conn;
    PreparedStatement pStatement;

    @Override
    public void getUnansweredQuestions() {

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareCall("{call getUnansweredQuestions()}");
            ResultSet resultset = pStatement.executeQuery();
            while (resultset.next()){
                String moduleName = resultset.getString("ModuleName");
                String questions = resultset.getString("Question");
                int numberOfUnansweredQuestions = resultset.getInt("UnAnsweredQuestion");
                System.out.println("Module = " + moduleName + ", Question = "
                        + questions + ", Unanswered Questions = " + numberOfUnansweredQuestions);
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
    public void endorsedUsersForWeek(Date startDate, Date endDate) {

        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareCall("{call endorsedUsersForWeek(?, ?)}");
            pStatement.setDate(1,startDate);
            pStatement.setDate(2,endDate);
            ResultSet resultset = pStatement.executeQuery();
            while (resultset.next()){
                String name = resultset.getString("Name");
                System.out.println("Name = " + name);
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
}

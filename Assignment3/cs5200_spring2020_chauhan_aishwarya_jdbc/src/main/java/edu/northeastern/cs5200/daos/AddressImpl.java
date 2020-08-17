package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressImpl implements AddressDao {

    Connection conn;
    PreparedStatement pStatement;

    private static String DELETE_ADDRESS = "DELETE FROM `cs5200_spring2020_chauhan`.`address` " +
                                    "where address.id = ? AND address.primary = true";
    @Override
    public int deleteAddress(int addressId) {
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            pStatement = conn.prepareStatement(DELETE_ADDRESS);
            pStatement.setInt(1, addressId);
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

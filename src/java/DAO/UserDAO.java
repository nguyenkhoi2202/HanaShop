/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DBContext;
import DTO.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Nguyen Khoi
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "select Username, Password,Fullname,Role,Gmail from account where Username = ? and Password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    UserDTO dto = new UserDTO(rs.getString("Username"), rs.getString("Password"),
                            rs.getString("Fullname"), rs.getString("Role"), rs.getString("Gmail"));
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public static void checkExist(String email) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "select Username from account where Username = ? ";
                ps = conn.prepareStatement(sql);
                String[] username = email.split("@");
                ps.setString(1, username[0]);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ps.close();
                    conn.close();
                } else {
                    String insert = "INSERT INTO [dbo].[account]\n"
                            + "           ([Username]\n"
                            + "           ,[Role]\n"
                            + "           ,[Gmail])\n"
                            + "     VALUES\n"
                            + "           (?,?,?)";
                    ps = conn.prepareStatement(insert);
                    ps.setString(1, username[0]);
                    ps.setString(2, "user");
                    ps.setString(3, email);
                    ps.executeUpdate();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
}

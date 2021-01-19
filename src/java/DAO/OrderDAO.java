/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DBContext;
import DTO.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Nguyen Khoi
 */
public class OrderDAO implements Serializable {

    public int createItemOrder(String username, String date, float totalprice) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[tblorder]\n"
                        + "           ([Username]\n"
                        + "           ,[Orderdate]\n"
                        + "           ,[Totalprice])\n"
                        + "     VALUES\n"
                        + "           (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, date);
                ps.setFloat(3, totalprice);
                int result = ps.executeUpdate();
                return result;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public List<OrderDTO> getAllOrder(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "SELECT [orderid]\n"
                        + "      ,[Username]\n"
                        + "      ,[Orderdate]\n"
                        + "      ,[Totalprice]\n"
                        + "  FROM [dbo].[tblorder]\n"
                        + "  where Username = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                List<OrderDTO> list = new ArrayList<>();
                while (rs.next()) {
                    OrderDTO dto = new OrderDTO(rs.getString("orderid"), rs.getString("Username"), rs.getFloat("Totalprice"), rs.getString("Orderdate"));
                    list.add(dto);
                }
                return list;
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

    public static int getOrderid(String username) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "  SELECT TOP 1 * FROM [tblorder] where Username = ? ORDER BY [orderid] DESC ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int orderid = rs.getInt("orderid");
                    return orderid;
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
        return 0;
    }

}

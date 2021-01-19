/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DBContext;
import DTO.OrderDetailDTO;
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
public class OrderDetailDAO implements Serializable {

    public static int createItemOrderDetail(int orderid, String id, String foodname, float price, int quantity) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[tblorderdetail]\n"
                        + "           ([orderid]\n"
                        + "           ,[id]\n"
                        + "           ,[name]\n"
                        + "           ,[price]\n"
                        + "           ,[quantity])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, orderid);
                ps.setString(2, id);
                ps.setString(3, foodname);
                ps.setFloat(4, price);
                ps.setInt(5, quantity);
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

    public List<OrderDetailDTO> getAllOrderDetail(String orderid) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "SELECT [Detailid]\n"
                        + "      ,[orderid]\n"
                        + "      ,[id]\n"
                        + "      ,[name]\n"
                        + "      ,[price]\n"
                        + "      ,[quantity]\n"
                        + "  FROM [dbo].[tblorderdetail]\n"
                        + "  where orderid =?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, orderid);
                rs = ps.executeQuery();
                List<OrderDetailDTO> list = new ArrayList<>();
                while (rs.next()) {
                    OrderDetailDTO dto = new OrderDetailDTO(rs.getInt("orderid"), rs.getString("name"),
                            rs.getString("id"), rs.getFloat("price"), rs.getInt("quantity"));
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
}

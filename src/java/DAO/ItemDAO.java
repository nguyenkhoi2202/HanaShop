/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DBContext;
import DTO.CategoryDTO;
import DTO.ItemDTO;
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
public class ItemDAO implements Serializable {

    public List<ItemDTO> searchItemByName(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select id, name,image,description,price,createDate,category,quantity,status from shopping where name like ? and status = 1";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                rs = ps.executeQuery();
                List<ItemDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ItemDTO item = new ItemDTO(rs.getString("id"), rs.getString("name"), rs.getString("image"),
                            rs.getString("description"), rs.getFloat("price"),
                            rs.getString("createDate"), rs.getString("category"),
                            rs.getInt("quantity"), rs.getInt("status"));
                    list.add(item);
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
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public int createItem(ItemDTO dto) throws SQLException, NamingException {
        int result = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[shopping]\n"
                        + "           ([id]\n"
                        + "           ,[name]\n"
                        + "           ,[image]\n"
                        + "           ,[description]\n"
                        + "           ,[price]\n"
                        + "           ,[createDate]\n"
                        + "           ,[category]\n"
                        + "           ,[quantity]\n"
                        + "           ,[status])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, dto.getId());
                ps.setString(2, dto.getName());
                ps.setString(3, dto.getImage());
                ps.setString(4, dto.getDescription());
                ps.setFloat(5, dto.getPrice());
                ps.setString(6, dto.getCreateDate());
                ps.setString(7, dto.getCategory());
                ps.setInt(8, dto.getQuantity());
                ps.setInt(9, dto.getStatus());
                result = ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public int updateItem(String id, String name, String image, String description, float price, String category, int quantity, int status) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "update shopping set name =?, image=?,description=?,"
                        + "price=?,category=?,quantity=?,status=? where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, image);
                ps.setString(3, description);
                ps.setFloat(4, price);
                ps.setString(5, category);
                ps.setInt(6, quantity);
                ps.setInt(7, status);
                ps.setString(8, id);
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

    public int deleteItem(String name) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[shopping]\n"
                        + "   SET [status] =0\n"
                        + "      WHERE name = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                return ps.executeUpdate();
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

    public static int getQuantity(String name) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "select quantity from shopping where name = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int result = rs.getInt("quantity");
                    return result;
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

    public static int updateQuantity(String name, int quantity) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "update shopping set quantity=? where name = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, name);
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

    public List<ItemDTO> getAllPagging(int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String query = "SELECT id,name,image,description,price,createDate,category,quantity,status FROM shopping where status = 1 ORDER BY id "
                        + "OFFSET (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";

                ps = conn.prepareStatement(query);
                ps.setInt(1, pageIndex);
                ps.setInt(2, pageSize);
                ps.setInt(3, pageSize);
                ps.setInt(4, pageSize);
                rs = ps.executeQuery();

                List<ItemDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ItemDTO i = new ItemDTO(rs.getString(1),
                            rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getFloat(5),
                            rs.getString(6), rs.getString(7),
                            rs.getInt(8), rs.getInt(9));

                    list.add(i);
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

    public List<ItemDTO> getAllPaggingAdmin(int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String query = "SELECT id,name,image,description,price,createDate,category,quantity,status FROM shopping ORDER BY id "
                        + "OFFSET (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";

                ps = conn.prepareStatement(query);
                ps.setInt(1, pageIndex);
                ps.setInt(2, pageSize);
                ps.setInt(3, pageSize);
                ps.setInt(4, pageSize);
                rs = ps.executeQuery();

                List<ItemDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ItemDTO i = new ItemDTO(rs.getString(1),
                            rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getFloat(5),
                            rs.getString(6), rs.getString(7),
                            rs.getInt(8), rs.getInt(9));

                    list.add(i);
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

    public int countPage(int pageSize) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String query = "select Count(*) from shopping";

            conn = new DBContext().makeConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            int numOfPage = count / pageSize;
            if (count % pageSize != 0) {
                numOfPage++;
            }
            return numOfPage;
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

    public List<ItemDTO> getAllItem() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "SELECT id,name,image,description,price,createDate,category,quantity,status FROM shopping";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                List<ItemDTO> list = new ArrayList<>();
                while (rs.next()) {
                    ItemDTO i = new ItemDTO(rs.getString(1),
                            rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getFloat(5),
                            rs.getString(6), rs.getString(7),
                            rs.getInt(8), rs.getInt(9));
                    list.add(i);
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

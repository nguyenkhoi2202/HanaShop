/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DBContext;
import DTO.CategoryDTO;
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
public class CategoryDAO {

    public List<CategoryDTO> getAllCategory() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "select  category, categoryName from tblcategory";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                List<CategoryDTO> list = new ArrayList<>();
                while (rs.next()) {
                    CategoryDTO dto = new CategoryDTO(rs.getString(1), rs.getString(2));
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
   

    public int CreateCategory(String category, String categoryName) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[tblcategory]\n"
                        + "           ([category]\n"
                        + "           ,[categoryName])\n"
                        + "     VALUES\n"
                        + "           (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, category);
                ps.setString(2, categoryName);
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
}

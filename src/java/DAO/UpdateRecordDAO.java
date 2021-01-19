/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DBContext;
import DTO.UpdateRecordDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Nguyen Khoi
 */
public class UpdateRecordDAO {

    public static int createUpdate(UpdateRecordDTO dto) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[tblUpdaterecord]\n"
                        + "           ([username]\n"
                        + "           ,[updateday]\n"
                        + "           ,[kind])\n"
                        + "     VALUES\n"
                        + "           (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, dto.getUsername());
                ps.setString(2, dto.getUpdateday());
                ps.setString(3, dto.getKind());
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
}

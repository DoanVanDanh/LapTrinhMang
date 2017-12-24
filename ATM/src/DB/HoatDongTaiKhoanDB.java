/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Presario CQ45
 */
public class HoatDongTaiKhoanDB {
     private static Connection connection = null;

    public HoatDongTaiKhoanDB() {
    }
     
    public static boolean napTien(String code, String soTien) throws SQLException{
		boolean numTableAffected = false ;
		try{
			connection= ConnectionDB.getConnection();
			
			String sql ="INSERT INTO `taikhoanchinh`(`code`, `soTien`) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, soTien);
		
			numTableAffected = ps.executeUpdate()>0;
			
                        connection.close();
                        ps.close();
		}catch (Exception e) {
			System.out.println("HoatDongTaiKhoanDB.java   :"+e.getMessage());
		}finally {
			connection.close();
		}
		
		return numTableAffected;
	}
    
}

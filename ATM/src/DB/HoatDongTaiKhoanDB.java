/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import BUSSINESS.TaiKhoanChinh;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Position;

/**
 *
 * @author Presario CQ45
 */
public class HoatDongTaiKhoanDB {
     private static Connection connection = null;

    public HoatDongTaiKhoanDB() {
    }
    
    //cập nhật số tiền có trong tài khoản: nộp/gửi : + / -
    public static boolean updateSoTien(String code, String soTien) throws SQLException{
		boolean numTableAffected = false ;
		try{
			connection= ConnectionDB.getConnection();
			
			String sql ="update  `taikhoanchinh` set soTien=? where code=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(2, code);
			ps.setString(1, soTien);
		
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
    
    // lấy giá trị trong tài khoản: coi có bao nhiêu để thực hiện các hành động cập nhật
    public static String getSoTien(String code){
        TaiKhoanChinh taiKhoanChinh = TaiKhoanChinhDB.getTaiKhoanChinh(code);
        String soTien = taiKhoanChinh.getSoTien();
        
        return soTien;
    }
    
    //nạp tiền vào tài khoản: số tiền cập nhật = lấy số tiền hiện có + số tiền đã nộp
    public static boolean napTien(String code,String soTienDaNap){
        BigInteger soTienHienCo = new  BigInteger(getSoTien(code));
        BigInteger soTienDaNapVo = new BigInteger(soTienDaNap);
        
        BigInteger soTienCapNhat = soTienHienCo.add(soTienDaNapVo);

         try {
             updateSoTien(code, soTienCapNhat.toString());
             
             return true;
         } catch (SQLException ex) {
             Logger.getLogger("lỗi nạp tiền vô tài khoản  "+ ex.getMessage());
         }
        
        return false;
    }
    
    //rút tiền: số tiền cập nhật = lấy số tiền hiện có - số tiền đã nhập (nếu thỏa điều kiện số tiền mún rút<= số tiền đang có) 
    public static boolean rutTien(String code,String soTienDaNhap){
        BigInteger soTienHienCo = new  BigInteger(getSoTien(code));
        BigInteger soTienMuonRut = new BigInteger(soTienDaNhap);
        
        BigInteger soTienCapNhat = soTienHienCo.add(soTienMuonRut.negate());

         try {
             updateSoTien(code, soTienCapNhat.toString());
             
             return true;
         } catch (SQLException ex) {
             Logger.getLogger("lỗi rút tiền từ tài khoản  "+ ex.getMessage());
         }
        
        return false;
    }
    
    //chuyển tiền--> trừ tài khoản đang chuyển và cộng tương ứng vào tài khoản nhận
    public static boolean chuyenTien(String codeNguoiGui,String codeNguoiNhan, String soTienDaNhap){
        BigInteger soTienMuonGui = new BigInteger(soTienDaNhap);
        
        //người gửi bị trừ đi
        BigInteger soTienHienCoNguoiGui = new  BigInteger(getSoTien(codeNguoiGui));
        BigInteger soTienNguoiGuiCapNhat = soTienHienCoNguoiGui.add(soTienMuonGui.negate());
        
        //người nhận nhận số tiền tương ứng
        BigInteger soTienHienCoNguoiNhan = new  BigInteger(getSoTien(codeNguoiNhan));
        BigInteger soTienNguoiNhanCapNhat = soTienHienCoNguoiNhan.add(soTienMuonGui);

         try {
             updateSoTien(codeNguoiGui, soTienNguoiGuiCapNhat.toString());
             updateSoTien(codeNguoiNhan, soTienNguoiNhanCapNhat.toString());
             
             return true;
         } catch (SQLException ex) {
             Logger.getLogger("lỗi rút tiền từ tài khoản  "+ ex.getMessage());
         }
        
        return false;
    }
    
}

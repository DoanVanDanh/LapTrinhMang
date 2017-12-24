/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import BUSSINESS.TaiKhoanChinh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Presario CQ45
 */
public class TaiKhoanChinhDB {

    private static Connection connection = null;

    public TaiKhoanChinhDB() {
    }

    //kiểm tra đăng nhập
    public boolean isTaiKhoan(String code, String matKhau) {
        connection = ConnectionDB.getConnection();
        String sql = "select code,matkhau from TaiKhoanChinh";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codeDB = rs.getString(1);
                String matKhauDB = rs.getString(2);

                if (code.equalsIgnoreCase(codeDB) && matKhau.equalsIgnoreCase(matKhauDB)) {
                    System.out.println("hi, admin~~");

                    rs.close();
                    connection.close();
                    return true;
                }
            }
            rs.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
        return false;
    }

    //lấy thông tin tài khoản đó
    public static TaiKhoanChinh getTaiKhoanChinh(String code) {
        TaiKhoanChinh taiKhoanChinh = new TaiKhoanChinh();
        connection = ConnectionDB.getConnection();
        String sql = "SELECT `code`,`ten`,`matkhau`,`email`,`soTien` \n"
                + "FROM taikhoanchinh \n"
                + "\n"
                + "where code=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              
                String tenTaiKhoan = rs.getString(2);
                String matKhau = rs.getString(3);
                String email= rs.getString(4);
                String soTien = rs.getString(5);
                taiKhoanChinh = new TaiKhoanChinh(code, tenTaiKhoan, matKhau, email, soTien);
            }
            rs.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("BanDB.java" + e.getMessage());
        } finally {
        }
        return taiKhoanChinh;
    }

}

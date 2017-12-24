/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

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

    }

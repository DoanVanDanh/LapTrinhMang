/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSSINESS;

import java.math.BigInteger;

/**
 *
 * @author Presario CQ45
 */
public class TaiKhoanChinh {
    private String code;
    private String tenTaiKhoan;
    private String matKhau;
    private String email;
    private BigInteger soTien;

    public TaiKhoanChinh() {
    }

    public TaiKhoanChinh(String code, String tenTaiKhoan, String matKhau, String email, BigInteger soTien) {
        this.code = code;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.soTien = soTien;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getSoTien() {
        return soTien;
    }

    public void setSoTien(BigInteger soTien) {
        this.soTien = soTien;
    }
    
    
    
    
}

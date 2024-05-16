/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.DanhMuc;
import Model.SanPham;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SanPhamRepository {

    DBConnext dbConnext;

       public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        String sql = "select * from SANPHAM";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("Id"));
                sp.setMa(rs.getString("Ma"));
                sp.setTen(rs.getString("Ten"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                sanPhams.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhams;
    }
}

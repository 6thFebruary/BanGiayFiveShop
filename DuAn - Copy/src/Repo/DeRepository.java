/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.De;
import Model.SanPham;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DeRepository {
    
    DBConnext dbConnext;

       public ArrayList<De> getAllde() {
        ArrayList<De> deGiay = new ArrayList<>();
        String sql = "select * from SANPHAM";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                De de = new De();
                de.setId(rs.getInt("Id"));
                de.setMa(rs.getString("Ma"));
                de.setTen(rs.getString("Ten"));
                de.setTrangThai(rs.getInt("TrangThai"));
                deGiay.add(de);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deGiay;
    }
}

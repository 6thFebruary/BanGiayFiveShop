/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.MauSac;
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
public class MauSacRepository {
     DBConnext dbConnext;
       public ArrayList<MauSac> getAllMauSacs() {
        ArrayList<MauSac> mauSacs = new ArrayList<>();
        String sql = "select * from SANPHAM";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("Id"));
                ms.setMa(rs.getString("Ma"));
                ms.setMauSac(rs.getString("MauSac"));
                ms.setTrangThai(rs.getInt("TrangThai"));
                mauSacs.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSacs;
    }
}

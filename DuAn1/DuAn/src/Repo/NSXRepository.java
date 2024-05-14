/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.NSX;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class NSXRepository {
      DBConnection dbConnext;

       public ArrayList<NSX> getAllNsxs() {
        ArrayList<NSX> ngaySanXuat = new ArrayList<>();
        String sql = "select * from SANPHAM";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NSX nsx = new NSX();
                nsx.setId(rs.getInt("Id"));
                nsx.setMa(rs.getString("Ma"));
                nsx.setTen(rs.getString("Ten"));
                nsx.setTrangThai(rs.getInt("TrangThai"));
                ngaySanXuat.add(nsx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ngaySanXuat;
    }
}

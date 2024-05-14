/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.SIZE;
import Utils.DBConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SizeRepository {
    DBConnection dBConnext;
    public ArrayList<SIZE> getAllSize() {
        ArrayList<SIZE> size = new ArrayList<>();
        String sql = "select*from Size";
        try ( Connection con = dBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SIZE sz = new SIZE();
                sz.setId(rs.getInt("id"));
                sz.setMa(rs.getString("Ma"));
                sz.setKichCo(rs.getInt("KichCo"));
                sz.setTrangThai(rs.getInt("TrangThai"));
                size.add(sz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}

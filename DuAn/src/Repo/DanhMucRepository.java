/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Interface.DanhMucImpl;
import Model.DanhMuc;
import Utilities.DBConnext;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DanhMucRepository {

    DBConnext dBConnext;

    public ArrayList<DanhMuc> getAllDanhMuc() {
        ArrayList<DanhMuc> danhmucs = new ArrayList<>();
        String sql = "select* from DANHMUC";
        try ( Connection con = dBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc dm = new DanhMuc();
                dm.setId(rs.getInt("Id"));
                dm.setMa(rs.getString("Ma"));
                dm.setTen(rs.getString("Ten"));
                dm.setTrangThai(rs.getInt("TrangThai"));
                danhmucs.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhmucs;
    }

}

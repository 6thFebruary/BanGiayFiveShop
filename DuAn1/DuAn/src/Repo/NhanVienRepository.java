/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;
import Model.NguoiDung;
import Utils.DBConnext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class NhanVienRepository {
    public ArrayList<NguoiDung> getList(){
    ArrayList<NguoiDung> listnd = new ArrayList<>();
    
    String sql = "Select Ma, Ten, NgaySinh, Email, Sdt, GioiTinh, DiaChi, MatKhau, TrangThai from NguoiDung";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              listnd.add(new NguoiDung(rs.getString(1),
                      rs.getString(2),
                      rs.getDate(3),
                      rs.getString(4),
                      rs.getString(5),
                      rs.getInt(6),
                      rs.getString(7),
                      rs.getString(8),
                      rs.getInt(9)));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return listnd;
    
   }
    
    public static void main(String[] args) {
        NhanVienRepository nv = new NhanVienRepository();
        System.out.println(nv.getList());
    }
}

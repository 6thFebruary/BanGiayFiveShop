/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.ChiTietGiay;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Windows
 */
public class HoaDonChoRepository {
        
        public long generateRandomId() {
        return 10000 + new Random().nextInt(90000);
    }
        
        public ArrayList<HoaDon> getAllHoaDonCho(){
            ArrayList<HoaDon> listHD = new ArrayList<>();
            
            try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT HOADON.Id, HOADON.Ma, HOADON.TongTien, HOADON.ThanhTien, HOADON.NgayTao, HOADON.TrangThai,\n"
                    + "    NGUOIDUNG.Id AS IDND, NGUOIDUNG.Ma AS MAND, NGUOIDUNG.Ten AS TENND,\n"
                    + "    KHACHHANG.Id AS IDKH, KHACHHANG.Ten AS TENKH, KHACHHANG.Sdt AS SDTKH,\n"
                    + "    KHUYENMAI.Id AS IDKM, KHUYENMAI.Ma AS MAKM, KHUYENMAI.PhanTramGiam AS PTGKM\n"
                    + "FROM HOADON\n"
                    + "LEFT JOIN NGUOIDUNG ON HOADON.IdND = NGUOIDUNG.Id\n"
                    + "LEFT JOIN KHACHHANG ON HOADON.IdKH = KHACHHANG.Id\n"
                    + "LEFT JOIN KHUYENMAI ON HOADON.IdKM = KHUYENMAI.Id\n"
                    + "WHERE HOADON.TrangThai = 0;";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("Id"));
                hd.setMa(rs.getString("Ma"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setIdKhachHang(rs.getString("TENKH"));
                hd.setIdKhuyenMai(rs.getString("MAKM"));

                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;

    }
     public Integer addHoaDonCho(){
         Integer row = null;
         String sql = "INSERT INTO HOADON (Ma, NgayTao, TrangThai) VALUES(?,?,?)";
         try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            Long maHD = generateRandomId();
            ptm.setString(1, "HD" + maHD);
            ptm.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ptm.setInt(3, 0);
            row = ptm.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return row;
     }
     
     public static void main(String[] args) {
        HoaDonChoRepository hd = new HoaDonChoRepository();
         System.out.println(hd.getAllHoaDonCho());
    }
        
   }
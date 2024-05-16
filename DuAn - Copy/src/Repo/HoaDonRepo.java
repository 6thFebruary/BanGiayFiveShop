/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.GioHang;
import Model.HoaDon;
import Model.KhachHang;
import Model.KhuyenMai;
import Model.NguoiDung;
import Utilities.DBConnext;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HoaDonRepo {

    public int randomMa() {
        return 10000 + new Random().nextInt(90000);
    }

    public ArrayList getGioHang(String idHD) {
        ArrayList<GioHang> listGH = new ArrayList<>();
        try {
            Connection conn = DBConnext.getConnection();
            String sql = "  SELECT ctg.Id as ID, sp.Ten as TEN, hdct.SoLuong as SL, cl.Ten as CL, ms.MauSac as MS, nsx.Ten as HANG, s.KichCo as SIZE, hdct.DonGia, sp.TrangThai\n"
                    + "  FROM HOADONCHITIET as hdct JOIN CHITIETGIAY as ctg on hdct.IdCTD = ctg.Id\n"
                    + "  JOIN SANPHAM as sp on ctg.IdSanPham = sp.Id JOIN HOADON as hd on hdct.IdHD = hd.Id JOIN CHATLIEU as cl on ctg.IdChatLieu = cl.Id\n"
                    + "  JOIN MAUSAC as ms on ctg.IdMauSac = ms.Id JOIN NSX as nsx on ctg.IdNSX = nsx.Id JOIN SIZE as s on ctg.IdSize = s.Id\n"
                    + "  WHERE hd.Id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, idHD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GioHang gh = new GioHang();
                gh.setIdCTSP(rs.getString("ID"));
                gh.setTenSanPham(rs.getString("TEN"));
                gh.setSoLuong(rs.getInt("SL"));
                gh.setChatLieu(rs.getString("CL"));
                gh.setMauSac(rs.getString("MS"));
                gh.setHang(rs.getString("HANG"));
                gh.setSize(rs.getString("SIZE"));
                gh.setDonGia(rs.getDouble("DONGIA"));
                gh.setTrangThai(rs.getInt("TRANGTHAI"));
                listGH.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGH;
    }

    public String getIdHoaDon(String maHD) {
        String idHD = null;
        try {
            Connection conn = DBConnext.getConnection();
            String sql = "SELECT Id FROM HOADON WHERE Ma = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, maHD);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                idHD = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHD;
    }
    
//    public String getIdChiTietGiay(String idGH){
//        try {
//            Connection conn = DBConnection.getConnection();
//            String sql = "SELECT Id FROM CHITIETGIAY";
//        } catch (Exception e) {
//        }
//    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.ChiTietGiay;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Utilities.DBConnext;
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
public class HoaDonChoRepo {

    public long generateRandomId() {
        return 10000 + new Random().nextInt(90000);
    }

    public ArrayList<HoaDon> getAllHoaDonCho() {
        ArrayList<HoaDon> listHD = new ArrayList<>();

        try {
            Connection con = DBConnext.getConnection();
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

    public Integer addHoaDonCho() {
        Integer row = null;
        String sql = "INSERT INTO HOADON (Ma, NgayTao, TrangThai) VALUES(?,?,?)";
        try {
            Connection con = DBConnext.getConnection();
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

    public Integer checkGioHang(String idHD, String idCTSP) {
        Integer row = 0;
        String sql = "SELECT * FROM HOADONCHITIET AS HDCT WHERE IdHD = ? AND HDCT.IdCTD =?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ps.setString(2, idCTSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                row = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateDSSP(Integer slsp, String Id) {
        Integer row = null;
        String sql = "UPDATE CHITIETGIAY SET SoLuong = ? WHERE Id =?";
        try {
            Connection n = DBConnext.getConnection();
            PreparedStatement ptm = n.prepareCall(sql);
            ptm.setInt(1, slsp);
            ptm.setString(2, Id);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateSLGH(Integer slsp, String Id) {
        Integer row = null;
        String sql = "UPDATE HOADONCHITIET SET SoLuong = ? WHERE Id = ?";
        try {
            Connection conn = DBConnext.getConnection();
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, slsp);
            stm.setString(2, Id);
            row = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer addHoaDonChiTiet(HoaDonChiTiet hdct) {
        Integer row = null;
        String sql = "INSERT INTO HOADONCHITIET(IdHD,IdCTD,SoLuong,DonGia,NgayTao,TrangThai) VALUES (?,?,?,?,?,?)";

        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);

            ptm.setString(1, hdct.getIdHD());
            ptm.setString(2, hdct.getIdCTG());
            ptm.setInt(3, hdct.getSoLuong());
            ptm.setDouble(4, hdct.getDonGia());
            ptm.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            ptm.setInt(6, 0);

            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    
    public String getIdCTSPbyIdHDCT(String idHDCT){
        String idCTD = null;
        
        String sql = "SELECT IdCTD FROM HOADONCHITIET WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHDCT);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idCTD = rs.getString("IdCTD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCTD;
    }
    
    public Integer getSoLuongByIdCTSP(String idCTSP) {
        Integer soLuong = null;

        String sql = "SELECT SoLuong FROM CHITIETGIAY WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }
    
    public Integer updteSoLuongChiTietSanPham(Integer slsp, String Id) {
        Integer row = null;
        String sql = "UPDATE CHITIETGIAY SET SoLuong = ? WHERE Id =?";
        try {
            Connection n = DBConnext.getConnection();
            PreparedStatement ptm = n.prepareCall(sql);
            ptm.setInt(1, slsp);
            ptm.setString(2, Id);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    
    public void xoaHoaDonCT(String id) {
        String sql = "DELETE FROM HOADONCHITIET WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public String getIdHoaDonByMa(String maHD) {
        String idHD = null;

        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT Id FROM HOADON WHERE Ma = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maHD);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                idHD = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHD;
    }
    
    public Double getGiaTienHDCTtoTongTienHDbyIdHD(String idHD) {
        Double row = null;
        String sql = "SELECT SUM(DonGia) FROM HOADONCHITIET WHERE IdHD = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                row = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    
    public void capNhatTongTienHoaDon(String idHD, Double tongTien) {
        String sql = "UPDATE HOADON SET TongTien = TongTien+? WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, tongTien);
            ps.setString(2, idHD);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<HoaDonChiTiet> getAllHoaDonTatCa() {
        ArrayList<HoaDonChiTiet> listHD = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT * FROM HOADONCHITIET ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setId(rs.getString("Id"));
                hd.setIdCTG(rs.getString("IdCTG"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTrangThai(rs.getInt("TrangThai"));

                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;

    }
    
    public void xoaHoaDonChiTiet(String id) {
        String sql = "DELETE  FROM HOADONCHITIET WHERE IdHD =? AND  TrangThai =0";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void xoaHoaDonCho(String ma) {
        String sql = "DELETE FROM HOADON WHERE Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, ma);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    public void capNhatSoLuongChiTietSanPham(String idCTSP, int slXoa) {

        String sql = "UPDATE CHITIETGIAY SET SoLuong = SoLuong+? WHERE Id= ?";
        try {
            Connection connection = DBConnext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, slXoa);
            ps.setString(2, idCTSP);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTietTheoIdHD(String idHD) {
        ArrayList<HoaDonChiTiet> listHD = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT * FROM HOADONCHITIET WHERE IdHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setId(rs.getString("Id"));
                hd.setIdCTG(rs.getString("IdCTG"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTrangThai(rs.getInt("TrangThai"));

                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }


    public static void main(String[] args) {
        HoaDonChoRepo hd = new HoaDonChoRepo();
        System.out.println(hd.getAllHoaDonCho());
    }

}

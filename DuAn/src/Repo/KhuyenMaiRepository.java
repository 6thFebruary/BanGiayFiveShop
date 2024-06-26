/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Interface.KhachHangServiceIplm;
import Interface.KhuyenMaiRepositoryImpl;
import Model.KhuyenMai;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class KhuyenMaiRepository implements KhuyenMaiRepositoryImpl{
    DBConnext dbConNext = new DBConnext();

    @Override
    public ArrayList<KhuyenMai> getAllKM() {
        ArrayList<KhuyenMai> dskm = new ArrayList<>();
        String sql = "Select * from KHUYENMAI";
        try {
            Connection con = dbConNext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMa(rs.getString("Ma"));
                km.setTen(rs.getString("Ten"));
                km.setPhanTramGiam(rs.getDouble("PhanTramGiam"));
                km.setGiaGiam(rs.getDouble("GiaGiam"));
                km.setSoLuong(rs.getInt("SoLuong"));
                km.setHinhThucGiam(rs.getInt("HinhThucGiam"));
                km.setNgayBatDau(rs.getDate("NgayBatDau"));
                km.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                km.setTrangThai(rs.getInt("TrangThai"));
                km.setMoTa(rs.getString("Mota"));
                dskm.add(km);
            }
        } catch (Exception e) {
            System.out.println("Lỗi GetAll KhuyenMai!");
        }
        return dskm;
    }
    @Override
    public boolean add(KhuyenMai km) {
        String sql = "INSERT INTO KHUYENMAI (Ma, Ten, PhanTramGiam, GiaGiam, SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa)\n"
                + "VALUES \n"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = dbConNext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMa());
            ps.setObject(2, km.getTen());
            ps.setObject(3, km.getPhanTramGiam());
            ps.setObject(4, km.getGiaGiam());
            ps.setObject(5, km.getSoLuong());
            ps.setObject(6, km.getNgayBatDau());
            ps.setObject(7, km.getNgayKetThuc());
            ps.setObject(8, km.getHinhThucGiam());
            ps.setObject(9, km.getTrangThai());
            ps.setObject(10, km.getMoTa());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi ADD KhuyenMaiiii");
        }
        return false;
    }

    @Override
    public boolean update(String ma, KhuyenMai km) {
        String sql = "Update KHUYENMAI Set Ten= ?, PhanTramGiam = ?, GiaGiam = ?,"
                + " SoLuong = ?, NgayBatDau = ?, NgayKetThuc = ?, HinhThucGiam = ?, TrangThai= ?, MoTa = ? where Ma= ?";
        try {
            Connection con = dbConNext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTen());
            ps.setObject(2, km.getPhanTramGiam());
            ps.setObject(3, km.getGiaGiam());
            ps.setObject(4, km.getSoLuong());
            ps.setObject(5, km.getNgayBatDau());
            ps.setObject(6, km.getNgayKetThuc());
            ps.setObject(7, km.getHinhThucGiam());
            ps.setObject(8, km.getTrangThai());
            ps.setObject(9, km.getMoTa());
            ps.setObject(10, ma);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi update KHUYEN MAIiii");
        }

        return false;
    }

    @Override
    public ArrayList<KhuyenMai> searchDate(String ngayBatDau, String ngayKetThuc) {
        ArrayList<KhuyenMai> listSearch = new ArrayList<>();
        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI\n"
                + "WHERE NgayBatDau >= ? and NgayKetThuc <= ? ";
        try {
            Connection con = dbConNext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSearch.add(new KhuyenMai(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi SearchDate KhuyenMai!");
        }
        return listSearch;
    }

//    @Override
//    public ArrayList<KhuyenMai> searchTinhTrang(int tinhTrang) {
//        ArrayList<KhuyenMai> listSeachTT = new ArrayList<>();
//        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau,"
//                + " NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI where TrangThai =?";
//        try {
//            Connection con = cdao.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, tinhTrang);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                listSeachTT.add(new KhuyenMai(rs.getString(1),
//                        rs.getString(2),
//                        rs.getFloat(3),
//                        rs.getDouble(4),
//                        rs.getInt(5),
//                        rs.getDate(6),
//                        rs.getDate(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getString(10)));
//            }
//        } catch (Exception e) {
//            System.out.println("Lỗi SearchTinhTrang KhuyenMai!");
//        }
//        return listSeachTT;
//    }
//    @Override
//    public ArrayList<KhuyenMai> search(String maKM) {
//        ArrayList<KhuyenMai> listTimKiem = new ArrayList<>();
//        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI";
//        try {
//            Connection con = cdao.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                listTimKiem.add(new KhuyenMai(rs.getString(1),
//                        rs.getString(2),
//                        rs.getFloat(3),
//                        rs.getDouble(4),
//                        rs.getInt(5),
//                        rs.getDate(6),
//                        rs.getDate(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getString(10)));
//            }
//        } catch (Exception e) {
//            System.out.println("Lỗi Tim Kiem Khuyến Mại!");
//        }
//        return listTimKiem;
//    }
//    @Override
//    public ArrayList<KhuyenMai> searchHinhThuc(int hinhThuc) {
//        ArrayList<KhuyenMai> listSeachHT = new ArrayList<>();
//        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, "
//                + "NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI where HinhThucGiam =?";
//        try {
//            Connection con = cdao.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, hinhThuc);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                listSeachHT.add(new KhuyenMai(rs.getString(1),
//                        rs.getString(2),
//                        rs.getFloat(3),
//                        rs.getDouble(4),
//                        rs.getInt(5),
//                        rs.getDate(6),
//                        rs.getDate(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getString(10)));
//            }
//        } catch (Exception e) {
//            System.out.println("Lỗi SearchHinhThuc KhuyenMai!");
//        }
//        return listSeachHT;
//    }
    @Override
    public ArrayList<KhuyenMai> timKiem(String ma, String ten, float mucGiam) {
        ArrayList<KhuyenMai> listTimKiem = new ArrayList<>();
        String sql = "SELECT Ma, Ten, PhanTramGiam, SoLuong, NgayBatDau, NgayKetThuc, TrangThai, MoTa FROM KHUYENMAI WHERE Ma =? or Ten =? or PhanTramGiam = ?";
        try {
            Connection con = dbConNext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ps.setString(2, ten);
            ps.setFloat(3, mucGiam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTimKiem.add(new KhuyenMai(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi Tim Kiem KhuyenMai!");
        }
        return listTimKiem;
    }
    public static void main(String[] args) {
        KhuyenMaiRepository km = new KhuyenMaiRepository();
        System.out.println(km.getAllKM());
    }
}

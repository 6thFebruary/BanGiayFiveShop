/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.ChiTietGiay;
import Model.DanhMuc;
import Model.SanPham;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SanPhamRepository {

    DBConnext dbConnext;

    public ArrayList<ChiTietGiay> getAllSanPham() {
        ArrayList<ChiTietGiay> listctg = new ArrayList<>();
        String sql = "  SELECT dbo.SANPHAM.Id, dbo.SANPHAM.Ma, dbo.SanPham.Ten as TenSanPham, dbo.DANHMUC.Ten as TenDanhMuc,\n"
                + "                dbo.CHITIETGIAY.GiaBan, dbo.CHITIETGIAY.MoTa,\n"
                + "               dbo.CHITIETGIAY.TrangThai, dbo.CHITIETGIAY.MoTa, dbo.CHITIETGIAY.HinhAnh\n"
                + "                FROM dbo.CHITIETGIAY\n"
                + "               INNER JOIN dbo.SanPham ON dbo.CHITIETGIAY.IdSanPham = dbo.SanPham.Id\n"
                + "                INNER JOIN dbo.DANHMUC ON dbo.CHITIETGIAY.IdDanhMuc = dbo.DANHMUC.Id;";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng ChiTietGiay và thêm vào danh sách
                ChiTietGiay ctg = new ChiTietGiay();
                ctg.setId(rs.getInt("Id"));
                ctg.setGiaBan(rs.getBigDecimal("GiaBan"));
                ctg.setMota(rs.getString("MoTa"));
                ctg.setTrangThai(rs.getInt("TrangThai"));
                ctg.setHinhAnh(rs.getString("HinhAnh"));
                // Tạo đối tượng DanhMuc và thêm vào ChiTietGiay
                DanhMuc dm = new DanhMuc();
                dm.setTen(rs.getString("TenDanhMuc"));
                ctg.setIdDanhMuc(dm);
                // Tạo đối tượng SanPham và thêm vào ChiTietGiay
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("Id"));
                sp.setMa(rs.getString("Ma"));
                sp.setTen(rs.getString("TenSanPham"));
                ctg.setIdSanPham(sp);

                // Thêm ChiTietGiay vào danh sách
                listctg.add(ctg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listctg;
    }
    public static void main(String[] args) {
        
    }
}

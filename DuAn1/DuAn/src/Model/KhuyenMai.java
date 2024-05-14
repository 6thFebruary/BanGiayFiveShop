/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Windows
 */
public class KhuyenMai {
    private int id;
    private String ma;
    private String ten;
    private Float phanTramGiam;
    private Double giaGiam;
    private int soLuong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int hinhThucGiam;
    private int trangThai;
    private String moTa;

    public KhuyenMai(String string) {
    }

    public KhuyenMai(int id, String ma, String ten, Float phanTramGiam, Double giaGiam, int soLuong, Date ngayBatDau, Date ngayKetThuc, int hinhThucGiam, int trangThai, String moTa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.phanTramGiam = phanTramGiam;
        this.giaGiam = giaGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucGiam = hinhThucGiam;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Float getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(Float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getHinhThucGiam() {
        return hinhThucGiam;
    }

    public void setHinhThucGiam(int hinhThucGiam) {
        this.hinhThucGiam = hinhThucGiam;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    
}

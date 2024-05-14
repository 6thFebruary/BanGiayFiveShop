/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.DateTimeException;
import java.util.Date;

/**
 *
 * @author Windows
 */
public class HoaDon {
    private String id;
    private String ma;
    private String idND;
    private String idKH;
    private String idKM;
    private Double tongTien;
    private Double thanhTien;
    private Date NgayThanhToan;
    private Date NgayTao;
    private Date NgaySua;
    private String moTa;
    private Integer trangThai;

    public HoaDon() {
    }

    public HoaDon(String id, String ma, String idND, String idKH, String idKM, Double tongTien, Double thanhTien, Date NgayThanhToan, Date NgayTao, Date NgaySua, String moTa, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.idND = idND;
        this.idKH = idKH;
        this.idKM = idKM;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.NgayThanhToan = NgayThanhToan;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getIdND() {
        return idND;
    }

    public void setIdND(String idND) {
        this.idND = idND;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdKM() {
        return idKM;
    }

    public void setIdKM(String idKM) {
        this.idKM = idKM;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    
    
    
}

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
public class HoaDonChiTiet {
    private String id;
    private String idHD;
    private String idCTG;
    private Double donGia;
    private Integer soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String id, String idHD, String idCTG, Double donGia, Integer soLuong, Date ngayTao, Date ngaySua, Integer trangThai) {
        this.id = id;
        this.idHD = idHD;
        this.idCTG = idCTG;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdCTG() {
        return idCTG;
    }

    public void setIdCTG(String idCTG) {
        this.idCTG = idCTG;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

  

   

}

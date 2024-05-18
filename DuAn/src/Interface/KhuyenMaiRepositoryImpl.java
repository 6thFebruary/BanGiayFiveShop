/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.KhuyenMai;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public interface KhuyenMaiRepositoryImpl {
    public ArrayList<KhuyenMai> getAllKM();

    public boolean add(KhuyenMai km);
    
    public boolean update(String ma, KhuyenMai km);
    
    public ArrayList<KhuyenMai> timKiem(String ma, String ten, float mucGiam);
    public ArrayList<KhuyenMai> searchDate(String ngayBatDau, String ngayKetThuc);
}

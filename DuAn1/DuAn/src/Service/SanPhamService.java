/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.SanPhamImpl;
import Model.ChiTietGiay;
import Model.SanPham;
import Repo.SanPhamRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SanPhamService implements SanPhamImpl {

    SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public ArrayList<SanPham> getAllSanPhamService() {
        return sanPhamRepository.getAllSanPham();
    }

}

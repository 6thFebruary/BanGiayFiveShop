/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.DanhMucImpl;
import Model.DanhMuc;
import Repo.DanhMucRepository;
import java.util.ArrayList;

public class DanhMucService implements DanhMucImpl{
    DanhMucRepository danhmucRepository = new DanhMucRepository();

    @Override
    public ArrayList<DanhMuc> getAllDanhMuc() {
        return danhmucRepository.getAllDanhMuc();
         }
}

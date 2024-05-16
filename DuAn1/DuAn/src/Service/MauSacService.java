/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.MauSacImpl;
import Model.MauSac;
import Repo.MauSacRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class MauSacService implements MauSacImpl {

    MauSacRepository mauSacRepositorys = new MauSacRepository();

    @Override
    public ArrayList<MauSac> getAllMauSac() {
        return mauSacRepositorys.getAllMauSacs();
    }
}

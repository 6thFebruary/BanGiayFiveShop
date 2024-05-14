/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.NSXImpl;
import Model.NSX;
import Repo.NSXRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NSXService implements NSXImpl {

    NSXRepository nSXRepository = new NSXRepository();

    @Override
    public ArrayList<NSX> getAllNsx() {
        return nSXRepository.getAllNsxs();
    }
}

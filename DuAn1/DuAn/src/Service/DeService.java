/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.DeImpl;
import Model.De;
import Repo.DeRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DeService implements DeImpl{
    DeRepository deRepository = new DeRepository();

    @Override
    public ArrayList<De> getAllDe() {
    return deRepository.getAllde();
    }
}

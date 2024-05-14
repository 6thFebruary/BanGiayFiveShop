/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.SizeImpl;
import Model.SIZE;
import Repo.SizeRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SizeService implements SizeImpl{
     SizeRepository sizeRepository = new SizeRepository();

    @Override
    public ArrayList<SIZE> getAllSize() {
    return sizeRepository.getAllSize();
            }
}

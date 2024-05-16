/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.ChatLieuimpl;
import Model.ChatLieu;
import Repo.ChatLieuRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ChatLieuService implements ChatLieuimpl{
    ChatLieuRepository chatLieuRepository = new ChatLieuRepository();

    @Override
    public ArrayList<ChatLieu> getALlChatLieus() {
        return chatLieuRepository.getallChatLieus();
    }
}

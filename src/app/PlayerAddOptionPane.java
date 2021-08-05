/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Match;
import model.Player;

/**
 *
 * @author stim
 */
public class PlayerAddOptionPane extends JOptionPane {

    JTextField name;
    JTextField address;
    JTextField phone;
    JTextField email;
    Object[] field;
    
    {
        name = new JTextField(20);
        address = new JTextField(20);
        phone = new JTextField(20);
        email = new JTextField(20);
        
        field = new Object[]{
            "Name", name, "Address", address, "Phone", phone, "Email", email };
    }
    
    public PlayerAddOptionPane() {
    }

    Player showOption(String boardName) {
        Player player = new Player();
    String userInput=null;
        int i = PlayerAddOptionPane.showConfirmDialog(null, field, "Input "+boardName+"?", JOptionPane.OK_CANCEL_OPTION);
        if(i==0){
        
            if(!name.getText().equals("")){
                player.setName(name.getText());
            }
            if(!address.getText().equals("")){
                player.setAddress(address.getText());
            }
            if(!phone.getText().equals("")){
                player.setPhone(Integer.parseInt(phone.getText()));
            }
            if(!email.getText().equals("")){
                player.setEmail(email.getText());
            }
        }
        return player;
    }

}

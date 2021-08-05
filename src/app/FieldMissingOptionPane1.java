/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Match;

/**
 *
 * @author stim
 */
public class FieldMissingOptionPane1 extends JOptionPane {

    JTextField input;
    Object[] field;
    
    {
        input = new JTextField(20);
        
        field = new Object[]{
            "Value", input };
    }
    
    public FieldMissingOptionPane1() {
    }

    String showOption(String fieldName) {
        
    String userInput=null;
        int i = FieldMissingOptionPane1.showConfirmDialog(null, field, "Input "+fieldName+"?", JOptionPane.OK_CANCEL_OPTION);
        if(i==0){
        userInput = input.getText();
        }
        return userInput;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Match;

/**
 *
 * @author stim
 */
public class MatchStartOptionPane extends JOptionPane{
     JComboBox<String> nameA;
     JComboBox<String> nameB;
     JComboBox<String> matchType;
     JComboBox<Integer> matchRate;
     Object[] fields;
    
    
     {
         String[] players = TriptaSports.playerNames;
         String[] matchOption  = {"Board","Point","Tuornament"};
        Integer[] rateOption = {3,4,5};
        nameA = new JComboBox<>(players);
        nameB = new JComboBox<>(players);
        matchType = new JComboBox<>(matchOption);
        matchRate = new JComboBox<>(rateOption);
        
         fields = new Object[]{
            "Player-1", nameA,
            "Player-2", nameB,
            "Type",matchType,
            "Rate", matchRate
        };
    }

    public MatchStartOptionPane() {
    }
    
    Match showStart(){
        Match match = null;
        int i =  MatchStartOptionPane.showConfirmDialog(null,fields,"Start new game?", JOptionPane.OK_CANCEL_OPTION);
        System.out.println(i);
        if(i==0){
        match = new Match();
        if(!"NULL".equals(nameA.getSelectedItem().toString())){
        match.setPlayerOne(nameA.getSelectedItem().toString());
        }else{
         match.setPlayerOne("PlayerA");   
        }
        
        if(!"NULL".equals(nameB.getSelectedItem().toString())){
        match.setPlayerTwo(nameB.getSelectedItem().toString());
        }else{
         match.setPlayerTwo("PlayerB");   
        }
        
        match.setGameType(matchType.getSelectedItem().toString());
        match.setRate(Integer.parseInt(matchRate.getSelectedItem().toString()));
        }
    return match;
    }
    
}

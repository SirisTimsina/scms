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
public class MatchEndOptionPane extends JOptionPane {
    String board;
    JComboBox<String> winner;
    JComboBox remark;
    Object[] fields;

   String[] possibleWinners = null;
    
    public MatchEndOptionPane(String board) {
        String[] possibleRemarks = {"Looser will pay", "Winner will pay"};
        remark =  new JComboBox<>(possibleRemarks);
        
         switch(board){
            
            case "BOARD-A":
                possibleWinners = new String[] {TriptaSports.currentPlayers.get("AA").getName(),TriptaSports.currentPlayers.get("AB").getName()};
                winner = new JComboBox<>(possibleWinners);
                
                break;
            case "BOARD-B":
                possibleWinners = new String[]  {TriptaSports.currentPlayers.get("BA").getName(),TriptaSports.currentPlayers.get("BB").getName()};
                winner = new JComboBox<>(possibleWinners);
                break;
            case "BOARD-C":
                possibleWinners = new String[] {TriptaSports.currentPlayers.get("CA").getName(),TriptaSports.currentPlayers.get("CB").getName()};
                winner = new JComboBox<>(possibleWinners);
                break;
            case "BOARD-D":
               possibleWinners = new String[] {TriptaSports.currentPlayers.get("DA").getName(),TriptaSports.currentPlayers.get("DB").getName()};
                winner = new JComboBox<>(possibleWinners);
                break;
            default :
               possibleWinners = new String[] {"Player-A","Player-B"};
                winner = new JComboBox<>(possibleWinners);
                break;
        
        }

        fields = new Object[]{
            "Winner", winner,
            "Remark", remark,};
    }

    Match showEnd() {
        
    Match match = null;
        int i = MatchEndOptionPane.showConfirmDialog(null, fields, "Game ended properly??", JOptionPane.OK_CANCEL_OPTION);
        System.out.println(i);
//        System.out.println(TriptaSports.gameOnBoardA);
        if(i==0){
        match = new Match();
        match.setRemark(winner.getSelectedItem().toString() +": " + remark.getSelectedItem().toString());
        
        }
        return match;
    }

}

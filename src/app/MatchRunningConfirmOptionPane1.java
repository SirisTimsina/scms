
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Color;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import model.Match;

/**
 *
 * @author stim
 */
public class MatchRunningConfirmOptionPane1 extends JOptionPane {
    JCheckBox running;
//    JTextField endTime;

JSpinner timeSpinner;
    JComboBox<String> winner;
    JComboBox remark;
    Object[] fields;
    
String[] possibleWinners = null;

    {
        

        
    }
    
    public MatchRunningConfirmOptionPane1(String board) {
        
        running = new JCheckBox("Game already ended?");
        running.setForeground(Color.red);
        running.setBackground(Color.yellow);
//        endTime = new JTextField(20);
        
         String[] possibleRemarks = {"Looser will pay", "Winner will pay"};
         remark =  new JComboBox<>(possibleRemarks);
        
        Date date = new Date();
        timeSpinner = new JSpinner( new SpinnerDateModel(date,null, null, Calendar.HOUR_OF_DAY) );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        
        
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
                 possibleWinners = new String[]  {TriptaSports.currentPlayers.get("CA").getName(),TriptaSports.currentPlayers.get("CB").getName()};
                winner = new JComboBox<>(possibleWinners);
                break;
            case "BOARD-D":
                possibleWinners = new String[]  {TriptaSports.currentPlayers.get("DA").getName(),TriptaSports.currentPlayers.get("DB").getName()};
                winner = new JComboBox<>(possibleWinners);
                break;
            default :
                 possibleWinners = new String[]  {"PlayerA","PlayerB"};
                winner = new JComboBox<>(possibleWinners);
                break;
        
        }

        fields = new Object[]{
            "There is a record of running Game. Please Confirm!", running,
            "End Time", timeSpinner,
            "Winner", winner,
            "Remark", remark};
    }

    Match showEnd() {
        
    Match match = null;
        int i = MatchRunningConfirmOptionPane1.showConfirmDialog(null, fields, "Confirm game end?", JOptionPane.OK_CANCEL_OPTION);
        System.out.println(i);
        if(i==0 && running.isSelected()){
        match = new Match();
        
            System.out.println(timeSpinner.getValue());
            Date d = (Date)timeSpinner.getValue();
           LocalTime lt = LocalTime.of(d.getHours(), d.getMinutes(), d.getSeconds());
        match.setEndTime(lt);
        if(remark.getSelectedIndex() == 1){
            match.setRemark(possibleWinners[0] +": " + remark.getSelectedItem().toString());
        }
        else{
           match.setRemark(possibleWinners[1] +": " + remark.getSelectedItem().toString());
        }
        }
        return match;
    }
        

}

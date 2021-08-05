/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author stim
 */
public class MatchTableModel extends AbstractTableModel{

    private static int sno=0;
    private final List<Match> matchList;
    private final String[] columnNames = new String[] {"S.No.","ID","Board", "Date","Player-1","Player-2","Game-Type","Start-Time","End-Time","Rate","Remark", "EDIT","DELETE"};

    private final Class[] columnClass = new Class[] {Integer.class,Integer.class,String.class, LocalDate.class,String.class,String.class,String.class,LocalTime.class,LocalTime.class,Integer.class,String.class,Boolean.class,Boolean.class};

    public MatchTableModel(List<Match> matchList) {
       this.matchList = matchList;
    }
    
    
    @Override
    public int getRowCount() {
        return matchList.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Match match = matchList.get(rowIndex);
       Object obj = null;
       switch(columnIndex){
           case 0: obj = rowIndex+1;
           case 1: obj = match.getId(); break;
           case 2: obj = match.getBoard(); break;
           case 3: obj = match.getDate(); break;
           case 4: obj = match.getPlayerOne(); break;
           case 5: obj = match.getPlayerTwo(); break;
           case 6: obj = match.getGameType(); break;
           case 7: obj = match.getStartTime(); break;
           case 8: obj = match.getEndTime(); break;
           case 9: obj = match.getRate(); break;
           case 10: obj = match.getRemark(); break;
           case 11: obj = true; break;
           case 12: obj = true; break;
           default: obj = "XXX"; break;
       }
       return obj;
    }
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       Match match = matchList.get(rowIndex);
       Object obj = null;
       switch(columnIndex){
           case 0:  sno++; break;
           case 1:  match.setId((int) aValue); break;
           case 2:  match.setBoard((String) aValue); break;
           case 3:  match.setDate((LocalDate) aValue); break;
           case 4:  match.setPlayerOne((String) aValue); break;
           case 5:  match.setPlayerTwo((String) aValue); break;
           case 6:  match.setGameType((String) aValue); break;
           case 7:  match.setStartTime((LocalTime) aValue); break;
           case 8:  match.setEndTime((LocalTime) aValue); break;
           case 9:  match.setRate((int) aValue); break;
           case 10:  match.setRemark((String) aValue); break;
           case 11: obj = "EDIT"; break;
           case 12: obj = "DELETE"; break;
           default: obj = "XXX"; break;
       }
   }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
 
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
    
    
    
}

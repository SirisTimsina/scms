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
public class GameTableModel extends AbstractTableModel{

    
    private static boolean select=false;
    private final List<Game> gameList;
    private final String[] columnNames = new String[] {"S.No.","MatchID","MatchTime", "Amount","Payer","Paid","PaidOn","PaidAt","Discount","Remark","Select"};

    private final Class[] columnClass = new Class[] {Integer.class,Integer.class,Integer.class,Double.class,String.class,String.class, LocalDate.class,LocalTime.class,Integer.class,String.class,Boolean.class};

    
    public GameTableModel(List<Game> gameList) {
 
        this.gameList = gameList;
        System.out.println("Preparing table");
    }
    
    
    @Override
    public int getRowCount() {
        return gameList.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
       Game game = gameList.get(rowIndex);
       switch(columnIndex){

           case 0: obj = 1+rowIndex; break;
           case 1: obj = game.getMatchId(); break;
           case 2: obj = game.getMatchTime(); break;
           case 3: obj = game.getAmount(); break;
           case 4: obj = game.getPayer(); break;
           case 5: obj = game.getPaid(); break;
           case 6: obj = game.getPaidDate(); break;
           case 7: obj = game.getPaidAt(); break;
           case 8: obj = game.getDiscount(); break;
           case 9: obj = game.getRemark(); break;
           case 10: obj = game.getIsPaid(); break;
         default: obj = "XXX"; break;
        }
       
       return obj;
    }
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {   
       Game game = gameList.get(rowIndex);
       Object obj = null;
       switch(columnIndex){
           
           case 0:  game.setId((int) aValue); break;
           case 1:  game.setMatchId((int) aValue); break;
           case 2:  game.setMatchTime((int) aValue); break;
           case 3:  game.setAmount((double) aValue); break;
           case 4:  game.setPayer((String) aValue); break;
           case 5:  game.setPaid((String) aValue); break;
           case 6:  game.setPaidDate((LocalDate) aValue); break;
           case 7:  game.setPaidAt((LocalTime) aValue); break;
           case 8:  game.setDiscount((int) aValue); break;
           case 9:  game.setRemark((String) aValue); break;
           case 10: game.setIsPaid((boolean)aValue);
                    super.setValueAt(aValue, rowIndex, columnIndex);
                    break;
           default: obj = "XXX"; break;
       }
   }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {   
        return columnIndex==10;
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

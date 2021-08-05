/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author stim
 */
public class PlayerTableModel extends AbstractTableModel{

    
    private static boolean select=false;
    private final List<Player> playerList;
    private final String[] columnNames = new String[] {"S.No.","PlayerId","Name", "Address","Phone","Email","Ranking","Games_Played","Wins"};

    private final Class[] columnClass = new Class[] {Integer.class,Integer.class,String.class,String.class,Long.class,String.class, String.class,Integer.class,Integer.class};
    
    
    public PlayerTableModel(List<Player> playerList) {
 
        this.playerList = playerList;
        System.out.println("Preparing table");
    }
    
    
    @Override
    public int getRowCount() {
        return playerList.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
       Player player = playerList.get(rowIndex);
       switch(columnIndex){

           case 0: obj = 1+rowIndex; break;
           case 1: obj = player.getId(); break;
           case 2: obj = player.getName(); break;
           case 3: obj = player.getAddress(); break;
           case 4: obj = player.getPhone(); break;
           case 5: obj = player.getEmail(); break;
           case 6: obj = player.getRanking(); break;
           case 7: obj = player.getTotalGames(); break;
           case 8: obj = player.getWins(); break;
         default: obj = "XXX"; break;
        }
       
       return obj;
    }
    
    @Override
   public void setValueAt(Object value, int rowIndex, int columnIndex)
   {   
       Player player = playerList.get(rowIndex);
       Object obj = null;
       switch(columnIndex){
           
           case 0:  player.setId((int) value); break;
           case 1:  player.setName((String) value); break;
           case 2:  player.setAddress((String) value); break;
           case 3:  player.setPhone((Long) value); break;
           case 4:  player.setEmail((String) value); break;
           case 5:  player.setRanking((String) value); break;
           case 6:  player.setTotalGames((int) value); break;
           case 7:  player.setWins((int) value); break;
           default: obj = "XXX"; break;
       }
   }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {   
        return false;
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

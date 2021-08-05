/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Player;

/**
 *
 * @author stim
 */
public class PlayerDao {
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ts_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static PreparedStatement connect(String sql, boolean returnKey) {
        PreparedStatement ps = null;
        try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            if (returnKey) {
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = conn.prepareStatement(sql);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database not available!");
        }
        return ps;
    }
    
    
    public boolean addPlayer(Player player){
        boolean ok = false;
        String sql = "insert into player_tbl (name,address,phone,email) values(?,?,?,?)";
        PreparedStatement ps = connect(sql,false);
        if(ps!=null){
            try{
                ps.setString(1, player.getName());
                ps.setString(2, player.getAddress());
                ps.setLong(3, player.getPhone());
                ps.setString(4, player.getEmail());
                if(ps.executeUpdate()==1){
                    ok = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return ok;
    }

    public int setPlayerOnGame(Player player){
        int pk = 0;
        String sql = "insert into player_tbl (name,address,phone,email) values(?,?,?,?)";
        PreparedStatement ps = connect(sql,true);
        if(ps!=null){
            try{
                ps.setString(1, player.getName());
                ps.setString(2, player.getAddress());
                ps.setLong(3, player.getPhone());
                ps.setString(4, player.getEmail());
                if(ps.executeUpdate()==1){
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs!=null && rs.next()){
                        pk = rs.getInt(1);
                    }
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return pk;
    }
    
    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        String sql = "select * from player_tbl";
        PreparedStatement ps = connect(sql,false);
        if(ps!=null){
            try{
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    players.add(new Player( rs.getInt("id"),
                                            rs.getString("name"),
                                            rs.getString("address"),
                                            rs.getLong("phone"),
                                            rs.getString("email"),
                                            rs.getString("ranking"),
                                            rs.getInt("totalGames"),
                                            rs.getInt("wins")));
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return players;
    }
    
    public HashMap<Integer,String> getMapOfAllPlayers(){
        HashMap<Integer,String> players = new HashMap<>();
        String sql = "select * from player_tbl";
        PreparedStatement ps = connect(sql,false);
        if(ps!=null){
            try{
                players.put(0,"NULL");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    players.put(rs.getInt("id"),rs.getString("name"));
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        return players;
    }
}

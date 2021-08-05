/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Game;

/**
 *
 * @author stim
 */
public class GameDao {

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

    public boolean saveGame(Game game) {
        boolean ok = false;
        String sql = "insert into game_table (match_id, match_time, amount, payer, paid, paid_date, paid_at, discount, remark) "
                + "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {

            try {
                ps.setInt(1, game.getMatchId());
                ps.setInt(2, game.getMatchTime());
                ps.setDouble(3, game.getAmount());
                ps.setString(4, game.getPayer());
                ps.setString(5, game.getPaid());
                ps.setDate(6, java.sql.Date.valueOf(game.getPaidDate()));
                ps.setTime(7, java.sql.Time.valueOf(game.getPaidAt()));
                ps.setDouble(8, game.getDiscount());
                ps.setString(9, game.getRemark());

                if (ps.executeUpdate() == 1) {
                    ok = true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return ok;
    }
    
    public ArrayList<Game> getAllGames(){
        ArrayList<Game> gameList = null;
        String sql = "select * from game_table";
        PreparedStatement ps = connect(sql,false);
        try{
            ResultSet rs = ps.executeQuery();
            gameList = new ArrayList<>();
            while(rs.next()){
                gameList.add(new Game(rs.getInt("id"),
                                        rs.getInt("match_id"),
                                        rs.getInt("match_time"),
                                        rs.getDouble("amount"),
                                        rs.getString("payer"),
                                            rs.getString("paid"),
                                            rs.getDate("paid_date").toLocalDate(),
                                            rs.getTime("paid_at").toLocalTime(),
                                            rs.getInt("discount"),
                                            rs.getString("remark")
                                    ));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return gameList;
    }

    
    public Game getGameDetailOfMatch(int mid){
        return null;
    }
    
    public ArrayList<Game> getGamesOnBoard(String board){
        ArrayList<Game> gameList = null;
        String sql = "select g.id, g.match_id, g.match_time,"
                + "g.amount, g.payer, g.paid, g.paid_date,"
                + "g.paid_at, g.discount, g.remark,"
                + "m.board, m.player_one, m.player_two,"
                + "m.date, m.start_time, m.remark "
                + " from game_table as g, match_table as m"
                + " where m.id = g.match_id and m.date=? and m.board=?";
        PreparedStatement ps = connect(sql,false);
        try{
            
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, board);
            
            ResultSet rs = ps.executeQuery();
            gameList = new ArrayList<>();
            while(rs.next()){
                gameList.add(new Game(rs.getInt("id"),
                                        rs.getInt("match_id"),
                                        rs.getInt("match_time"),
                                        rs.getDouble("amount"),
                                        rs.getString("board")+": "+rs.getString("player_one")+ " vs "+ rs.getString("player_two")+"==>"+rs.getString("payer")+" Paid ",
                                            rs.getString("paid"),
                                            rs.getDate("paid_date").toLocalDate(),
                                            rs.getTime("paid_at").toLocalTime(),
                                            rs.getInt("discount"),
                                            rs.getString("g.remark")+": "+rs.getDate("date").toLocalDate().toString()+" "+rs.getTime("start_time").toString()
                                    ));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return gameList;
    }
    
    public ArrayList<Game> getGamesForPaymentByPayer(String payer){
        return null;
    }
    
    public ArrayList<Game> getGamesForPaymentBySelection(ArrayList<Integer> matchids){
        return null;
    }
    
    public ArrayList<Game> getAllPaidGames(String board){
       ArrayList<Game> games = null;
      String sql = "select g.id, g.match_id, g.match_time,"
                + "g.amount, g.payer, g.paid, g.paid_date,"
                + "g.paid_at, g.discount, g.remark,"
                + "m.board, m.player_one, m.player_two,"
                + "m.date, m.start_time, m.remark "
                + " from game_table as g, match_table as m"
                + " where m.id = g.match_id and m.board=? and m.date=? and g.paid=?";
        PreparedStatement ps = connect(sql,false);
        try{
            ps.setString(1, board);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setString(3, "1");
            ResultSet rs = ps.executeQuery();
            games = new ArrayList<>();
            while(rs.next()){
                games.add(new Game(rs.getInt("id"),
                                        rs.getInt("match_id"),
                                        rs.getInt("match_time"),
                                        rs.getDouble("amount"),
                                        rs.getString("board")+": "+rs.getString("player_one")+ " vs "+ rs.getString("player_two")+"==>"+rs.getString("payer")+" Paid ",
                                            rs.getString("paid"),
                                            rs.getDate("paid_date").toLocalDate(),
                                            rs.getTime("paid_at").toLocalTime(),
                                            rs.getInt("discount"),
                                            rs.getString("g.remark")+": "+rs.getDate("date").toLocalDate().toString()+" "+rs.getTime("start_time").toString()
                                    ));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return games;
        
    }
    
    public ArrayList<Game> getAllUnpaidGames(String board){
        ArrayList<Game> games = null;
      String sql = "select g.id, g.match_id, g.match_time,"
                + "g.amount, g.payer, g.paid, g.paid_date,"
                + "g.paid_at, g.discount, g.remark,"
                + "m.board, m.player_one, m.player_two,"
                + "m.date, m.start_time, m.remark "
                + " from game_table as g, match_table as m"
                + " where m.id = g.match_id and m.board=? and m.date=? and g.paid=?";
        PreparedStatement ps = connect(sql,false);
        try{
            ps.setString(1, board);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setString(3, "0");
            ResultSet rs = ps.executeQuery();
            games = new ArrayList<>();
            while(rs.next()){
                games.add(new Game(rs.getInt("id"),
                                        rs.getInt("match_id"),
                                        rs.getInt("match_time"),
                                        rs.getDouble("amount"),
                                        rs.getString("board")+": "+rs.getString("player_one")+ " vs "+ rs.getString("player_two")+"==>"+rs.getString("payer")+" Paid ",
                                            rs.getString("paid"),
                                            rs.getDate("paid_date").toLocalDate(),
                                            rs.getTime("paid_at").toLocalTime(),
                                            rs.getInt("discount"),
                                            rs.getString("g.remark")+": "+rs.getDate("date").toLocalDate().toString()+" "+rs.getTime("start_time").toString()
                                    ));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return games;
    }
    
    public ArrayList<Game> getGamesByPaidDate(LocalDate date){
        return null;
    }
    
    public ArrayList<Game> getGamesByPaidTime(LocalTime time){
        // returns payment done for multiple games at once
        return null;
    }
    
    public boolean updateGame(Game game){
       boolean ok = false;
       String sql = "update game_table set amount=?,discount=?,payer=?,discount=? where id=?";
       PreparedStatement ps = connect(sql, false);
       
       try{
           ps.setDouble(1, game.getAmount());
           ps.setDouble(2, game.getDiscount());
           ps.setString(3, game.getPayer());
           ps.setString(4, game.getRemark());
           int i = ps.executeUpdate();
           
           if(i>0){
               System.out.println("No of records updated: "+i);
               ok = true;
           }
           
       }catch(SQLException ex){
           System.out.println(ex);
       }
       return ok;
    }
    
    public void endGameDetailsUpdate(Game game){
        // calculate and update game_time, paid, amount amount ;
        String sql = "update game_table set match_time=?, amount=?, remark=? where match_id=?";
        PreparedStatement ps = connect(sql, false);
        try{
            ps.setInt(1, game.getMatchTime());
            ps.setDouble(2, game.getAmount());
            ps.setString(3, game.getRemark());
            ps.setInt(4, game.getMatchId());
            
            if(ps.executeUpdate()==1){
                System.out.println("Game end calculations update success..");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
   
   public boolean payForGame(Game game){
       // update payer, paid, paid_at, paid_date, discount, remark
       boolean ok= false;
       String sql = "update game_table set payer=?, paid=?, paid_date=?, paid_at=?,amount=?, discount=?, remark=? where id= ?";
       PreparedStatement ps = connect(sql,false);
       try{
           ps.setString(1, game.getPayer());
           ps.setString(2, "1");
           ps.setDate(3, Date.valueOf(LocalDate.now()));
           ps.setTime(4, Time.valueOf(LocalTime.now()));
           ps.setDouble(5, game.getAmount());
           ps.setDouble(6, game.getDiscount());
           ps.setString(7, game.getRemark());
           ps.setInt(8, game.getId());
           if(ps.executeUpdate()==1){
           ok = true;
           }
       }catch(SQLException ex){
           System.out.println(ex);
       }
       return ok;
   }
   
   
}

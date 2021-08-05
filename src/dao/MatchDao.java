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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Match;

/**
 *
 * @author stim
 */
public class MatchDao {

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

    public Match setMatch(Match match) {
        Match m = null;
        String sql = "insert into match_table (date, board, player_one, player_two, game_type, start_time, end_time, rate ) values(?,?,?,?,?,?,?,?)";
        // only relavent fields during game start is added to start a game
        PreparedStatement ps = connect(sql, true);
        if (ps != null) {
            try {
                ps.setDate(1, Date.valueOf(match.getDate()));
                ps.setString(2, match.getBoard());
                ps.setString(3, match.getPlayerOne());
                ps.setString(4, match.getPlayerTwo());
                ps.setString(5, match.getGameType());
                ps.setTime(6, Time.valueOf(match.getStartTime()));
                ps.setTime(7, Time.valueOf("00:00:00"));
                ps.setInt(8, match.getRate());

                if (ps.executeUpdate() !=0) {
                    
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs != null && rs.next()) {
                        m = getMatchById(rs.getInt(1));
                        
                        // save Game 
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, "Database connection problem! : add match failed!");
        }
        return m;
    }

    public List<Match> getAllMatch() {
//        get all todays matches
        ArrayList<Match> al = new ArrayList<>();
        String sql = "select * from match_table where date=?";
        PreparedStatement ps = connect(sql, false);
        if (ps != null) {
            try {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ResultSet rs = ps.executeQuery();
                if (rs == null) {
                    System.out.println("Null returned!");
                }
                while (rs.next()) {
                    LocalTime et;
                    if (rs.getTime("end_time") != null) {
                        et = rs.getTime("end_time").toLocalTime();
                    } else {
                        et = LocalTime.parse("00:00:00");
                    }
                    al.add(new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            et, rs.getInt("rate"),rs.getString("remark")));
                }

            } catch (SQLException ex) {
//            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
        return al;
    }

    public ArrayList<Match> getRunningMatch() {
        ArrayList<Match> al = new ArrayList<>();
        String sql = "select * from match_table where date=? and end_time=?";
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {
            try {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setTime(2, Time.valueOf(LocalTime.MIDNIGHT));  // Not any relevant time..

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    al.add(new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(), rs.getInt("rate"),
                            rs.getString("remark")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return al;
    }

    public List<Match> getCompletedMatch() {
        ArrayList<Match> al = new ArrayList<>();
        String sql = "select * from match_table where date=? and end_time!=?";  // choose other fields to make match complete
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {
            try {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setTime(2, Time.valueOf(LocalTime.MIDNIGHT));  // Not any relevant time..

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    al.add(new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(), rs.getInt("rate"),
                            rs.getString("remark")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return al;
    }

    public List<Match> getMatchByPlayer(String player) {
        ArrayList<Match> al = new ArrayList<>();
        String sql = "select * from match_table where player_one=? or player_two=?";
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {
            try {

                ps.setString(1, player);
                ps.setString(2, player);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    al.add(new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(), rs.getInt("rate"),
                            rs.getString("remark")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return al;
    }

    public List<Match> getMatchByBothPlayer(String playerOne, String playerTwo) {
        ArrayList<Match> al = new ArrayList<>();
        String sql = "select * from match_table where player_one=? or player_two=?";
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {
            try {

                ps.setString(1, playerOne);
                ps.setString(2, playerTwo);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    al.add(new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(), rs.getInt("rate"),
                            rs.getString("remark")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return al;
    }

    public Match getMatchById(int id) {
        Match m = null;
        String sql = "select * from match_table where id=?";
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {
            try {

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    m = new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(), rs.getInt("rate"),
                            rs.getString("remark"));
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return m;
    }

    public ArrayList<Match> getMatchByDate(Date date) {
        ArrayList<Match> ml = new ArrayList<>();
        String sql = "select * from match_table where date=?";
        PreparedStatement ps = connect(sql, false);

        if (ps != null) {
            try {

                ps.setDate(1, date);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    ml.add(new Match(rs.getInt("id"), rs.getString("board"), rs.getDate("date").toLocalDate(), rs.getString("player_one"),
                            rs.getString("player_two"), rs.getString("game_type"), rs.getTime("start_time").toLocalTime(),
                            rs.getTime("end_time").toLocalTime(), rs.getInt("rate"),
                            rs.getString("remark")));
                }

            } catch (Exception ex) {
//                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return ml;
    }

    public boolean updateMatch(Match match) {
        // update required fields
        boolean ok = false;
        String sql = "update match_table set player_one=?, player_two=?, remark=? where id=?";
        PreparedStatement ps = connect(sql, false);
        if (ps != null) {
            try {

                ps.setString(1, match.getPlayerOne());
                ps.setString(2, match.getPlayerTwo());
                ps.setString(3, match.getRemark());
                ps.setInt(4, match.getId());

                if (ps.executeUpdate() == 1) {
                    ok = true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, "Database connection problem! : add match failed!");
        }
        return ok;
    }

    public boolean updateRemark(Match match) {
        // update required fields
        boolean ok = false;
        String sql = "update match_table set remark=? where id=?";
        PreparedStatement ps = connect(sql, false);
        if (ps != null) {
            try {

                ps.setString(1, match.getRemark());
                ps.setInt(2, match.getId());

                if (ps.executeUpdate() == 1) {
                    ok = true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, "Database connection problem! : add match failed!");
        }
        return ok;
    }

    public boolean updatePlayer(Match match) {
        // only player names are updated
        boolean ok = false;
        
        String sql = "update match_table set player_one=?, player_two=? where id=?";
        PreparedStatement ps = connect(sql, false);
        if (ps != null) {
            try {

                ps.setString(1, match.getPlayerOne());
                ps.setString(2, match.getPlayerTwo());
                ps.setInt(3, match.getId());

                if (ps.executeUpdate() == 1) {
                    ok = true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, "Database connection problem! : add match failed!");
        }
        return ok;
    }

    public boolean endMatch(Match match) {
        // update endTime, discount and remark only;
        boolean ok = false;
        String sql = "update match_table set end_time=?, remark=? where id=?";
        PreparedStatement ps = connect(sql, false);
        if (ps != null) {
            try {
                if(null != match.getEndTime()){
                ps.setTime(1, Time.valueOf(match.getEndTime()));
                // if game is end during confirm-running game which provided game end
                }
                else{
                    ps.setTime(1, Time.valueOf(LocalTime.now()));
                }
                ps.setString(2, match.getRemark());
                ps.setInt(3, match.getId());

                if (ps.executeUpdate() == 1) {
                    ok = true;
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, "Database connection problem! : add match failed!");
        }
        return ok;
    }

    public boolean deleteMatch(Match match) {
        // should be removed form final product..
        // or should only be accessed by previleged user
        boolean ok = false;

        return ok;
    }

}

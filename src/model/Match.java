/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author stim
 */
public class Match {
    
    private int id;
    
    private String board;
    
    private LocalDate date;
    
    private String playerOne;
    
    private String playerTwo;
    
    private String gameType;
    
    private LocalTime startTime;
    
    private LocalTime endTime;
    
    private int rate;
    
    private String remark;
    // winner + paid/not-paid + other

    public Match() {
    }

    public Match(int id, String board, LocalDate date, String playerOne, String playerTwo, String gameType, LocalTime startTime, LocalTime endTime, int rate, String remark) {
        this.id = id;
        this.board = board;
        this.date = date;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameType = gameType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rate = rate;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    

    @Override
    public String toString() {
        if(endTime!=null){
        int playTime=0;
        int hourDuration = endTime.getHour() - startTime.getHour();
        
        playTime = endTime.getMinute() + (hourDuration * 60) - startTime.getMinute();
//      // formula to calculate elapsedTime...

//        int minuteDuration =0;
//        if(hourDuration==0){
//            playTime = endTime.getMinute() - startTime.getMinute();
//        }
//        else{
//            playTime = endTime.getMinute() + (hourDuration * 60) - startTime.getMinute();
//        }
        
        return "MatchStat [ <br>" + "SN=" + id + ", Date=" + date +
                "<br> Players=" + playerOne + " VS " + playerTwo + 
                "<br> PlayTime=" + playTime +" Minutes  @ " + rate + "/Minute, remark=" + remark + ']';
    }
        else{
            return "Match continues..";
        }
 }
    
}

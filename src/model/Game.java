/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author stim
 */
public class Game implements Serializable{
    
    private static final long serialVersionUID = 2348345235423L;
    
    // using one-to-one relation with match
   private int id;
   
   private int   matchId;   // one match  to  one payment with unique constrain
   
//   private List<Integer>   matchId;  
// for many match  to one game_paymeny if using jpa
   
   private int matchTime;  // in minutes
   
   private double amount;
   
   private String payer;
   
   private String paid;
   
   private LocalDate paidDate;
   
   private LocalTime paidAt;   // also useful for many to one 
   
   private int discount;
    
   private String remark;
   
   private boolean isPaid;

    public Game() {
    }

    public Game(int id, int matchId, int matchTime, double amount, String payer, String paid, LocalDate paidDate, LocalTime paidAt, int discount, String remark) {
        this.id = id;
        this.matchId = matchId;
        this.matchTime = matchTime;
        this.amount = amount;
        this.payer = payer;
        this.paid = paid;
        this.paidDate = paidDate;
        this.paidAt = paidAt;
        this.discount = discount;
        this.remark = remark;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public LocalTime getPaidAt() {
        return paidAt;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    
    public void setPaidAt(LocalTime paidAt) {
        this.paidAt = paidAt;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Game(int id, int matchId, int matchTime, double amount, String payer, String paid, LocalDate paidDate, LocalTime paidAt, int discount, String remark, boolean isPaid) {
        this.id = id;
        this.matchId = matchId;
        this.matchTime = matchTime;
        this.amount = amount;
        this.payer = payer;
        this.paid = paid;
        this.paidDate = paidDate;
        this.paidAt = paidAt;
        this.discount = discount;
        this.remark = remark;
        this.isPaid = isPaid;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
    
   
  
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author stim
 */
public class Player {
    
    private int id;
    private String name;
    private String address;
    private long phone;
    private String email;
    private String ranking;
    private int totalGames;
    private int wins;

    public Player() {
    }
    
    public Player(String name) {
        this.name = name;
    }
    public Player(int id, String name, String address, long phone, String email, String ranking, int totalGames, int wins) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ranking = ranking;
        this.totalGames = totalGames;
        this.wins = wins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        String info;
        try{
            info = "Player(" + id + "): Name" + name + ", Phone=" + phone + ", Ranking=" + ranking + ", Winning_rate=" 
                                .concat(wins/totalGames*100 + "%");
        }catch(ArithmeticException ex){
            info = "Player(" + id + "): Name" + name + ", Phone=" + phone + ", Ranking=" + ranking + ", Winning_rate=" 
                                .concat("0%");
        }
        
        return info;
        
        }
    
    
    public String basicInfo(){
        return "Name:"+name+" Phone: "+phone+" Ranking: "+ranking;
    }
}

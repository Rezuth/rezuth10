/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;





public class Purchase {
    
    private String username;
    private String telephone;
    private String match;
    private String area;
    private int nrTickets;
    private float totalSum;

    public Purchase(String username, String telephone, String match, String area, int nrTickets, float totalSum) {
        this.username = username;
        this.telephone=telephone;
        this.match = match;
        this.area = area;
        this.nrTickets = nrTickets;
        this.totalSum = totalSum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNrTickets() {
        return nrTickets;
    }

    public void setNrTickets(int nrTickets) {
        this.nrTickets = nrTickets;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }
   
    
    

    
    
}

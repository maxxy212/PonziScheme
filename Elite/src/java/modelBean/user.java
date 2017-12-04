/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBean;

/**
 *
 * @author Maxwell
 */

public class user {
    private String pnumber;
    private String password;
    private String fname;
    private String sname;
    private String email;
    private String bank;
    private String acc;
    private String accname;
    private String gender;
    private String pac;
  

        
    public user()
    {
        
    }

    public user(String pnumber) {
        this.pnumber = pnumber;
    }
    
    

    public user(String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
    }

    public user(String pnumber, String password, String fname, String sname) {
        this.pnumber = pnumber;
        this.password = password;
        this.fname = fname;
        this.sname = sname;
    }

    public user(String pnumber, String password, String fname, String sname, String email, String bank, String acc, String accname, String gender, String pac) {
        super();
        this.pnumber = pnumber;
        this.password = password;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        this.bank = bank;
        this.acc = acc;
        this.accname = accname;
        this.gender = gender;
        this.pac = pac;
    }
    
    
    
    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }
    
    

    @Override
    public String toString() {
        return "user{" + "name=" + fname + ", surname=" + sname + '}';
    }
}

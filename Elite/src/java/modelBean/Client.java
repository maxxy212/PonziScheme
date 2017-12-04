/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maxwell
 */

public class Client {

    //pnumber `fname`, `sname`, `email`, `bank`, `acc`, `password`, `gender
    private int acc;
    private String fname,sname,email,bank,password,gender,pnumber;
    public Client() 
    {
        
    }

    public Client(String pnumber, int acc, String fname, String sname, String email, String bank, String password, String gender) {
        this.pnumber = pnumber;
        this.acc = acc;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        this.bank = bank;
        this.password = password;
        this.gender = gender;
    }
    
    public static HttpSession getsession()
    {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    public static HttpServletRequest getreq()
    {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}

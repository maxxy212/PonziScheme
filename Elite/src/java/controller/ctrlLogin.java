/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import db.userDataManager;
import db.ClientBean;
import static db.ClientBean.closeAll;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import db.userDataManager;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.servlet.ServletException;
//import modelBean.SessionCount;
import modelBean.user;


/**
 *
 * @author Maxwell
 */
@ManagedBean
@SessionScoped
public class ctrlLogin implements Serializable {
    
    private static final long serialVersionUID = 1094801825228386363L;
        
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
    private int counter;
    private int donate;
    private String time;
   
    private user session;
    
    private userDataManager dmanager;
    private UIComponent component;
    private boolean showPopup;
    
     private String tpnumber;
    private String tfname;
    private String tsname;
    private String temail;
    private String tbank;
    private String tacc;
    private String taccname;
    private String tgender;

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    @PostConstruct
    public void init()
    {
        if(dmanager == null)
        {
            dmanager = new userDataManager();
        }
        
    }


    public String login() throws IOException, ServletException, SQLException
    {
                        
        session = dmanager.findUser(pnumber, password);
    
        if(session!= null)
        {
//            HttpSession hs = Client.getsession();
//            hs.setAttribute("pnumber", pnumber);
            
                setFname(session.getFname());
                setSname(session.getSname());
                setPnumber(session.getPnumber());
                setEmail(session.getEmail());
                setBank(session.getBank());
                setAcc(session.getAcc());
                setAccname(session.getAccname());
                setGender(session.getGender());
                setPac(session.getPac());
                
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", pnumber);
            return "home.xhtml?faces-redirect=true";
            
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(component.getClientId(), new FacesMessage("Phone number or password invalid *"));
            return "";
        }
        
    }
    
    
    public String logout()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("session");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        session = null;
        return "index.xhtml?faces-redirect=true";
    }
    
    public String status(int counter)
    {
        String stats;
        if(counter < 2)
        {
            stats ="Awaiting Payment";
        }
        else
        {
            stats ="Paid";
        }
        return stats;
    }
    
    public String Getpayto5k()
    {
        String pnum = null;
        try {
            conn = ClientBean.getConne();
            
            String sql = "SELECT pnumber FROM 5k WHERE counter < 2 ORDER BY transId ASC Limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                pnum = rs.getString("pnumber");
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
        
        return pnum;
    }
    public void payto5k()
    {
        
        try 
        {   
            String res;
            res = Getpayto5k();
            conn = ClientBean.getConne();
            String sql = "select * from client where pnumber= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, res);
            rs = pst.executeQuery();
            if(rs.next())
            {
                setTpnumber(rs.getString("pnumber"));
                setTfname(rs.getString("fname"));
                setTsname(rs.getString("sname"));
                setTemail(rs.getString("email"));
                setTbank(rs.getString("bank"));
                setTacc(rs.getString("acc"));
                setTaccname(rs.getString("accname"));
                setTgender(rs.getString("gender"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
   
    }
    public String Getpayto10k()
    {
        String pnum = null;
        try {
            conn = ClientBean.getConne();
            
            String sql = "SELECT pnumber FROM 10k WHERE counter < 2 ORDER BY transId ASC Limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                pnum = rs.getString("pnumber");
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
        
        return pnum;
    }
    public void payto10k()
    {
        
        try 
        {   
            String res;
            res = Getpayto10k();
            conn = ClientBean.getConne();
            String sql = "select * from client where pnumber= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, res);
            rs = pst.executeQuery();
            if(rs.next())
            {
                setTpnumber(rs.getString("pnumber"));
                setTfname(rs.getString("fname"));
                setTsname(rs.getString("sname"));
                setTemail(rs.getString("email"));
                setTbank(rs.getString("bank"));
                setTacc(rs.getString("acc"));
                setTaccname(rs.getString("accname"));
                setTgender(rs.getString("gender"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
   
    }
    public String Getpayto20k()
    {
        String pnum = null;
        try {
            conn = ClientBean.getConne();
            
            String sql = "SELECT pnumber FROM 20k WHERE counter < 2 ORDER BY transId ASC Limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                pnum = rs.getString("pnumber");
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
        
        return pnum;
    }
    public void payto20k()
    {
        
        try 
        {   
            String res;
            res = Getpayto20k();
            conn = ClientBean.getConne();
            String sql = "select * from client where pnumber= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, res);
            rs = pst.executeQuery();
            if(rs.next())
            {
                setTpnumber(rs.getString("pnumber"));
                setTfname(rs.getString("fname"));
                setTsname(rs.getString("sname"));
                setTemail(rs.getString("email"));
                setTbank(rs.getString("bank"));
                setTacc(rs.getString("acc"));
                setTaccname(rs.getString("accname"));
                setTgender(rs.getString("gender"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
   
    }
    public String Getpayto50k()
    {
        String pnum = null;
        try {
            conn = ClientBean.getConne();
            
            String sql = "SELECT pnumber FROM 50k WHERE counter < 2 ORDER BY transId ASC Limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                pnum = rs.getString("pnumber");
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
        
        return pnum;
    }
    public void payto50k()
    {
        
        try 
        {   
            String res;
            res = Getpayto50k();
            conn = ClientBean.getConne();
            String sql = "select * from client where pnumber= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, res);
            rs = pst.executeQuery();
            if(rs.next())
            {
                setTpnumber(rs.getString("pnumber"));
                setTfname(rs.getString("fname"));
                setTsname(rs.getString("sname"));
                setTemail(rs.getString("email"));
                setTbank(rs.getString("bank"));
                setTacc(rs.getString("acc"));
                setTaccname(rs.getString("accname"));
                setTgender(rs.getString("gender"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
   
    }
    public String Getpayto100k()
    {
        String pnum = null;
        try {
            conn = ClientBean.getConne();
            
            String sql = "SELECT pnumber FROM 100k WHERE counter < 2 ORDER BY transId ASC Limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                pnum = rs.getString("pnumber");
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
        
        return pnum;
    }
    public void payto100k()
    {
        
        try 
        {   
            String res;
            res = Getpayto100k();
            conn = ClientBean.getConne();
            String sql = "select * from client where pnumber= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, res);
            rs = pst.executeQuery();
            if(rs.next())
            {
                setTpnumber(rs.getString("pnumber"));
                setTfname(rs.getString("fname"));
                setTsname(rs.getString("sname"));
                setTemail(rs.getString("email"));
                setTbank(rs.getString("bank"));
                setTacc(rs.getString("acc"));
                setTaccname(rs.getString("accname"));
                setTgender(rs.getString("gender"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ClientBean.closeAll(conn, pst, rs);
        }
   
    }
    
    public String ph() throws SQLException
    {
        String pack = session.getPac();
        String res = Getpayto5k();
        conn = ClientBean.getConne();
        String sql;
        String stat = status(counter);
       // `transId`, `pnumber`, `pay_to`, `counter`, `no_of_donation`, `status`
        switch (pack) {
                    case "5000":
                        sql ="insert into 5k (pnumber, pay_to, counter, no_of_donation, status) values (?,?,?,?,?)";
                        pst= conn.prepareStatement(sql);
                        pst.setString(1, this.getPnumber());
                        pst.setString(2, res);
                        pst.setInt(3, this.getCounter());
                        pst.setInt(4, donate);
                        pst.setString(5, stat);
                        
                        pst.execute();
                        payto5k();
                        break;
                    case "10000":
                        sql ="insert into 10k (pnumber, pay_to, counter, no_of_donation, status) values (?,?,?,?,?)";
                        pst= conn.prepareStatement(sql);
                        pst.setString(1, this.getPnumber());
                        pst.setString(2, res);
                        pst.setInt(3, this.getCounter());
                        pst.setInt(4, donate);
                        pst.setString(5, stat);
                        
                        pst.execute();
                        payto10k();
                        break;
                    case "20000":
                        sql ="insert into 20k (pnumber, pay_to, counter, no_of_donation, status) values (?,?,?,?,?)";
                        pst= conn.prepareStatement(sql);
                        pst.setString(1, this.getPnumber());
                        pst.setString(2, res);
                        pst.setInt(3, this.getCounter());
                        pst.setInt(4, donate);
                        pst.setString(5, stat);
                        
                        pst.execute();
                        payto20k();
                        break;
                    case "50000":
                        sql ="insert into 50k (pnumber, pay_to, counter, no_of_donation, status) values (?,?,?,?,?)";
                        pst= conn.prepareStatement(sql);
                        pst.setString(1, this.getPnumber());
                        pst.setString(2, res);
                        pst.setInt(3, this.getCounter());
                        pst.setInt(4, donate);
                        pst.setString(5, stat);
                        
                        pst.execute();
                        payto50k();
                        break;
                    case "100000":
                        sql ="insert into 100k (pnumber, pay_to, counter, no_of_donation, status) values (?,?,?,?,?)";
                        pst= conn.prepareStatement(sql);
                        pst.setString(1, this.getPnumber());
                        pst.setString(2, res);
                        pst.setInt(3, this.getCounter());
                        pst.setInt(4, donate);
                        pst.setString(5, stat);
                        
                        pst.execute();
                        payto100k();
                        break;
                    default:
                       
                        
                }
        return "transaction.xhtml?faces-redirect=true";
    }
    
    public void confirm5k()
    {
        conn = ClientBean.getConne();
        String sql = "update 5k set counter=counter + 1 where transId = ? ";
    }
    
    
    public void showTime()
    {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        time = dateFormat.format(now);
        
    }
    
//    public void clock()
//    {
//        Thread clock = new Thread()
//        {
//            public void run()
//            {
//               try
//               {
//                   while(true)
//                   {
//                    Calendar cal = new GregorianCalendar();
//                    int day = cal.get(Calendar.DAY_OF_MONTH);
//                    int month = cal.get(Calendar.MONTH);
//                    int year = cal.get(Calendar.YEAR);
//         
//                    int second = cal.get(Calendar.SECOND);
//                    int minute = cal.get(Calendar.MINUTE);
//                    int hour = cal.get(Calendar.HOUR);
//                    String a ="AM";
//                    String p = "PM";
//                    
//                    setTime("   Time: " + hour+":"+minute+":"+second+ "   Date: "+ day+"/"+(month+1)+"/"+ year);
//                    
//                    sleep(1000);
//                   }                 
//               }
//               catch(InterruptedException e)
//                       {
//                           e.printStackTrace();
//                       }
//            }
//        };
//        clock.start();
//    }
    
    public void updatPac() throws SQLException
    {
  
        String nam = session.getPnumber();
        conn = ClientBean.getConne();
        String sql;
        sql="update client set package = ? where pnumber = ? ";
        
        try 
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.getPac());
            pst.setString(2, nam);
            String pacman = this.getPac();
            System.out.println("" + this.getPac() + "" + nam);
            int exec = pst.executeUpdate();
            if(exec > 0)
            {
                System.out.println("updated package");
                session.setPac(pacman);
            }
            else
            {
                System.out.println("nope");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeAll(conn,pst,rs);
        }
        
    }
    
    public void updatAll() throws SQLException
    {
  
        String nam = session.getPnumber();
        conn = ClientBean.getConne();
        String sql;
        sql="update client set fname = ?, sname = ?, email = ?, bank = ?, acc = ?, accname = ?, password = ?, gender = ? where pnumber = ? ";
        
        try 
        {
            pst = conn.prepareStatement(sql);
          
            pst.setString(1, this.getFname());
            pst.setString(2, this.getSname());
            pst.setString(3, this.getEmail());
            pst.setString(4, this.getBank());
            pst.setString(5, this.getAcc());
            pst.setString(6, this.getAccname());
            pst.setString(7, this.getPassword());
            pst.setString(8, this.getGender());
            pst.setString(9, nam);
            
            String fna = this.getFname();
            String sna = this.getSname();
            String emai = this.getEmail();
            String bk = this.getBank();
            String acco = this.getAcc();
            String acname = this.getAccname();
            String pass = this.getPassword();
            String gen = this.getGender();
            
            int exec = pst.executeUpdate();
            if(exec > 0)
            {
                System.out.println("updated package");
                session.setFname(fna);
                session.setSname(sna);
                session.setEmail(emai);
                session.setBank(bk);
                session.setAcc(acco);
                session.setAccname(acname);
                session.setPassword(pass);
                session.setGender(gen);
                hide();
            }
            else
            {
                System.out.println("nope");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeAll(conn,pst,rs);
        }
        
    }
    
//    public int getSessionCount() {
//        System.out.println("session count getter invoked");
//        return SessionCount.getTotalActiveSession();
//    }
    
    
    public void show()
    {
        showPopup = true;
    }
    public void hide()
    {
        showPopup = false;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    
    
    
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public user getSession() {
        return session;
    }

    public void setSession(user session) {
        this.session = session;
    }
    
    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getDonate() {
        return donate;
    }

    public void setDonate(int donate) {
        this.donate = donate;
    }
    
    
    
//    public String incriz5k()
//    {
//        int a = (int) (Math.random()*10);
//        int b = (int) (Math.random() * 10);
//        int c = (int) (Math.random()*10);
//        int d = (int) (Math.random()*10);
//        
//        String aS = String.valueOf(a);
//        String bS = String.valueOf(b);
//        String cS = String.valueOf(c);
//        String dS = String.valueOf(d);
//        
//        String ans = "5"+""+aS+""+bS+""+cS+""+dS;
//        return ans;
//    }
//   public String incriz10k()
//    {
//        int a = (int) (Math.random()*10);
//        int b = (int) (Math.random() * 10);
//        int c = (int) (Math.random()*10);
//        int d = (int) (Math.random()*10);
//        
//        String aS = String.valueOf(a);
//        String bS = String.valueOf(b);
//        String cS = String.valueOf(c);
//        String dS = String.valueOf(d);
//        
//        String ans = "10"+""+aS+""+bS+""+cS+""+dS;
//        return ans;
//    }
//   public String incriz20k()
//    {
//        int a = (int) (Math.random()*10);
//        int b = (int) (Math.random() * 10);
//        int c = (int) (Math.random()*10);
//        int d = (int) (Math.random()*10);
//        
//        String aS = String.valueOf(a);
//        String bS = String.valueOf(b);
//        String cS = String.valueOf(c);
//        String dS = String.valueOf(d);
//        
//        String ans = "20"+""+aS+""+bS+""+cS+""+dS;
//        return ans;
//    }
//   public String incriz50k()
//    {
//        int a = (int) (Math.random()*10);
//        int b = (int) (Math.random() * 10);
//        int c = (int) (Math.random()*10);
//        int d = (int) (Math.random()*10);
//        
//        String aS = String.valueOf(a);
//        String bS = String.valueOf(b);
//        String cS = String.valueOf(c);
//        String dS = String.valueOf(d);
//        
//        String ans = "50"+""+aS+""+bS+""+cS+""+dS;
//        return ans;
//    }
//   public String incriz100k()
//    {
//        int a = (int) (Math.random()*10);
//        int b = (int) (Math.random() * 10);
//        int c = (int) (Math.random()*10);
//        int d = (int) (Math.random()*10);
//        
//        String aS = String.valueOf(a);
//        String bS = String.valueOf(b);
//        String cS = String.valueOf(c);
//        String dS = String.valueOf(d);
//        
//        String ans = "100"+""+aS+""+bS+""+cS+""+dS;
//        return ans;
//    }

    public String getTpnumber() {
        return tpnumber;
    }

    public void setTpnumber(String tpnumber) {
        this.tpnumber = tpnumber;
    }

    public String getTfname() {
        return tfname;
    }

    public void setTfname(String tfname) {
        this.tfname = tfname;
    }

    public String getTsname() {
        return tsname;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    public String getTbank() {
        return tbank;
    }

    public void setTbank(String tbank) {
        this.tbank = tbank;
    }

    public String getTacc() {
        return tacc;
    }

    public void setTacc(String tacc) {
        this.tacc = tacc;
    }

    public String getTaccname() {
        return taccname;
    }

    public void setTaccname(String taccname) {
        this.taccname = taccname;
    }

    public String getTgender() {
        return tgender;
    }

    public void setTgender(String tgender) {
        this.tgender = tgender;
    }
    
    
}

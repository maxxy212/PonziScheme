/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelBean.Client;

/**
 *
 * @author Maxwell
 */
@ManagedBean
@SessionScoped
public class ClientBean implements Serializable {
    
    private static final long serialVersionUID = 1094801825228386363L;
    private String acc,accname,pac;
    private String fname,sname,email,bank,password,gender,pnumber;
    private static Connection conn = null;
    private PreparedStatement pst;
    private ResultSet rs = null;
    private String sql="";
    private UIComponent component;
    List<ClientBean> users;
    private boolean showPopup;
    
    public ClientBean()
    {
        
    }

    public ClientBean(String acc, String accname, String pac, String fname, String sname, String email, String bank, String password, String gender, String pnumber) {
        this.acc = acc;
        this.accname = accname;
        this.pac = pac;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        this.bank = bank;
        this.password = password;
        this.gender = gender;
        this.pnumber = pnumber;
    }
    
    
    
    public static Connection getConne()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elite","root","");
           
        } catch (ClassNotFoundException | SQLException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static void closeAll(Connection conn, PreparedStatement pst, ResultSet rs)
    {
        if(conn!=null)
        {
            try 
            {
                conn.close();
            } catch (SQLException ex) 
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(pst!=null)
        {
            try 
            {
                pst.close();
            } catch (SQLException ex) 
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(rs!=null)
        {
            try 
            {
                rs.close();
            } catch (SQLException ex) 
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    
    
    public ArrayList<ClientBean> getAll()
    {
        ArrayList<ClientBean> arr = new ArrayList<>();
        sql ="select pnumber, fname, sname, email, bank, acc, accname, password, gender, package from client";
        getConne();
        try 
        {  
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next())
            {
                ClientBean cb = new ClientBean();
                cb.setPnumber(rs.getString("pnumber"));
                cb.setFname(rs.getString("fname"));
                cb.setSname(rs.getString("sname"));
                cb.setEmail(rs.getString("email"));
                cb.setBank(rs.getString("bank"));
                cb.setAcc(rs.getString("acc"));
                cb.setAccname(rs.getString("accname"));
                cb.setPassword(rs.getString("password"));
                cb.setGender(rs.getString("gender"));
                cb.setPac(rs.getString("package"));
                
                arr.add(cb);
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeAll(conn,pst,rs);
        }
        return arr;
    }
    
    
    public void add()
    {
        getConne();
        sql = "Insert into client (pnumber, fname, sname, email, bank, acc, accname, password, gender, package) values (?,?,?,?,?,?,?,?,?,?)";
        try 
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.getPnumber());
            pst.setString(2, this.getFname());
            pst.setString(3, this.getSname());
            pst.setString(4, this.getEmail());
            pst.setString(5, this.getBank());
            pst.setString(6, this.getAcc());
            pst.setString(7, this.getAccname());
            pst.setString(8, this.getPassword());
            pst.setString(9, this.getGender());
            pst.setString(10, this.getPac());
            
            int execUpdate = pst.executeUpdate();
            if(execUpdate > 0)
            {
                System.out.println("Added");
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
    
    public void update()
    {
        getConne();
        sql = "update employee set pnumber = ?, fname = ?, sname = ?, email = ?, bank = ?, acc = ?, accname = ?, password = ?, gender = ?, package = ? where pnumber = ?";
        
        try 
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.getPnumber());
            pst.setString(2, this.getFname());
            pst.setString(3, this.getSname());
            pst.setString(4, this.getEmail());
            pst.setString(5, this.getBank());
            pst.setString(6, this.getAcc());
            pst.setString(7, this.getAccname());
            pst.setString(8, this.getPassword());
            pst.setString(9, this.getGender());
            pst.setString(10, this.getPac());
            pst.setString(11, this.getPnumber());
            
            int execUpdate = pst.executeUpdate();
            if(execUpdate > 0)
            {
                System.out.println("updated");
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
    
    

    public void Edit()
    {
        getConne();
        String pid;
        ArrayList<ClientBean> cb = getAll();
        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String,String> mapParam = fc.getExternalContext().getInitParameterMap();
        HttpServletRequest rq = (HttpServletRequest) fc.getExternalContext().getRequest();
        pid = rq.getParameter("pnumber");
        
        System.out.println("Edited" + pid);
        for(ClientBean cbean : cb)
        {
            if(cbean.getPnumber()== pid)
            {
                this.setPnumber(cbean.getPnumber());
                this.setFname(cbean.getFname());
                this.setSname(cbean.getSname());
                this.setEmail(cbean.getEmail());
                this.setBank(cbean.getBank());
                this.setAcc(cbean.getAcc());
                this.setAccname(cbean.getAccname());
                this.setPassword(cbean.getPassword());
                this.setGender(cbean.getGender());
                this.setPac(cbean.getPac());
            }
        }
    }
    
    public void delete()
    {
         getConne();
        String pid;
        sql = "delete from client where pnumber=?";;
        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String,String> mapParam = fc.getExternalContext().getInitParameterMap();
        HttpServletRequest rq = (HttpServletRequest) fc.getExternalContext().getRequest();
        pid = rq.getParameter("pnumber");
        
         try 
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.getPnumber());
            
            int execUpdate = pst.executeUpdate();
            if(execUpdate > 0)
            {
                System.out.println("Deleted");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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

    
    
    
    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
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

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public List<ClientBean> getUsers() {
        return users;
    }

    public void setUsers(List<ClientBean> users) {
        this.users = users;
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
    
    
    //    public String userdata(String num)
//    {
//        
//        getConne();
//        sql = "SELECT * FROM client where pnumber like ('" + num +"')";
//        try 
//        {
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            
//            if(rs.next())
//            {
//                dbnum = rs.getString("pnumber");
//                dbpass = rs.getString("password");
//                fname = rs.getString("fname");
//                return fname;
//            }
//        } 
//        catch (SQLException ex) 
//        {
//            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    public String validate()
//    {
//        userdata(pnumber);
//        
//        if(pnumber.equalsIgnoreCase(dbnum) & password.equals(dbpass))
//        {
//            HttpSession hs = Client.getsession();
//            hs.setAttribute("pnumber", pnumber);
//            System.out.println("name is: " + fname);
//            
//            setFname(fname);
//          //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", pnumber);
//            return "login.xhtml?faces-redirect=true";
//        }
//        else
//        {
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(component.getClientId(), new FacesMessage("Phone number or password invalid *"));
//            return "";
//        }
//    }

   
    
}

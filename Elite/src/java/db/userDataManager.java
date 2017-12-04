/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelBean.user;

/**
 *
 * @author Maxwell
 */
public class userDataManager {
    
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    
    
    public user findUser(String pnumber, String password) throws SQLException{
    
        
        String n=null,l=null,pn=null,pa=null,e=null,b=null,a=null,g=null,an=null,p=null;
        
            con = ClientBean.getConne();
            String sql = "SELECT * FROM client where pnumber=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, pnumber);
            ps.setString(2, password);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                pn = rs.getString("pnumber");
                pa = rs.getString("password");
                n = rs.getString("fname");
                l = rs.getString("sname");
                e = rs.getString("email");
                b = rs.getString("bank");
                a = rs.getString("acc");
                an = rs.getString("accname");
                g = rs.getString("gender");
                p = rs.getString("package");
               
                System.out.println(""+n+""+l);
               
            }
         return new user(pn, pa, n, l, e, b, a, an, g, p);
    }
    
    
    
}

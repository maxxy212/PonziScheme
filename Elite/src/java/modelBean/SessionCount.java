package modelBean;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maxwell
 */
@WebListener
public class SessionCount implements HttpSessionListener {

   private static int totalActiveSessions;

  public static int getTotalActiveSession(){
	return totalActiveSessions;
  }

  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
	totalActiveSessions++;
	System.out.println("sessionCreated - add one session into counter" +arg0.getSession().getId());
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
	totalActiveSessions--;
	System.out.println("sessionDestroyed - deduct one session from counter" +arg0.getSession().getId());
  }
    
    
}

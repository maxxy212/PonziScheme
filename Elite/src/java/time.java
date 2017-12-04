
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maxwell
 */
public class time {
    
    public void printDifference( Date endDate){

        
              Date now = new Date();
             Date startDate = now;
		//milliseconds
		long different = endDate.getTime() - startDate.getTime();

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : "+ endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf(
		    "%d days, %d hours, %d minutes, %d seconds%n",
		    elapsedDays,
		    elapsedHours, elapsedMinutes, elapsedSeconds);

                int days = (int) (different / (1000 * 60 * 60 * 24));
                int seconds = (int) ((different % (1000 * 60)) / 1000);
                int minutes = (int) ((different % (1000 * 60 * 60)) / (1000 * 60));
                int hours   = (int) ((different / (1000*60*60)) % 24);
                System.out.println(days+" "+seconds+" "+minutes+" "+hours);
	}

    public static void main(String[] args) {
        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//              Date now = new Date();
//              System.out.println(dateFormat.format(now));
              
                //System.out.println(dateFormat.format(dt));
         Date dt = new Date();
                Calendar c = Calendar.getInstance(); 
                c.setTime(dt); 
                c.add(Calendar.DATE, 1);
                dt = c.getTime();
                        time obj = new time();
//	  SimpleDateFormat simpleDateFormat =
//                new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                        
                        Date date2 = dt;
                        obj.printDifference(date2);
          
        Thread clock = new Thread()
        {
            public void run()
            {
               try
               {
                   for(;;)
                   {

                      

//        System.out.println(dateFormat.format(currentDatePlusOne));

                    sleep(1000);
                   }                 
               }
               catch(InterruptedException e)
                       {
                           e.printStackTrace();
                       }
            }
        };
        clock.start();
    }
}

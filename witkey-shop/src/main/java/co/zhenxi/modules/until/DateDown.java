package co.zhenxi.modules.until;

import java.sql.Timestamp;

public class DateDown {


     public static String  CountDownDate(Timestamp startDate, Timestamp endDate) {
         long startTime = startDate.getTime();//获取毫秒数
         long endTime = endDate.getTime();	 //获取毫秒数
         int days=(int) ((startTime - endTime)/(1000*60*60*24));
         int hours=(int) ((startTime - endTime)/((1000*60*60)));
         int minutes=(int) (((startTime - endTime)/1000-hours*(60*60))/60);
         int second=(int) ((startTime - endTime)/1000-hours*(60*60)-minutes*60);
         return  ""+days+"天"+hours+"小时"+minutes+"分"+second+"秒";
     }

}

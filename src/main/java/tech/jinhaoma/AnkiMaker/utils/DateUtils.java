package tech.jinhaoma.AnkiMaker.utils;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Log4j2
public class DateUtils {

    /**
     * 获得指定日期周围的某一天
     *
     * @param aDay
     * @param delta
     * @return
     */
    public static Date getNextOrBeforeDay(Date aDay,int delta) {
        SimpleDateFormat dateFormatByDay = new SimpleDateFormat("yyyy-MM-dd");
        String specifiedDay = dateFormatByDay.format(aDay);
        Calendar calendar = Calendar.getInstance();

        Date date = null;
        try {
            date = dateFormatByDay.parse(specifiedDay);
        } catch (ParseException e) {
            log.error(date+"==>>"+e.toString());
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + delta);

        String newDay = dateFormatByDay.format(calendar.getTime());

        Date result = null;
        try {
            result = dateFormatByDay.parse(newDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        week = week < 0 ? 0 : week;
        return week;
    }

    public static String getFormatByDay(Date date){
        SimpleDateFormat dateFormatByDay = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormatByDay.format(date);
    }
    public static Date getFormatByDay(String date) throws ParseException {
        SimpleDateFormat dateFormatByDay = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormatByDay.parse(date);
    }
    public static String getFormatBySecond(Date date){
        SimpleDateFormat dateFormatBySecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormatBySecond.format(date);
    }
    public static Date getFormatBySecond(String dateString)  {
        SimpleDateFormat dateFormatBySecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormatBySecond.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("----------------errorString:"+dateString);
        } catch (NumberFormatException e1){
            e1.printStackTrace();
            System.out.println("----------------errorString:"+dateString);
        }
        return date;
    }
    public static Date formatNewCoderGmt(String gmt){

        Date result = null;
        gmt = gmt.substring(gmt.indexOf(" ")+1);
        if(gmt.indexOf("今天") == -1){
            result = DateUtils.getFormatBySecond(gmt);
        } else {
            gmt = gmt.substring(gmt.indexOf(" ")+1).trim();
            String[] time = gmt.split("[:]");
            result = new Date();
            result.setHours(Integer.valueOf(time[0]));
            result.setMinutes(Integer.valueOf(time[1]));
            result.setSeconds(Integer.valueOf(time[2]));
        }
        return result;
    }
}


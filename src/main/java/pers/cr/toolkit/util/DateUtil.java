package pers.cr.toolkit.util;

import java.util.Date;

/**
 * @author Cr
 * @date 2020-02-25
 * */
public class DateUtil {

    public static long DAY_TIMESTAMP = 1000 * 60 * 60 * 24;
    public static long WEEK_TIMESTAMP = DAY_TIMESTAMP * 7;

    /**
     *  <p>功能: 根据两个日期的时间差获取对应的相差的天数</p>
     *
     *  <p>场景1: 2020-02-25 15:07:00  2020-02-26 12:20:50  return 0 </p>
     *  <p>场景2: 2020-02-25 15:07:00  2020-02-26 16:20:50  return 1 </p>
     *  <p>场景3: 2020-02-25 15:07:00  2020-02-23 12:20:50  return 2 </p>
     * */
    public static long getDaysDiff(Date d1, Date d2){
        return getTimeStampByDateDiff(d1, d2) / DAY_TIMESTAMP;
    }

    /**
     *  <p>功能: 根据两个日期的时间差获取对应的相差的日期数</p>
     *
     *  <p>场景1: 2020-02-26 10:53:00  2020-02-19 12:20:50  return 0 </p>
     *  <p>场景2: 2020-02-26 15:53:00  2020-02-19 10:20:50  return 1 </p>
     *  <p>场景3: 2020-02-26 15:53:00  2020-03-05 10:20:50  return 1 </p>
     * */
    public static long getWeekDiff(Date d1, Date d2){
        return getTimeStampByDateDiff(d1, d2) / WEEK_TIMESTAMP;
    }

    public static long getTimeStampByDateDiff(Date d1, Date d2){
        return compare(d1, d2) ? (d1.getTime() - d2.getTime()) : (d2.getTime() - d1.getTime());
    }

   public static boolean compare(Date d1, Date d2){
       return d1.getTime() > d2.getTime();
   }

}

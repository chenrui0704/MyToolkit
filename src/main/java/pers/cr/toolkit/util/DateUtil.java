package pers.cr.toolkit.util;

import java.util.Date;

/**
 * @author Cr
 * @date 2020-02-25
 * */
public class DateUtil {

    /**
     *  功能: 根据两个日期的时间差获取对应的相差的天数
     *
     *  场景1: 2020-02-25 15:07:00  2020-02-26 12:20:50  return 0
     *  场景2: 2020-02-25 15:07:00  2020-02-26 16:20:50  return 1
     *  场景3: 2020-02-25 15:07:00  2020-02-23 12:20:50  return 2 时间戳
     * */
    public static long getDaysTimeDifferenceByTimestamp(Date d1, Date d2){
        boolean flag = compare(d1, d2);
        long timeDiff = flag ? (d1.getTime() - d2.getTime()) : (d2.getTime() - d1.getTime());
        return timeDiff / (1000 * 60 * 60 * 24);
    }

   public static boolean compare(Date d1, Date d2){
       return d1.getTime() > d2.getTime();
   }

}

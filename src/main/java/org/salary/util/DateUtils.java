package org.salary.util;

import org.salary.po.PayCheck;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月07日 14:00:56
 * @Description:
 * @ClassName: DateUtils
 */
public abstract class DateUtils {

    /**
     * @param [date1, date2]
     * @return boolean
     * @Title: isDateBetweenPreWeekOfTheLastDate
     * @Description:比较时间是否在后者时间的一周范围之内
     * @author chenjianrong-lhq 2019-04-07 14:18
     */
    public static boolean isDateBetweenPreWeekOfTheLastDate(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();

        if (date2 != null) {
            calendar.setTime(date2);
            calendar.add(Calendar.DAY_OF_YEAR, -5);
        }

        if (date1.getTime() >= calendar.getTime().getTime() && date1.getTime() <= date2.getTime()) {
            return true;
        }
        return false;
    }

    public static boolean isInPayPeriod(Date theDate, PayCheck pc) {
        Date payPeriodEndDate = pc.getPayPeriodEndDate();

        Date payPeriodstartDate = pc.getPayPeriodStartDate();

        return (theDate.getTime() > payPeriodstartDate.getTime() && theDate.getTime() <= payPeriodEndDate.getTime());
    }
}

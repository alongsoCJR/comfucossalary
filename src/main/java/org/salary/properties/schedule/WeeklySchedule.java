package org.salary.properties.schedule;

import org.salary.base.PaymentSchedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 10:51:32
 * @Description:
 * @ClassName: WeeklySchedule
 */
public class WeeklySchedule implements PaymentSchedule {

    public boolean isPaydate(Date payDate) {
        return isFriday(payDate);
    }

    private boolean isFriday(Date payDate) {
        Calendar calendar = Calendar.getInstance();

        if (payDate != null) {
            calendar.setTime(payDate);
        }
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    public Date getPayPeriodStartDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();

        if (payDate != null) {
            calendar.setTime(payDate);
            calendar.add(Calendar.DAY_OF_YEAR, -7);
        }
        return calendar.getTime();
    }


}

package org.salary.properties.schedule;

import org.salary.base.PaymentSchedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:27:22
 * @Description:
 * @ClassName: BiweeklySchedule
 */
public class BiweeklySchedule implements PaymentSchedule {

    public boolean isPaydate(Date payDate) {
        return isFirday(payDate);
    }

    private boolean isFirday(Date payDate) {

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
            calendar.add(Calendar.DAY_OF_YEAR, -14);
        }
        return calendar.getTime();
    }

}

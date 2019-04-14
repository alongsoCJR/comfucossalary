package org.salary.properties.schedule;

import org.salary.base.PaymentSchedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:58:20
 * @Description:
 * @ClassName: MonthlySchedule
 */
public class MonthlySchedule implements PaymentSchedule {

    public boolean isPaydate(Date payDate) {
        return isLastDayOfMonth(payDate);
    }

    private boolean isLastDayOfMonth(Date payDate) {

        Calendar calendar = Calendar.getInstance();

        if (payDate != null) {
            calendar.setTime(payDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        int month = getInteger(payDate, Calendar.MONTH);
        int calMonth = calendar.get(Calendar.MONTH);
        return month != calMonth;
    }

    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }


    public Date getPayPeriodStartDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();

        if (payDate != null) {
            calendar.setTime(payDate);
            calendar.add(Calendar.MONTH, -1);
        }
        return calendar.getTime();
    }

}

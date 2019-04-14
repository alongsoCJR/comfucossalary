package org.salary.base;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:54:02
 * @Description: 支付时间表
 * @ClassName: PaymentSchedule
 */
public interface PaymentSchedule {

    boolean isPaydate(Date payDate);

    //这个抽象亮瞎双眼
    Date getPayPeriodStartDate(Date payDate);
}

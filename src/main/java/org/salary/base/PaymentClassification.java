package org.salary.base;

import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:54:40
 * @Description: 雇员佣金支付分类，PaymentClassification起初也是设计为接口的，后来发现isInPayPeriod（）可以抽取上来，实现代码的复用。
 * @ClassName: PaymentClassification
 */
public abstract class PaymentClassification {

    //计算应收工资
    public abstract double calculatePay(PayCheck pc);

//    protected boolean isInPayPeriod(Date theDate, PayCheck pc) {
//        Date payPeriodEndDate = pc.getPayPeriodEndDate();
//
//        Date payPeriodstartDate = pc.getPayPeriodStartDate();
//
//        return (theDate.getTime() > payPeriodstartDate.getTime() && theDate.getTime() <= payPeriodEndDate.getTime());
//    }
}

package org.salary.base;

import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:56:18
 * @Description: 工资支付方式
 * @ClassName: PaymentMethod
 */
public interface PaymentMethod {

    //支付方式明细（账单）
    void pay(PayCheck pc);
}

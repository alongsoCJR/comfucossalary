package org.salary.service.base;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.po.Employee;

/**
 * @author chenjianrong-lhq 2019年04月06日 12:05:16
 * @Description:
 * @ClassName: ChangeClassificationTransaction
 */
public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction() {
    }

    public ChangeClassificationTransaction(Integer itsEmpid) {
        super(itsEmpid);
    }

    //模板方法
    protected void change(Employee employee) {
        employee.setPaymentSchedule(getPaymentSchedule());
        employee.setPaymentClassification(getPaymentClassification());
    }

    //策略模式，放的一个钩子
    protected abstract PaymentClassification getPaymentClassification();

    //策略模式，放的一个钩子
    protected abstract PaymentSchedule getPaymentSchedule();

}

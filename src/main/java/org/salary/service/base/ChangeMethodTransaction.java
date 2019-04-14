package org.salary.service.base;

import org.salary.base.PaymentMethod;
import org.salary.po.Employee;

/**
 * @author chenjianrong-lhq 2019年04月06日 12:05:16
 * @Description:
 * @ClassName: ChangeClassificationTransaction
 */
public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(Integer itsEmpid) {
        super(itsEmpid);
    }

    //模板方法
    protected void change(Employee employee) {
        employee.setPaymentMethod(getPaymentMethod());
    }

    //策略模式，放的一个钩子
    protected abstract PaymentMethod getPaymentMethod();

}

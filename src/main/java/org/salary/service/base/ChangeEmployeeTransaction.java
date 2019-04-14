package org.salary.service.base;

import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 11:21:42
 * @Description:
 * @ClassName: ChangeEmployeeTransaction
 */
public abstract class ChangeEmployeeTransaction implements Transaction {

    private Integer itsEmpid;

    public ChangeEmployeeTransaction() {
    }

    public ChangeEmployeeTransaction(Integer itsEmpid) {
        this.itsEmpid = itsEmpid;
    }

    //模板方法
    public void execute() {
        Employee employee = GpayrollDatabase.getEmployee(itsEmpid);
        if (employee != null) {
            change(employee);
        }

    }

    //策略模式，放的一个钩子
    protected abstract void change(Employee employee);
}

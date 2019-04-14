package org.salary.service.base;

import org.salary.base.*;
import org.salary.po.Employee;
import org.salary.properties.method.HoldMethod;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:49:00
 * @Description:
 * @ClassName: AddEmployeeTransaction
 */
public abstract class AddEmployeeTransaction implements Transaction {

    private Integer itsEmpid;

    private String itsName;

    private String itsAddress;

    // 策略模式来获取具体的实现
    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();

    public AddEmployeeTransaction(Integer itsEmpid, String itsName, String itsAddress) {
        this.itsEmpid = itsEmpid;
        this.itsName = itsName;
        this.itsAddress = itsAddress;
    }

    // 模板方法模式来增加雇员
    public void execute() {
        PaymentClassification pc = this.getClassification();
        PaymentSchedule ps = this.getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(itsEmpid, itsName, itsAddress);
        e.setPaymentClassification(pc);
        e.setPaymentSchedule(ps);
        e.setPaymentMethod(pm);
        GpayrollDatabase.addEmployee(itsEmpid, e);
    }

}

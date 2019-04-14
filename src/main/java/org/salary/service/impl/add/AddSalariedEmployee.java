package org.salary.service.impl.add;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.properties.classfication.SalariedClassfication;
import org.salary.properties.schedule.MonthlySchedule;
import org.salary.service.base.AddEmployeeTransaction;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:47:37
 * @Description:
 * @ClassName: AddSalariedEmployee
 */
public class AddSalariedEmployee extends AddEmployeeTransaction {

    private Double itsSalary;

    public AddSalariedEmployee(Integer itsEmpid, String itsName, String itsAddress, Double itsSalary) {
        super(itsEmpid,itsName,itsAddress);
        this.itsSalary=itsSalary;
    }

    public PaymentClassification getClassification() {
        return new SalariedClassfication(itsSalary);
    }

    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

}

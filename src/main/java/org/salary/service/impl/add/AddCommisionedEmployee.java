package org.salary.service.impl.add;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.properties.classfication.CommisionedClassfication;
import org.salary.properties.schedule.BiweeklySchedule;
import org.salary.service.base.AddEmployeeTransaction;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:20:44
 * @Description:
 * @ClassName: AddCommisionedEmployee
 */
public class AddCommisionedEmployee extends AddEmployeeTransaction {

    private Double salary;

    private Double commisionRate;

    public AddCommisionedEmployee(Integer itsEmpid, String itsName, String itsAddress,Double salary,Double commisionRate) {
        super(itsEmpid,itsName,itsAddress);
        this.salary=salary;
        this.commisionRate=commisionRate;
    }

    public PaymentClassification getClassification() {
        return new CommisionedClassfication(salary,commisionRate);
    }

    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}

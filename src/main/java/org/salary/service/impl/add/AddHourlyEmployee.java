package org.salary.service.impl.add;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.properties.classfication.HourlyClassfication;
import org.salary.properties.schedule.WeeklySchedule;
import org.salary.service.base.AddEmployeeTransaction;

/**
 * @author chenjianrong-lhq 2019年04月05日 10:49:39
 * @Description:
 * @ClassName: AddHourlyEmployee
 */
public class AddHourlyEmployee extends AddEmployeeTransaction {
    private Double HourlyRate;

    public AddHourlyEmployee(Integer itsEmpid, String itsName, String itsAddress, Double HourlyRate) {
        super(itsEmpid,itsName,itsAddress);
        this.HourlyRate=HourlyRate;
    }

    public PaymentClassification getClassification() {
        return new HourlyClassfication(HourlyRate);
    }

    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}

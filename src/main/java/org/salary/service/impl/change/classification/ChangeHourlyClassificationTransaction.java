package org.salary.service.impl.change.classification;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.properties.classfication.HourlyClassfication;
import org.salary.properties.schedule.WeeklySchedule;
import org.salary.service.base.ChangeClassificationTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 12:03:56
 * @Description:
 * @ClassName: ChangeHourlyClassificationTransaction
 */
public class ChangeHourlyClassificationTransaction extends ChangeClassificationTransaction {

    private Double hourlyRate;

    public ChangeHourlyClassificationTransaction(Integer empId, Double hourlyRate) {
        super(empId);
        this.hourlyRate=hourlyRate;
    }

    protected PaymentClassification getPaymentClassification() {
        return new HourlyClassfication(hourlyRate);
    }

    protected PaymentSchedule getPaymentSchedule() {
        return new WeeklySchedule();
    }
}

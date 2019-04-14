package org.salary.service.impl.change.classification;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.properties.classfication.CommisionedClassfication;
import org.salary.properties.schedule.BiweeklySchedule;
import org.salary.service.base.ChangeClassificationTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 12:37:50
 * @Description:
 * @ClassName: ChangeCommisionedClassificationTransaction
 */
public class ChangeCommisionedClassificationTransaction extends ChangeClassificationTransaction {

    private Double salary;

    private Double commisionRate;

    public ChangeCommisionedClassificationTransaction(Integer empId, double salary, double commisionRate) {
        super(empId);
        this.salary=salary;
        this.commisionRate=commisionRate;
    }


    protected PaymentClassification getPaymentClassification() {
        return new CommisionedClassfication(salary,commisionRate);
    }

    protected PaymentSchedule getPaymentSchedule() {
        return new BiweeklySchedule();
    }
}

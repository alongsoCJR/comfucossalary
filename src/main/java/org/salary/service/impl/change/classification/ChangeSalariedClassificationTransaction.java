package org.salary.service.impl.change.classification;

import org.salary.base.PaymentClassification;
import org.salary.base.PaymentSchedule;
import org.salary.properties.classfication.SalariedClassfication;
import org.salary.properties.schedule.MonthlySchedule;
import org.salary.service.base.ChangeClassificationTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 12:46:36
 * @Description:
 * @ClassName: ChangeSalariedClassificationTransaction
 */
public class ChangeSalariedClassificationTransaction extends ChangeClassificationTransaction {
    private Double salary;

    public ChangeSalariedClassificationTransaction(Integer empId, double salary) {
        super(empId);
        this.salary = salary;
    }

    protected PaymentClassification getPaymentClassification() {
        return new SalariedClassfication(salary);
    }

    protected PaymentSchedule getPaymentSchedule() {
        return new MonthlySchedule();
    }
}

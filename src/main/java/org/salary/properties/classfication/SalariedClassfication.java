package org.salary.properties.classfication;

import org.salary.base.PaymentClassification;
import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:57:27
 * @Description:
 * @ClassName: SalariedClassfication
 */
public class SalariedClassfication extends PaymentClassification {
    public SalariedClassfication(Double salary) {
        this.salary = salary;
    }

    private Double salary;

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public double calculatePay(PayCheck pc) {
        return salary;
    }
}

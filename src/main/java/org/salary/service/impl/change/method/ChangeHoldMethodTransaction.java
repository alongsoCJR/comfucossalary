package org.salary.service.impl.change.method;

import org.salary.base.PaymentMethod;
import org.salary.properties.method.HoldMethod;
import org.salary.service.base.ChangeMethodTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 13:04:03
 * @Description:
 * @ClassName: ChangeDirectMethodTransaction
 */
public class ChangeHoldMethodTransaction extends ChangeMethodTransaction {

    public ChangeHoldMethodTransaction(Integer empId) {
        super(empId);
    }

    protected PaymentMethod getPaymentMethod() {
        return new HoldMethod();
    }
}

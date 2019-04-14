package org.salary.service.impl.change.method;

import org.salary.base.PaymentMethod;
import org.salary.properties.method.MailMethod;
import org.salary.service.base.ChangeMethodTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 13:04:03
 * @Description:
 * @ClassName: ChangeDirectMethodTransaction
 */
public class ChangeMailMethodTransaction extends ChangeMethodTransaction {

    private String address;

    public ChangeMailMethodTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    protected PaymentMethod getPaymentMethod() {
        return new MailMethod(address);
    }
}

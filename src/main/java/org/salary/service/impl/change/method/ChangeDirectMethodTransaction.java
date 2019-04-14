package org.salary.service.impl.change.method;

import org.salary.base.PaymentMethod;
import org.salary.properties.method.DirectMethod;
import org.salary.service.base.ChangeMethodTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 13:04:03
 * @Description:
 * @ClassName: ChangeDirectMethodTransaction
 */
public class ChangeDirectMethodTransaction extends ChangeMethodTransaction {

    private String bank;

    private String account;

    public ChangeDirectMethodTransaction(Integer empId, String bank, String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    protected PaymentMethod getPaymentMethod() {
        return new DirectMethod(bank,account);
    }
}

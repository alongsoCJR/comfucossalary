package org.salary.properties.method;

import org.salary.base.PaymentMethod;
import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月05日 10:52:14
 * @Description:
 * @ClassName: DirectMethod
 */
public class DirectMethod implements PaymentMethod {

    private String bank;

    private String account;

    public DirectMethod(String bank, String account) {
        this.bank = bank;
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public String getAccount() {
        return account;
    }


    public void pay(PayCheck pc) {

    }
}

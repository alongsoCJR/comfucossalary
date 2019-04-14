package org.salary.properties.method;

import org.salary.base.PaymentMethod;
import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:23:22
 * @Description:
 * @ClassName: MailMethod
 */
public class MailMethod implements PaymentMethod {

    private String address;

    public MailMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void pay(PayCheck pc) {

    }
}

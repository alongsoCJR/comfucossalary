package org.salary.po;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 17:13:57
 * @Description:
 * @ClassName: ServiceCharge
 */
public class ServiceCharge {
    private Date date;

    private Double amount;

    public ServiceCharge(Date date, Double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

}

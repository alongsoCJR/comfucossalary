package org.salary.po;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 15:54:01
 * @Description:
 * @ClassName: SalesReceipt
 */
public class SalesReceipt {

    public SalesReceipt(Date date, Double amount) {
        this.date = date;
        this.amount = amount;
    }

    private Date date;

    private Double amount;

    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }
}

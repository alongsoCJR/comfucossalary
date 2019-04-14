package org.salary.po;

import org.salary.base.PaymentMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjianrong-lhq 2019年04月07日 10:46:50
 * @Description:
 * @ClassName: PayCheck
 */
public class PayCheck {
    /**
     * 算薪月份
     */
    private Date payDate;

    /**
     * 应收工资
     */
    private Double grossPay;

    /**
     * 扣除费用
     */
    private Double deductions;

    /**
     * 实收工资
     */
    private Double netPay;

    /**
     * 上次工资结束时间
     */
    private Date payPeriodEndDate;

    /**
     * 上次工资的开始时间
     */
    private Date payPeriodStartDate;



    private Map<String,PaymentMethod> fieldsMap=new HashMap<String, PaymentMethod>(10);

    public PayCheck(Date payPeriodStartDate, Date payPeriodEndDate) {
        this.payPeriodStartDate=payPeriodStartDate;
        this.payPeriodEndDate=payPeriodEndDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(Double grossPay) {
        this.grossPay = grossPay;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetPay() {
        return netPay;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public PaymentMethod getField(String disposition) {
        return fieldsMap.get(disposition);
    }

    public void setField(String disposition, PaymentMethod paymentMethod) {
        fieldsMap.put(disposition,paymentMethod);
    }

    public Date getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setPayPeriodEndDate(Date payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public Date getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setPayPeriodStartDate(Date payPeriodStartDate) {
        payPeriodStartDate = payPeriodStartDate;
    }
}

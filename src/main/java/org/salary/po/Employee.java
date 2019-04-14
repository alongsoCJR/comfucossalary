package org.salary.po;

import org.salary.base.Affiliation;
import org.salary.base.PaymentClassification;
import org.salary.base.PaymentMethod;
import org.salary.base.PaymentSchedule;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月04日 08:26:58
 * @Description:
 * @ClassName: Employee
 */
public class Employee {

    public Employee() {
    }

    public Employee(Integer empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    private Integer empId;

    private String name;

    private String address;

    private PaymentSchedule paymentSchedule;

    private PaymentClassification paymentClassification;

    private PaymentMethod paymentMethod;

    private Affiliation affiliation;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public boolean isPaydate(Date payDate) {
        return paymentSchedule.isPaydate(payDate);
    }

    //模板方法
    public void payday(PayCheck pc) {
        double grossPay = paymentClassification.calculatePay(pc);
        double deductions = affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        paymentMethod.pay(pc);
    }

    public Date getPayPeriodStartDate(Date payDate) {
        return paymentSchedule.getPayPeriodStartDate(payDate);
    }
}

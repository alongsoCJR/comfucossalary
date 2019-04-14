package org.salary.service;

import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.SalesReceipt;
import org.salary.properties.classfication.CommisionedClassfication;
import org.salary.util.GpayrollDatabase;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 15:53:34
 * @Description:
 * @ClassName: SalesReceiptTransaction
 */
public class SalesReceiptTransaction implements Transaction {

    private Integer empId;

    private Date date;

    private Double amount;

    public SalesReceiptTransaction(Integer empId, Date date, Double amount) {
        this.empId=empId;
        this.date=date;
        this.amount=amount;
    }

    public void execute() {
        Employee e= GpayrollDatabase.getEmployee(empId);
        if(e!=null){
            if(e.getPaymentClassification() instanceof CommisionedClassfication){
                CommisionedClassfication ccf = (CommisionedClassfication)e.getPaymentClassification();
                ccf.addSalesReceipt(date,new SalesReceipt(date,amount));
            }else{
                System.out.println("the employee not instanceof CommisionedClassfication,empId="+empId);
            }
        }else{
            System.out.println("No such employee,empId="+empId);
        }
    }
}

package org.salary.service;

import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.TimeCard;
import org.salary.properties.classfication.HourlyClassfication;
import org.salary.util.GpayrollDatabase;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 12:09:23
 * @Description:
 * @ClassName: TimeCardTransaction
 */
public class TimeCardTransaction implements Transaction {
    private Integer empId;

    private Date cardDate;

    private Double hours;

    public TimeCardTransaction(Integer empId, Date cardDate, Double hours) {
        this.empId = empId;
        this.cardDate = cardDate;
        this.hours = hours;
    }

    public void execute() {
        //判断所选择的雇员不是钟点雇员
        Employee e = GpayrollDatabase.getEmployee(empId);

        if (e != null) {
            if (e.getPaymentClassification() instanceof HourlyClassfication) {
                HourlyClassfication hsf = (HourlyClassfication) e.getPaymentClassification();
                hsf.addTimeCard(cardDate, new TimeCard(cardDate,hours));
            } else {
                System.out.println("该雇员不是钟点雇员");
            }
        } else {
            System.out.println("No Such employee!");
//            throw new BusinessException();
        }
    }
}

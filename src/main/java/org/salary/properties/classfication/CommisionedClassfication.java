package org.salary.properties.classfication;

import org.salary.base.PaymentClassification;
import org.salary.po.PayCheck;
import org.salary.po.SalesReceipt;
import org.salary.util.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:21:48
 * @Description:
 * @ClassName: CommisionedClassfication
 */
public class CommisionedClassfication extends PaymentClassification {
    private Double salary;

    private Double commisionRate;

    private Map<Date, SalesReceipt> salesReceiptMap = new HashMap<Date, SalesReceipt>(10);

    public CommisionedClassfication(Double salary, Double commisionRate) {
        this.salary = salary;
        this.commisionRate = commisionRate;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getCommisionRate() {
        return commisionRate;
    }


    public SalesReceipt getSalesReceipt(Date date) {
        return salesReceiptMap.get(date);
    }

    public void addSalesReceipt(Date date, SalesReceipt salesReceipt) {
        salesReceiptMap.put(date, salesReceipt);
    }


    public double calculatePay(PayCheck pc) {
        Double totalPay = 0.00;
        Set<Map.Entry<Date, SalesReceipt>> entrySet = salesReceiptMap.entrySet();
        for (Map.Entry<Date, SalesReceipt> entry : entrySet) {
            Date date = entry.getKey();
            SalesReceipt salesReceipt = entry.getValue();
//            if (isInPayPeriod(salesReceipt, pc.getPayDate())) {
//            if (isInPayPeriod(salesReceipt.getDate(), pc)) {
            if (DateUtils.isInPayPeriod(salesReceipt.getDate(), pc)) {
                totalPay += calculatePayForSalesReceipt(salesReceipt);
            }
        }
        return salary + totalPay;
    }

    private Double calculatePayForSalesReceipt(SalesReceipt salesReceipt) {
        return salesReceipt.getAmount() * commisionRate;
    }

    //在设计初期，是这样设计的，但是，这种程序怎么能用呢？
//    private boolean isInPayPeriod(SalesReceipt salesReceipt, Date payDate) {
//        Calendar calendar = Calendar.getInstance();
//
//        Date date = salesReceipt.getDate();
//
//        if (payDate != null) {
//            calendar.setTime(payDate);
//            calendar.add(Calendar.DAY_OF_YEAR, -14);
//        }
//
//        if (date.getTime() > calendar.getTime().getTime() && date.getTime() <= payDate.getTime()) {
//            return true;
//        }
//        return false;
//    }
}

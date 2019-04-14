package org.salary.test.add;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.PaymentClassification;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.SalesReceipt;
import org.salary.properties.classfication.CommisionedClassfication;
import org.salary.service.SalesReceiptTransaction;
import org.salary.service.impl.add.AddCommisionedEmployee;
import org.salary.util.GpayrollDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 15:48:50
 * @Description:
 * @ClassName: AddSalesReceiptTransactionTest
 */
public class AddSalesReceiptTransactionTest {
    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddCommisionedEmployee(empId, "Bob", "Home", 1000.00,300.00);

        t.execute();
    }

    @Test
    public void addTimeCardTransactionTest() throws ParseException {
        Integer empId = 3;

        Double amount = 200.0;

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new SalesReceiptTransaction(empId, date, amount);

        t.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        PaymentClassification pc = e.getPaymentClassification();

        Assert.assertTrue(pc instanceof CommisionedClassfication);

        SalesReceipt salesReceipt = ((CommisionedClassfication) pc).getSalesReceipt(date);

        Assert.assertTrue(salesReceipt != null);

        Assert.assertEquals(new Double(200.00), salesReceipt.getAmount());

    }
}

package org.salary.test.pay;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.PayCheck;
import org.salary.properties.method.HoldMethod;
import org.salary.service.SalesReceiptTransaction;
import org.salary.service.base.PaydayTransaction;
import org.salary.service.impl.add.AddCommisionedEmployee;
import org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月07日 10:36:21
 * @Description:
 * @ClassName: PaySingleSalariedEmployeeTest
 */
public class PaySingleCommisionedEmployeeTest {


    @Test
    @Before
    public void addCommisionedEmployeeTest() {
        Integer empId = 3;

        Integer memberId = 100;

        Transaction t = new AddCommisionedEmployee(empId, "Bob", "Home", 1000.00, 20.00);

        t.execute();

        Transaction cmt = new ChangeUnaffiliationTransaction(empId);

        cmt.execute();
    }

    @Test
    public void paySingleCommisionedEmployeeNoSalesReceiptTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 1000.0);

    }

    private void validatePaycheck(PaydayTransaction pt, Integer empId, Date payDate, double pay) {
        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc != null);

        Assert.assertTrue(pc.getPayDate().getTime() == payDate.getTime());

        Assert.assertEquals(new Double(pay), pc.getGrossPay());

        Assert.assertEquals(new Double(0.00), pc.getDeductions());

        Assert.assertEquals(new Double(pay), pc.getNetPay());

        Assert.assertTrue(pc.getField("disposition") instanceof HoldMethod);

    }

    @Test
    public void paySingleCommisionedEmployeeOneSalesReceiptTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new SalesReceiptTransaction(empId, payDate, 5.0);

        t.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 1100.00);
    }

    @Test
    public void paySingleCommisionedEmployeeOnwrongDateTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-03");

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc == null);
    }

    @Test
    public void paySingleCommisionedEmployeeTwoSalesReceiptTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new SalesReceiptTransaction(empId, payDate, 5.0);

        t.execute();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-25");

        Transaction ts = new SalesReceiptTransaction(empId, date, 10.0);

        ts.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 1300.00);
    }


    @Test
    public void paySingleCommisionedEmployeeTwoSalesReceiptSpaningTwoPayPeriodsTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new SalesReceiptTransaction(empId, payDate, 5.0);

        t.execute();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-22");

        Transaction ts = new SalesReceiptTransaction(empId, date, 10.0);

        ts.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 1100.00);
    }
}

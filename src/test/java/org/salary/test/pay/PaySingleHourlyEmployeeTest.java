package org.salary.test.pay;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.PayCheck;
import org.salary.properties.method.HoldMethod;
import org.salary.service.TimeCardTransaction;
import org.salary.service.base.PaydayTransaction;
import org.salary.service.impl.add.AddHourlyEmployee;
import org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月07日 10:36:21
 * @Description:
 * @ClassName: PaySingleSalariedEmployeeTest
 */
public class PaySingleHourlyEmployeeTest {


    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Integer memberId = 100;

        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 18.00);

        t.execute();

        Transaction cmt = new ChangeUnaffiliationTransaction(empId);

        cmt.execute();
    }

    @Test
    public void paySingleHourlyEmployeeNoTimeCardsTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 0.0);

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
    public void paySingleHourlyEmployeeOneTimeCardTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new TimeCardTransaction(empId, payDate, 5.5);

        t.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 99.00);
    }

    @Test
    public void paySingleHourlyEmployeeOverTimeCardTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new TimeCardTransaction(empId, payDate, 9.5);

        t.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 8 * 18 + (9.5 - 8) * 1.5 * 18);
    }

    @Test
    public void paySingleHourlyEmployeeOnwrongDateTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-30");

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc == null);
    }

    @Test
    public void paySingleHourlyEmployeeTwoTimeCardTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new TimeCardTransaction(empId, payDate, 5.00);

        t.execute();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-04");

        Transaction ts = new TimeCardTransaction(empId, date, 9.5);

        ts.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 90.00 + (9.5-8)*1.50*18.0+8*18.0);
    }


    @Test
    public void paySingleHourlyEmployeeWithTimeCardSpaningTwoPayPeriodsTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new TimeCardTransaction(empId, payDate, 5.00);

        t.execute();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-31");

        Transaction ts = new TimeCardTransaction(empId, date, 9.5);

        ts.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 90.00);
    }
}

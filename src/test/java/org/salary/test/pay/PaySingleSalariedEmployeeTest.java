package org.salary.test.pay;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.PayCheck;
import org.salary.properties.method.HoldMethod;
import org.salary.service.base.PaydayTransaction;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月07日 10:36:21
 * @Description:
 * @ClassName: PaySingleSalariedEmployeeTest
 */
public class PaySingleSalariedEmployeeTest {


    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Integer memberId=100;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();

        Transaction cmt = new ChangeUnaffiliationTransaction(empId);

        cmt.execute();
    }

    @Test
    public void paySingleSalariedEmployeeTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-31");

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc != null);

        Assert.assertTrue(pc.getPayDate().getTime() == payDate.getTime());

        Assert.assertEquals(new Double(1000.00), pc.getGrossPay());


        Assert.assertEquals(new Double(0.00), pc.getDeductions());

        Assert.assertEquals(new Double(1000.00), pc.getNetPay());

        Assert.assertTrue(pc.getField("disposition") instanceof HoldMethod);
    }

    @Test
    public void paySingleSalariedEmployeeOnwrongDateTest() throws ParseException {
        Integer empId = 3;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-30");

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc == null);
    }
}

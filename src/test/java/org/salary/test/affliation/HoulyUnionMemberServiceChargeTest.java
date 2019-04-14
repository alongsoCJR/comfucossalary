package org.salary.test.affliation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.PayCheck;
import org.salary.properties.method.HoldMethod;
import org.salary.service.ServiceChargeTransaction;
import org.salary.service.TimeCardTransaction;
import org.salary.service.base.PaydayTransaction;
import org.salary.service.impl.add.AddHourlyEmployee;
import org.salary.service.impl.change.affiliation.ChangMemberTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月07日 22:04:44
 * @Description:
 * @ClassName: HoulyUnionMemberServiceChargeTest
 */
public class HoulyUnionMemberServiceChargeTest {
    @Test
    @Before
    public void addHourlyEmployeeTest() {
        Integer empId = 3;

        Integer memberId = 100;

        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 20.00);

        t.execute();

        Transaction cmt = new ChangMemberTransaction(empId, memberId, 10.00);

        cmt.execute();
    }

    @Test
    public void paySingleSalariedEmployeeTest() throws ParseException {
        Integer empId = 3;

        Integer memberId = 100;


        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction sct = new ServiceChargeTransaction(memberId, payDate, new Double(12.00));

        sct.execute();

        Transaction t = new TimeCardTransaction(empId, payDate, 8.0);

        t.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 160.0, 22.0, 8.0 * 20 - 12 - 10);

    }

    private void validatePaycheck(PaydayTransaction pt, Integer empId, Date payDate, double grossPay, double deductions, double pay) {
        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc != null);

        Assert.assertTrue(pc.getPayDate().getTime() == payDate.getTime());

        Assert.assertEquals(new Double(grossPay), pc.getGrossPay());

        Assert.assertEquals(new Double(deductions), pc.getDeductions());

        Assert.assertEquals(new Double(pay), pc.getNetPay());

        Assert.assertTrue(pc.getField("disposition") instanceof HoldMethod);

    }


    @Test
    public void paySingleSalariedEmployeeTest1() throws ParseException {
        Integer empId = 3;

        Integer memberId = 100;


        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction sct = new ServiceChargeTransaction(memberId, payDate, new Double(12.00));

        sct.execute();

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-05");

        Transaction sect = new ServiceChargeTransaction(memberId, date, new Double(10.00));

        sect.execute();

        Transaction t = new TimeCardTransaction(empId, payDate, 8.0);

        t.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 160.0, 22,8.0 * 20 - 12 - 10);

    }
}

package org.salary.test.affliation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.PayCheck;
import org.salary.properties.method.HoldMethod;
import org.salary.service.base.PaydayTransaction;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.service.impl.change.affiliation.ChangMemberTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月07日 15:22:58
 * @Description:
 * @ClassName: UnionMenberTest
 */
public class UnionMenberTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();

    }

    @Test
    public void paySingleSalariedEmployeeTest() throws ParseException {
        Integer empId = 3;

        Integer memberId = 333;

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-31");

        Transaction cmt = new ChangMemberTransaction(empId, memberId, 99.9);

        cmt.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);

        pt.execute();

        validatePaycheck(pt, empId, payDate, 1000-99.9*5);

    }

    private void validatePaycheck(PaydayTransaction pt, Integer empId, Date payDate, double pay) {
        PayCheck pc = pt.getPaycheck(empId);

        Assert.assertTrue(pc != null);

        Assert.assertTrue(pc.getPayDate().getTime() == payDate.getTime());

        Assert.assertEquals(new Double(1000), pc.getGrossPay());

        Assert.assertEquals(new Double(99.9*5), pc.getDeductions());

        Assert.assertEquals(new Double(pay), pc.getNetPay());

        Assert.assertTrue(pc.getField("disposition") instanceof HoldMethod);

    }
}

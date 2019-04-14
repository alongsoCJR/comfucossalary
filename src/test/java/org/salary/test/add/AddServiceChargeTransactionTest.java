package org.salary.test.add;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.ServiceCharge;
import org.salary.properties.affiliation.UnionAffiliation;
import org.salary.service.ServiceChargeTransaction;
import org.salary.service.impl.add.AddCommisionedEmployee;
import org.salary.util.GpayrollDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 16:40:07
 * @Description:
 * @ClassName: AddServiceChargeTransactionTest
 */
public class AddServiceChargeTransactionTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddCommisionedEmployee(empId, "Bob", "Home", 1000.00, 20.00);

        t.execute();
    }

    @Test
    public void addServiceChargeTransactionTest() throws ParseException {
        Integer empId = 3;

        Integer memberId = 86;

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");


        UnionAffiliation af = new UnionAffiliation(memberId, 12.5);

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        e.setAffiliation(af);

        GpayrollDatabase.addUnionMember(memberId, e);

        Transaction sct = new ServiceChargeTransaction(memberId, date, new Double(12.95));

        sct.execute();

        ServiceCharge sc = af.getServiceCharge(date);

        Assert.assertTrue(sc != null);

        Assert.assertEquals(new Double("12.95"), sc.getAmount());
    }

}

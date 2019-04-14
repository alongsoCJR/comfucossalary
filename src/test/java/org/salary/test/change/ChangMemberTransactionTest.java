package org.salary.test.change;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Affiliation;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.properties.affiliation.NoAffiliation;
import org.salary.properties.affiliation.UnionAffiliation;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.service.impl.change.affiliation.ChangMemberTransaction;
import org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 21:37:28
 * @Description:
 * @ClassName: ChangMemberTransactionTest
 */
public class ChangMemberTransactionTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();
    }

    @Test
    public void changMemberTransactionTestTest() {
        Integer empId = 3;

        Integer memberId = 1342;

        Transaction cmt = new ChangMemberTransaction(empId, memberId, 99.42);

        cmt.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Affiliation af = e.getAffiliation();

        Assert.assertTrue(af != null);

        Assert.assertTrue(af instanceof UnionAffiliation);

        UnionAffiliation uf = (UnionAffiliation) af;

        Assert.assertEquals(new Double(99.42), uf.getWeeklyCharge());

        Employee member = GpayrollDatabase.getUnionMember(memberId);

        Assert.assertTrue(member != null);

        Assert.assertTrue(member == e);
    }

    @Test
    @After
    public void changUnaffiliationTransactionTestTest() {
        Integer empId = 3;

        Integer memberId = 1342;

        Transaction cmt = new ChangeUnaffiliationTransaction(empId);

        cmt.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Affiliation af = e.getAffiliation();

        Assert.assertTrue(af != null);

        Assert.assertTrue(af instanceof NoAffiliation);

        Employee member = GpayrollDatabase.getUnionMember(memberId);

        Assert.assertTrue(member == null);
    }

}

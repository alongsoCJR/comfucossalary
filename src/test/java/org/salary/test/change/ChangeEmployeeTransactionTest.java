package org.salary.test.change;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.service.impl.change.properties.ChangeAddressTransaction;
import org.salary.service.impl.change.properties.ChangeNameTransaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 11:15:13
 * @Description:
 * @ClassName: ChangeEmployeeTransactionTest
 */
public class ChangeEmployeeTransactionTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();
    }

    @Test
    public void changeEmployeeNameTransactinTest() {
        Integer empId = 3;

        Transaction ct = new ChangeNameTransaction(empId, "Lily");

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        Assert.assertEquals("Lily", e.getName());

    }

    @Test
    public void changeEmployeeAddressTransactinTest() {
        Integer empId = 3;

        Transaction ct = new ChangeAddressTransaction(empId, "BeiJingHaidian");

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        Assert.assertEquals("BeiJingHaidian", e.getAddress());

    }
}

package org.salary.test.change;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.properties.method.DirectMethod;
import org.salary.properties.method.HoldMethod;
import org.salary.properties.method.MailMethod;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.service.impl.change.method.ChangeDirectMethodTransaction;
import org.salary.service.impl.change.method.ChangeHoldMethodTransaction;
import org.salary.service.impl.change.method.ChangeMailMethodTransaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 11:15:13
 * @Description:
 * @ClassName: ChangeEmployeeTransactionTest
 */
public class ChangeMethodTransactionTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();
    }

    @Test
    public void changeDirectMethodTransactinTest() {
        Integer empId = 3;

        Transaction ct = new ChangeDirectMethodTransaction(empId,"中国建设银行","6719165298146728");

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        Assert.assertTrue(e.getPaymentMethod() instanceof DirectMethod);

        DirectMethod directMethod=(DirectMethod) e.getPaymentMethod();

        Assert.assertTrue(directMethod != null);

        Assert.assertEquals("6719165298146728",directMethod.getAccount());

        Assert.assertEquals("中国建设银行",directMethod.getBank());
    }

    @Test
    public void changeMailMethodTransactinTest() {
        Integer empId = 3;

        Transaction ct = new ChangeMailMethodTransaction(empId,"海淀区中关村路一号楼十座1101室");

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        Assert.assertTrue(e.getPaymentMethod() instanceof MailMethod);

        MailMethod mailMethod=(MailMethod) e.getPaymentMethod();

        Assert.assertTrue(mailMethod != null);

        Assert.assertEquals("海淀区中关村路一号楼十座1101室",mailMethod.getAddress());


    }

    @Test
    public void changeHoldMethodTransactinTest() {
        Integer empId = 3;

        Transaction ct = new ChangeHoldMethodTransaction(empId);

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        Assert.assertTrue(e.getPaymentMethod() instanceof HoldMethod);
    }
}

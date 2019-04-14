package org.salary.test.delete;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.service.DeleteEmployeeTransaction;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:41:19
 * @Description:
 * @ClassName: DeleteEmployeeTest
 */
public class DeleteEmployeeTest {


    //to do test
    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();
    }

    @Test
    public void deleteSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction dt = new DeleteEmployeeTransaction(empId);

        dt.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e == null);
    }
}

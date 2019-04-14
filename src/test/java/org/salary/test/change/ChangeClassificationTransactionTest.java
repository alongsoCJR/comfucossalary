package org.salary.test.change;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.PaymentClassification;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.properties.classfication.CommisionedClassfication;
import org.salary.properties.classfication.HourlyClassfication;
import org.salary.properties.classfication.SalariedClassfication;
import org.salary.properties.schedule.BiweeklySchedule;
import org.salary.properties.schedule.MonthlySchedule;
import org.salary.properties.schedule.WeeklySchedule;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.service.impl.change.classification.ChangeCommisionedClassificationTransaction;
import org.salary.service.impl.change.classification.ChangeHourlyClassificationTransaction;
import org.salary.service.impl.change.classification.ChangeSalariedClassificationTransaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 11:15:13
 * @Description:
 * @ClassName: ChangeEmployeeTransactionTest
 */
public class ChangeClassificationTransactionTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();
    }

    @Test
    public void changeToHourlyClassificationTransactionTest() {
        Integer empId = 3;

        Transaction ct = new ChangeHourlyClassificationTransaction(empId, 20.00);

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        PaymentClassification pc = e.getPaymentClassification();

        Assert.assertTrue(pc instanceof HourlyClassfication);

        Double houlyRate = ((HourlyClassfication) pc).getHourlyRate();

        Assert.assertEquals(new Double(20.0), houlyRate);

        Assert.assertTrue(e.getPaymentSchedule() instanceof WeeklySchedule);

    }

    @Test
    public void changeToCommisionedClassificationTransactionTest() {
        Integer empId = 3;

        Transaction ct = new ChangeCommisionedClassificationTransaction(empId, 2000.00, 200.00);

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        PaymentClassification pc = e.getPaymentClassification();

        Assert.assertTrue(pc instanceof CommisionedClassfication);

        CommisionedClassfication cc = (CommisionedClassfication) pc;

        Assert.assertEquals(new Double(2000.0), cc.getSalary());

        Assert.assertEquals(new Double(200.0), cc.getCommisionRate());

        Assert.assertTrue(e.getPaymentSchedule() instanceof BiweeklySchedule);
    }

    @Test
    public void changeToSalariedClassificationTransactionTest() {
        Integer empId = 3;

        Transaction ct = new ChangeSalariedClassificationTransaction(empId, 3000.00);

        ct.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertTrue(e != null);

        PaymentClassification pc = e.getPaymentClassification();

        Assert.assertTrue(pc instanceof SalariedClassfication);

        SalariedClassfication cc = (SalariedClassfication) pc;

        Assert.assertEquals(new Double(3000.0), cc.getSalary());

        Assert.assertTrue(e.getPaymentSchedule() instanceof MonthlySchedule);
    }
}

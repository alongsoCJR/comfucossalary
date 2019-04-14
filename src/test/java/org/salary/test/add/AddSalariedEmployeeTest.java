package org.salary.test.add;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.PaymentClassification;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.properties.classfication.CommisionedClassfication;
import org.salary.properties.classfication.HourlyClassfication;
import org.salary.properties.classfication.SalariedClassfication;
import org.salary.properties.method.DirectMethod;
import org.salary.properties.method.HoldMethod;
import org.salary.properties.method.MailMethod;
import org.salary.properties.schedule.BiweeklySchedule;
import org.salary.properties.schedule.MonthlySchedule;
import org.salary.properties.schedule.WeeklySchedule;
import org.salary.service.impl.add.AddCommisionedEmployee;
import org.salary.service.impl.add.AddHourlyEmployee;
import org.salary.service.impl.add.AddSalariedEmployee;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月04日 08:05:48
 * @Description: 新增Employee测试案例
 * @ClassName: AddSalariedEmployeeTest
 */
public class AddSalariedEmployeeTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 1;

        Transaction t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);

        t.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertEquals("Bob", e.getName());

        PaymentClassification sc=e.getPaymentClassification();

        Assert.assertTrue(sc instanceof SalariedClassfication);

        Assert.assertEquals(new Double("1000.00"), ((SalariedClassfication) sc).getSalary());


        Assert.assertTrue(e.getPaymentSchedule() instanceof MonthlySchedule);

        Assert.assertTrue(e.getPaymentMethod() instanceof HoldMethod);
    }

    @Test
    public void addHourlyEmployeeTest() {
        Integer empId = 2;

        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 20.00);

        t.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertEquals("Bob", e.getName());

        PaymentClassification sc=e.getPaymentClassification();

        Assert.assertTrue(sc instanceof HourlyClassfication);

        Assert.assertEquals(new Double("20.00"), ((HourlyClassfication) sc).getHourlyRate());

        Assert.assertTrue(e.getPaymentSchedule() instanceof WeeklySchedule);

        DirectMethod directMethod = new DirectMethod("中国建设银行","6719165298146728");

        e.setPaymentMethod(directMethod);

        Assert.assertTrue(e.getPaymentMethod() instanceof DirectMethod);

        Assert.assertEquals("6719165298146728", ((DirectMethod) e.getPaymentMethod()).getAccount());

    }

    @Test
    @After
    public void addCommisionedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddCommisionedEmployee(empId, "Bob", "Home", 1000.00,200.00);

        t.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        Assert.assertEquals("Bob", e.getName());

        PaymentClassification sc=e.getPaymentClassification();

        Assert.assertTrue(sc instanceof CommisionedClassfication);

        Assert.assertEquals(new Double("200.00"), ((CommisionedClassfication) sc).getCommisionRate());

        Assert.assertTrue(e.getPaymentSchedule() instanceof BiweeklySchedule);

        MailMethod mailMethod = new MailMethod("海淀区中关村路一号楼十座1101室");

        e.setPaymentMethod(mailMethod);

        Assert.assertTrue(e.getPaymentMethod() instanceof MailMethod);

        Assert.assertEquals("海淀区中关村路一号楼十座1101室", ((MailMethod) e.getPaymentMethod()).getAddress());

    }
}

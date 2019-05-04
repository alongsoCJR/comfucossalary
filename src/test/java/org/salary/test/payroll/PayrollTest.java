package org.salary.test.payroll;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.payroll.Payroll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @param
 * @author chenjianrong-lhq 2019-04-21 22:38
 * @return
 * @Title:
 * @Description:刘哥的测试案例
 */
public class PayrollTest {

    Payroll payroll = null;

    @Before
    public void setUp() {
        payroll = new Payroll();
    }

    @After
    public void tearDown() {
        payroll = null;
    }

    @Test
    public void testPayMonthlyEmployee() throws Exception {
        Object[][] commands = new Object[][]{
                {"org.salary.service.impl.add.AddSalariedEmployee", new Integer("101"), "Bob", "Beijing", new Double("1000.0")},
                {"org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction", new Integer("101")},
                {"org.salary.service.impl.add.AddSalariedEmployee", new Integer("102"), "Joe", "Beijing", new Double("2000.0")},
                {"org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction", new Integer("102")}
        };
        payroll.parse(commands);

        Date payDay = parseDate("2019-4-30");
        Map<Integer, Double> payments = payroll.payFor(payDay);
        Assert.assertEquals(2, payments.size());
        Assert.assertEquals(Double.valueOf(1000.0), payments.get(new Integer("101")), 0.001);
        Assert.assertEquals(Double.valueOf(2000.0), payments.get(new Integer("102")), 0.001);

    }

    @Test
    public void testPayHourlyEmployee() throws Exception {
        Object[][] commands = new Object[][]{
                {"org.salary.service.impl.add.AddHourlyEmployee", new Integer("102"), "Joe", "Beijing", new Double("15.0")},
                {"org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction", new Integer("102")},
                {"org.salary.service.TimeCardTransaction", new Integer("102"), parseDate("2019-4-24"), new Double("8")}
        };
        payroll.parse(commands);
        Date payDay = parseDate("2019-4-26"); //Friday

        Map<Integer, Double> payments = payroll.payFor(payDay);

        Assert.assertEquals(1, payments.size());
        Assert.assertEquals(15.0 * 8, (payments.get(new Integer("102"))), 0.01);

    }

    @Test
    public void testChangeEmployeeType() throws Exception {

        Object[][] commands = new Object[][]{
                {"org.salary.service.impl.add.AddSalariedEmployee", new Integer("101"), "Bob", "Beijing", new Double("1000.0")},
                {"org.salary.service.impl.change.affiliation.ChangeUnaffiliationTransaction", new Integer("101")}
        };

        payroll.parse(commands);

        Date payDay = parseDate("2019-4-30");
        Map<Integer, Double> payments = payroll.payFor(payDay);


        Assert.assertEquals(1, payments.size());
        Assert.assertEquals(Double.valueOf(1000), payments.get(new Integer("101")), 0.01);

        commands = new Object[][]{{"org.salary.service.impl.change.classification.ChangeHourlyClassificationTransaction", new Integer("101"), new Double("25.0")}};

        payroll.parse(commands);

        payments = payroll.payFor(payDay);
        Assert.assertEquals(0, payments.size());

    }

    public Date parseDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(s);
    }

}

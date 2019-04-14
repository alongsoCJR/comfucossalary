package org.salary.test.add;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.salary.base.PaymentClassification;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.TimeCard;
import org.salary.properties.classfication.HourlyClassfication;
import org.salary.service.TimeCardTransaction;
import org.salary.service.impl.add.AddHourlyEmployee;
import org.salary.util.GpayrollDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:55:19
 * @Description:
 * @ClassName: AddTimeCardTransactionTest
 */
public class AddTimeCardTransactionTest {

    @Test
    @Before
    public void addSalariedEmployeeTest() {
        Integer empId = 3;

        Transaction t = new AddHourlyEmployee(empId, "Bob", "Home", 10.00);

        t.execute();
    }

    @Test
    public void addTimeCardTransactionTest() throws ParseException {
        Integer empId = 3;

        Double hours = 10.5;

        Date cardDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-05");

        Transaction t = new TimeCardTransaction(empId, cardDate, hours);

        t.execute();

        Employee e = GpayrollDatabase.getEmployee(empId);

        PaymentClassification pc = e.getPaymentClassification();

        Assert.assertTrue(pc instanceof HourlyClassfication);

        TimeCard timeCard = ((HourlyClassfication) pc).getTimeCard(cardDate);

        Assert.assertTrue(timeCard != null);

        Assert.assertEquals(new Double(10.5), timeCard.getHours());

//        List<TimeCard> timeCardList=(HourlyClassfication(pc)).getTimeCardList();
//
//        Assert.assertEquals(1,timeCardList.size());
//
//        Assert.assertEquals(10.5,timeCardList.get(0).getHours());
//
//        Assert.assertEquals(10.5,timeCardList.get(0).getTimeCard(cardDate).getHours());


    }
}

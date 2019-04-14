package org.salary.properties.classfication;

import org.salary.base.PaymentClassification;
import org.salary.po.PayCheck;
import org.salary.po.TimeCard;
import org.salary.util.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chenjianrong-lhq 2019年04月05日 10:51:06
 * @Description:PaymentClassification本来是定义为接口的，由于isInPayPeriod（）方法可以抽象出来放在父类，因此改为抽象类
 * @ClassName: HourlyClassfication
 */
public class HourlyClassfication extends PaymentClassification {

    private Double hourlyRate;

    //什么情况下，应该用Map，什么情况下该用List
    //高并发的情况下，这样设计能行吗？
    private Map<Date, TimeCard> timeCardMap = new HashMap<Date, TimeCard>(10);

    public HourlyClassfication(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public TimeCard getTimeCard(Date cardDate) {
        return timeCardMap.get(cardDate);
    }

    public TimeCard addTimeCard(Date cardDate, TimeCard timeCard) {
        return timeCardMap.put(cardDate, timeCard);
    }

//    public double calculatePay(PayCheck pc) {
//        Double org.salary = 0.0;
//        Set<Map.Entry<Date, TimeCard>> entrySet = timeCardMap.entrySet();
//        for (Map.Entry<Date, TimeCard> entry : entrySet) {
//            Date date = entry.getKey();
//            if (DateUtils.isDateBetweenPreWeekOfTheLastDate(date, pc.getPayDate())) {
//                TimeCard timeCard = entry.getValue();
//                Double hours = timeCard.getHours();
//                //if (hours > 8) {
//                //   org.salary += hourlyRate * 1.5 * (hours - 8.0);
//                //    hours = 8.0;
//                //}
//                org.salary += hourlyRate * hours;
//
//                Double overtime = Math.max(0.0, hours - 8.0);
//                Double straightTime = hours - overtime;
//                org.salary += straightTime * hourlyRate + overtime * hourlyRate * 1.5;
//            }
//        }
//        return org.salary;
//    }


    public double calculatePay(PayCheck pc) {
        Double totalPay = 0.0;
        Set<Map.Entry<Date, TimeCard>> entrySet = timeCardMap.entrySet();
        for (Map.Entry<Date, TimeCard> entry : entrySet) {
            Date date = entry.getKey();
            TimeCard timeCard = entry.getValue();
//            if (isInPayPeriod(timeCard, pc.getPayDate())) {
//            if (isInPayPeriod(timeCard.getCardDate(), pc)) {
            if (DateUtils.isInPayPeriod(timeCard.getCardDate(), pc)) {
                totalPay += calculatePayForTimeCard(timeCard);
            }
        }
        return totalPay;
    }

    private Double calculatePayForTimeCard(TimeCard timeCard) {
        Double hours = timeCard.getHours();
        Double overtime = Math.max(0.0, hours - 8.0);
        Double straightTime = hours - overtime;
        return straightTime * hourlyRate + overtime * hourlyRate * 1.5;
    }

//    private boolean isInPayPeriod(TimeCard timeCard, Date payDate) {
//        Calendar calendar = Calendar.getInstance();
//
//        Date date = timeCard.getCardDate();
//
//        if (payDate != null) {
//            calendar.setTime(payDate);
//            calendar.add(Calendar.DAY_OF_YEAR, -7);
//        }
//
//        if (date.getTime() >= calendar.getTime().getTime() && date.getTime() <= payDate.getTime()) {
//            return true;
//        }
//        return false;
//    }
}

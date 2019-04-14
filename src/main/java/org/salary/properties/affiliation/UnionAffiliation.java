package org.salary.properties.affiliation;

import org.salary.base.Affiliation;
import org.salary.po.PayCheck;
import org.salary.po.ServiceCharge;
import org.salary.util.DateUtils;

import java.util.*;

/**
 * @author chenjianrong-lhq 2019年04月05日 16:57:26
 * @Description:
 * @ClassName: UnionAffiliation
 */
public class UnionAffiliation implements Affiliation {

    private Integer memberId;

    private Double weeklyCharge;

    private static Map<Date, ServiceCharge> chargeMap = new HashMap<Date, ServiceCharge>(10);

    public UnionAffiliation(Integer memberId, Double weeklyCharge) {
        this.memberId = memberId;
        this.weeklyCharge = weeklyCharge;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Double getWeeklyCharge() {
        return weeklyCharge;
    }

    public ServiceCharge getServiceCharge(Date date) {
        return chargeMap.get(date);
    }

    public void addServiceCharge(Date date, Double amount) {
        chargeMap.put(date, new ServiceCharge(date, amount));
    }

    public double calculateDeductions(PayCheck pc) {
        double totalCharges = 0;
        int fridays = numberOfFridayInpayPeriod(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
        totalCharges = weeklyCharge * fridays;

        Set<Map.Entry<Date, ServiceCharge>> entrySet = chargeMap.entrySet();

        for (Map.Entry<Date, ServiceCharge> entry : entrySet) {
            Date date = entry.getKey();
            ServiceCharge serviceCharge = entry.getValue();
//            if (isInPayPeriod(serviceCharge.getDate(), pc)) {
            if (DateUtils.isInPayPeriod(serviceCharge.getDate(), pc)) {
                totalCharges += serviceCharge.getAmount();
            }
        }
        return totalCharges;
    }

//    private boolean isInPayPeriod(Date theDate, PayCheck pc) {
//        Date payPeriodEndDate = pc.getPayPeriodEndDate();
//
//        Date payPeriodstartDate = pc.getPayPeriodStartDate();
//
//        return (theDate.getTime() > payPeriodstartDate.getTime() && theDate.getTime() <= payPeriodEndDate.getTime());
//    }

    public int numberOfFridayInpayPeriod(Date payPeriodStartDate, Date payPeriodEndDate) {
        int fridays = 0;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(payPeriodStartDate);
        Calendar endCalendar = Calendar.getInstance();

        endCalendar.setTime(payPeriodEndDate);
//        int days = startCalendar.get(Calendar.DAY_OF_YEAR);
//        System.out.println(days);
//
//        int endays = endCalendar.get(Calendar.DAY_OF_YEAR);
//        System.out.println(endays);
        for (int day = startCalendar.get(Calendar.DAY_OF_YEAR); day <= endCalendar.get(Calendar.DAY_OF_YEAR); day++) {
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);
            if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                fridays++;
            }
        }
        return fridays;
    }
}


package org.salary.po;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 12:09:50
 * @Description:
 * @ClassName: TimeCard
 */
public class TimeCard {

    private Date cardDate;

    private Double hours;

    public TimeCard(Date cardDate, Double hours) {
        this.cardDate = cardDate;
        this.hours = hours;
    }

    public Date getCardDate() {
        return cardDate;
    }

    public Double getHours() {
        return hours;
    }

}

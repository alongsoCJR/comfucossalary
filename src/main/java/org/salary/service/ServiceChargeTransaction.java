package org.salary.service;

import org.salary.base.Affiliation;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.properties.affiliation.UnionAffiliation;
import org.salary.util.GpayrollDatabase;

import java.util.Date;

/**
 * @author chenjianrong-lhq 2019年04月05日 16:59:48
 * @Description:
 * @ClassName: ServiceChargeTransaction
 */
public class ServiceChargeTransaction implements Transaction {

    private Integer memberId;

    private Date date;

    private Double amount;

    public ServiceChargeTransaction(Integer memberId, Date date, Double amount) {
        this.memberId=memberId;
        this.date=date;
        this.amount=amount;
    }

    public void execute() {
        Employee e= GpayrollDatabase.getUnionMember(memberId);

        Affiliation af=e.getAffiliation();

        if(af instanceof UnionAffiliation){
            ((UnionAffiliation) af).addServiceCharge(date,amount);
        }
    }
}

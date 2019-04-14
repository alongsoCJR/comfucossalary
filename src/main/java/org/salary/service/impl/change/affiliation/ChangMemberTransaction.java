package org.salary.service.impl.change.affiliation;

import org.salary.base.Affiliation;
import org.salary.po.Employee;
import org.salary.properties.affiliation.UnionAffiliation;
import org.salary.service.base.ChangeAffiliationTransaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 21:50:34
 * @Description:
 * @ClassName: ChangMemberTransaction
 */
public class ChangMemberTransaction extends ChangeAffiliationTransaction {

    private Integer memberId;

    private Double weeklyCharge;

    public ChangMemberTransaction(Integer empId, Integer memberId, Double weeklyCharge) {
        super(empId);
        this.memberId=memberId;
        this.weeklyCharge=weeklyCharge;
    }


    protected void recordMembership(Employee e) {
        GpayrollDatabase.addUnionMember(memberId,e);
    }

    protected Affiliation getAffiliation() {
        return new UnionAffiliation(memberId,weeklyCharge);
    }
}

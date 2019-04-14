package org.salary.service.impl.change.affiliation;

import org.salary.base.Affiliation;
import org.salary.po.Employee;
import org.salary.properties.affiliation.NoAffiliation;
import org.salary.properties.affiliation.UnionAffiliation;
import org.salary.service.base.ChangeAffiliationTransaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月06日 22:11:12
 * @Description:
 * @ClassName: ChangeUnaffiliationTransaction
 */
public class ChangeUnaffiliationTransaction extends ChangeAffiliationTransaction {

    public ChangeUnaffiliationTransaction(Integer empId) {
        super(empId);
    }

    protected void recordMembership(Employee e) {
        Affiliation af=e.getAffiliation();

        if(af instanceof UnionAffiliation){
            int memberId=((UnionAffiliation) af).getMemberId();
            GpayrollDatabase.removeUnionMember(memberId);
        }
    }

    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}

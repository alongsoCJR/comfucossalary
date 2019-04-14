package org.salary.service.base;

import org.salary.base.Affiliation;
import org.salary.po.Employee;

/**
 * @author chenjianrong-lhq 2019年04月06日 21:54:01
 * @Description:
 * @ClassName: ChangeAffiliationTransaction
 */
public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction{

    public ChangeAffiliationTransaction(Integer itsEmpid) {
        super(itsEmpid);
    }

    //模板方法
    protected void change(Employee employee) {
        recordMembership(employee);
        employee.setAffiliation(getAffiliation());
    }

    //钩子
    protected abstract void recordMembership(Employee e);

    protected abstract Affiliation getAffiliation();

}

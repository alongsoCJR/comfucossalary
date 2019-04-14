package org.salary.service.impl.change.properties;

import org.salary.po.Employee;
import org.salary.service.base.ChangeEmployeeTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 11:20:53
 * @Description:
 * @ClassName: ChangeNameTransaction
 */
public class ChangeNameTransaction extends ChangeEmployeeTransaction {

    private String name;

    public ChangeNameTransaction(Integer empId, String name) {
        super(empId);
        this.name=name;
    }


    protected void change(Employee employee) {
        employee.setName(name);
//        GpayrollDatabase.addEmployee(employee.getEmpId(),employee);
    }
}

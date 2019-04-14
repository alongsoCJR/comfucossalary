package org.salary.service.impl.change.properties;

import org.salary.po.Employee;
import org.salary.service.base.ChangeEmployeeTransaction;

/**
 * @author chenjianrong-lhq 2019年04月06日 11:45:58
 * @Description:
 * @ClassName: ChangeAddressTransaction
 */
public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    private String address;

    public ChangeAddressTransaction(Integer empId, String address) {
        super(empId);
        this.address=address;
    }

    protected void change(Employee employee) {
        employee.setAddress(address);
    }
}

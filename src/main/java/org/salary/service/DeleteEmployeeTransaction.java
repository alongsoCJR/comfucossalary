package org.salary.service;

import org.salary.base.Transaction;
import org.salary.util.GpayrollDatabase;

/**
 * @author chenjianrong-lhq 2019年04月05日 11:45:33
 * @Description:
 * @ClassName: DeleteEmployeeTransaction
 */
public class DeleteEmployeeTransaction implements Transaction {
    private Integer empId;

    public DeleteEmployeeTransaction() {
    }

    public DeleteEmployeeTransaction(Integer empId) {
        this.empId=empId;
    }

    public void execute() {
        GpayrollDatabase.deleteEmployee(empId);
    }
}

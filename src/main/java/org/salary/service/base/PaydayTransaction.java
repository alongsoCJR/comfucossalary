package org.salary.service.base;

import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.PayCheck;
import org.salary.util.GpayrollDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chenjianrong-lhq 2019年04月07日 10:46:26
 * @Description:
 * @ClassName: PaydayTransaction
 */
public class PaydayTransaction implements Transaction {
    private Date payDate;

    private Map<Integer, PayCheck> payChecksMap = new HashMap<Integer, PayCheck>(10);

    public PaydayTransaction(Date payDate) {
        this.payDate = payDate;
    }

    public void execute() {
        Set<Integer> empIds = GpayrollDatabase.getAllEmployeeIds();

        for (Integer empId : empIds) {
            Employee employee = GpayrollDatabase.getEmployee(empId);

            if (employee.isPaydate(payDate)) {
                PayCheck pc = new PayCheck(employee.getPayPeriodStartDate(payDate),payDate);
                pc.setPayDate(payDate);
                pc.setField("disposition",employee.getPaymentMethod());
                payChecksMap.put(empId, pc);
                employee.payday(pc);
            }
        }


    }

    public PayCheck getPaycheck(Integer empId) {
        return payChecksMap.get(empId);
    }
}

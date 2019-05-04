package org.salary.payroll;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.salary.base.Transaction;
import org.salary.po.Employee;
import org.salary.po.PayCheck;
import org.salary.service.base.PaydayTransaction;
import org.salary.util.GpayrollDatabase;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Payroll {

    private final Log logger = LogFactory.getLog(this.getClass());

    private static Map<Integer, Double> resultMap = new HashMap<Integer, Double>();

    public Map<Integer, Double> payFor(Date payDate) {

        Set<Integer> empIds = GpayrollDatabase.getAllEmployeeIds();

        for (Integer empId : empIds) {
            Employee employee = GpayrollDatabase.getEmployee(empId);

            if (employee.isPaydate(payDate)) {
                PaydayTransaction pt = new PaydayTransaction(payDate);
                pt.execute();
                PayCheck pc = pt.getPaycheck(empId);
                resultMap.put(empId, pc.getNetPay());
            } else {
                resultMap.remove(empId);
            }
        }
        return resultMap;
    }


    private Object reflateInstance(String className, Object[] args) throws Exception {

        // 获取需要映射对象的Class信息
        Class newoneClass = Class.forName(className);
        // 构造函数的参数类型数组
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        // 获取需反射类的构造函数
        Constructor cons = newoneClass.getConstructor(argsClass);
        // 执行构造函数
        return cons.newInstance(args);
    }

    public void parse(Object[][] commands) throws Exception {
        //遍历二维数组，取出一条对象信息 for
        for (Object[] employee : commands) {
            String className = employee[0].toString();
            //遍历数组内容，第一个参数为类名，后面的参数对应构造函数参数名
            logger.info("类名：" + className);
            Class clz = Class.forName(className);

            //获取构造函数参数集合
            Class[] argsClass = new Class[employee.length - 1];
            Object[] arg = new Object[employee.length - 1];
            for (int i = 1, j = employee.length; i < j; i++) {
                argsClass[i - 1] = employee[i].getClass();
                arg[i - 1] = employee[i];
            }
            Constructor cons = clz.getConstructor(argsClass);

            //利用反射实例化对象
            Transaction ts = (Transaction) cons.newInstance(arg);

            //执行方法execute
            ts.execute();
        }
    }
}

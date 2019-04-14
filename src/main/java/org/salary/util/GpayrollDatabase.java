package org.salary.util;

import org.salary.po.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chenjianrong-lhq 2019年04月04日 08:20:31
 * @Description:
 * @ClassName: GpayrollDatabase
 */
public class GpayrollDatabase {
    private static Map<Integer, Employee> itsEmployees=new HashMap<Integer, Employee>(10);

    private static Map<Integer, Employee> itsMembers=new HashMap<Integer, Employee>(10);

    private GpayrollDatabase() {

    }

    public static void addEmployee(Integer empId, Employee employee) {
        itsEmployees.put(empId, employee);
    }

    public static Employee getEmployee(Integer empId) {
        return itsEmployees.get(empId);
    }

    public static void deleteEmployee(Integer empId) {
        itsEmployees.remove(empId);
    }

    public static void addUnionMember(Integer memberId, Employee employee) {
        itsMembers.put(memberId,employee);
    }

    public static Employee getUnionMember(Integer memberId) {
        return itsMembers.get(memberId);
    }

    public static void removeUnionMember(Integer memberId) {
        itsMembers.remove(memberId);
    }

    public static Set<Integer> getAllEmployeeIds() {
        return itsEmployees.keySet();
    }
}

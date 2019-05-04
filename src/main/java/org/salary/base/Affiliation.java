package org.salary.base;

import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月05日 16:56:38
 * @Description:
 * @ClassName: Affiliation
 */
public interface Affiliation {

    //计算扣除的费用（-branch的测试）
    double calculateDeductions(PayCheck pc);
}

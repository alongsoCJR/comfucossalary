package org.salary.properties.affiliation;

import org.salary.base.Affiliation;
import org.salary.po.PayCheck;

/**
 * @author chenjianrong-lhq 2019年04月06日 22:10:32
 * @Description:
 * @ClassName: NoAffiliation
 */
public class NoAffiliation implements Affiliation {

    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}

package org.salary.util;

/**
 * @author chenjianrong-lhq 2019年04月05日 12:46:31
 * @Description:
 * @ClassName: BusinessException
 */
public class BusinessException extends Exception{

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}

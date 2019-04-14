package org.salary.base;

/**
 * @author chenjianrong-lhq 2019年04月05日 09:46:23
 * @Description:
 * @ClassName: Transaction
 */
public interface Transaction {

    //命令模式，将每个请求封装成一个对象，命令模式将发出请求的对象和接受请求的对象解耦
    void execute();

}

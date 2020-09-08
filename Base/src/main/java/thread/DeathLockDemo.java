package thread;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class DeathLockDemo
{
    public static void main (String[] args)
    {
        Account fromAccount = new Account("张三",1000);
        Account toAccount = new Account("李四",2000);
        /*
        // 任务一：张三向李四转1元钱
        Thread threadA = new Thread(new TransferMoneyThread(fromAccount,toAccount,1));
        // 任务二：李四向张三转2元钱
        Thread threadB = new Thread(new TransferMoneyThread(toAccount,fromAccount,2));
        */
        Allocater allocater = new Allocater();
        // 任务一：张三向李四转1元钱
        Thread threadA = new Thread(new TransferMoneyThread_DeathLockSolution01(fromAccount,toAccount,1,allocater));
        // 任务二：李四向张三转2元钱
        Thread threadB = new Thread(new TransferMoneyThread_DeathLockSolution01(toAccount,fromAccount,2,allocater));

        threadA.start();
        threadB.start();

    }
}

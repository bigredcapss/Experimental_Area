package thread;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class TransferMoneyThread_DeathLockSolution01 implements Runnable
{
    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private Integer amount;
    private Allocater allocater;//第三方角色

    public TransferMoneyThread_DeathLockSolution01 (Account fromAccount, Account toAccount, Integer amount,Allocater
                                                    allocater)
    {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.allocater = allocater;
    }

    @Override
    public void run ()
    {
        while(true){
            if(allocater.apply(fromAccount,toAccount))//申请资源统一到一个临界区
            {
                try
                {
                    synchronized (fromAccount){
                        synchronized (toAccount){
                            if(fromAccount.getBalance()>=amount){
                                fromAccount.decreDebit(amount);
                                toAccount.increDebit(amount);
                            }
                        }
                    }
                    System.out.println(fromAccount.getAccountName()+"----"+fromAccount.getBalance());
                    System.out.println(toAccount.getAccountName()+"----"+toAccount.getBalance());
                }finally
                {
                    allocater.free(fromAccount,toAccount);
                }
            }
        }

    }
}

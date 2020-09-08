package thread;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class TransferMoneyThread_DeathLockSolution03 implements Runnable
{
    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private Integer amount;

    public TransferMoneyThread_DeathLockSolution03 (Account fromAccount, Account toAccount, Integer amount)
    {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run ()
    {
        Account left = null;
        Account right = null;
        if(fromAccount.hashCode()>toAccount.hashCode())
        {
            left = toAccount;
            right = fromAccount;
        }
        while(true){
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

        }

    }
}

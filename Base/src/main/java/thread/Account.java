package thread;

/**
 * @Description:账户类
 * @Author:BigRedCaps
 */
public class Account
{
    private String accountName;
    private Integer balance;

    public Account (String accountName, Integer balance)
    {
        this.accountName = accountName;
        this.balance = balance;
    }

    public String getAccountName ()
    {
        return accountName;
    }

    public void setAccountName (String accountName)
    {
        this.accountName = accountName;
    }

    public Integer getBalance ()
    {
        return balance;
    }

    public void setBalance (Integer balance)
    {
        this.balance = balance;
    }

    public void decreDebit(Integer amount){
        this.balance-=amount;
    }

    public void increDebit(Integer amount){
        this.balance+=amount;
    }
}

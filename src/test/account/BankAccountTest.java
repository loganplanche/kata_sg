package account;

import exception.OverdraftReachedException;
import operation.Operation;
import operation.OperationType;
import org.junit.Before;
import org.junit.Test;
import exception.NegativeAmountException;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class BankAccountTest {

    private BankAccount bankAccTest;
    private BigDecimal balanceBeforeDeposit;

    @Before
    public void init() {
        bankAccTest = new BankAccount();
        balanceBeforeDeposit = bankAccTest.getBalance();
    }

    @Test
    public void deposit1000InMyAccount() throws NegativeAmountException {
    	BigDecimal deposit = new BigDecimal(1000);
        bankAccTest.deposit(deposit);
        BigDecimal balanceAfterDeposit = bankAccTest.getBalance();
        assertEquals(balanceAfterDeposit, balanceBeforeDeposit.add(deposit));
    }

    @Test(expected = NegativeAmountException.class)
    public void depositNegativeAmountInMyAccount() throws NegativeAmountException {
    	BigDecimal negativeDeposit = new BigDecimal(50.5).negate();
        bankAccTest.deposit(negativeDeposit);
    }

    @Test
    public void withdraw500FromMyAccount() throws NegativeAmountException, OverdraftReachedException {
    	BigDecimal firstDeposit = new BigDecimal(1500);
        bankAccTest.deposit(firstDeposit);
        BigDecimal withdrawal = new BigDecimal(500);
        bankAccTest.withdraw(withdrawal);
        BigDecimal balanceAfterDeposit = bankAccTest.getBalance();
        assertEquals(balanceAfterDeposit, firstDeposit.subtract(withdrawal));
    }

    @Test(expected = NegativeAmountException.class)
    public void withdrawNegativeAmountFromMyAccount() throws NegativeAmountException, OverdraftReachedException {
    	BigDecimal negativeWithdrawal = new BigDecimal(17.8).negate();
        bankAccTest.withdraw(negativeWithdrawal);
    }

    @Test(expected = OverdraftReachedException.class)
    public void withdrawWhenOverdraftIsReached() throws NegativeAmountException, OverdraftReachedException {
        // As balance at creation equals 0.0, we need to withdraw 150 to hit the overdraft limit
        bankAccTest.withdraw(new BigDecimal(150));
        // Then we try to withdraw again, and the exception is thrown
        BigDecimal withdrawal = new BigDecimal(200);
        bankAccTest.withdraw(withdrawal);
    }

    @Test
    public void retrieveOperationsOfMyAccount() throws NegativeAmountException, OverdraftReachedException {
        bankAccTest.deposit(new BigDecimal(5000));
        bankAccTest.withdraw(new BigDecimal(4500));
        bankAccTest.deposit(new BigDecimal(700));
        int nbOperationsPerformed = bankAccTest.getOperations().size();
        assertEquals(3, nbOperationsPerformed);
        int nbDeposits = 0;
        for (Operation operation : bankAccTest.getOperations()) {
            if (operation.getType().equals(OperationType.DEPOSIT)) {
                nbDeposits++;
            }
        }
        assertEquals(2, nbDeposits);
    }

}

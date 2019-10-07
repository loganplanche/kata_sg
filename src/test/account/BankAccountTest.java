package account;

import exception.OverdraftReachedException;
import operation.Operation;
import operation.OperationType;
import org.junit.Before;
import org.junit.Test;
import exception.NegativeAmountException;

import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount bankAccTest;
    private double balanceBeforeDeposit;

    @Before
    public void init() {
        bankAccTest = new BankAccount();
        balanceBeforeDeposit = bankAccTest.getBalance();
    }

    @Test
    public void deposit1000InMyAccount() throws NegativeAmountException {
        double deposit = 1000.0;
        bankAccTest.deposit(deposit);
        double balanceAfterDeposit = bankAccTest.getBalance();
        assertEquals(balanceAfterDeposit, balanceBeforeDeposit + deposit, 0.0);
    }

    @Test(expected = NegativeAmountException.class)
    public void depositNegativeAmountInMyAccount() throws NegativeAmountException {
        double negativeDeposit = -50.5;
        bankAccTest.deposit(negativeDeposit);
    }

    @Test
    public void withdraw500FromMyAccount() throws NegativeAmountException, OverdraftReachedException {
        double firstDeposit = 1500.0;
        bankAccTest.deposit(firstDeposit);
        double withdrawal = 500.0;
        bankAccTest.withdraw(withdrawal);
        double balanceAfterDeposit = bankAccTest.getBalance();
        assertEquals(balanceAfterDeposit, firstDeposit - withdrawal, 0.0);
    }

    @Test(expected = NegativeAmountException.class)
    public void withdrawNegativeAmountFromMyAccount() throws NegativeAmountException, OverdraftReachedException {
        double negativeWithdrawal = -17.80;
        bankAccTest.withdraw(negativeWithdrawal);
    }

    @Test(expected = OverdraftReachedException.class)
    public void withdrawWhenOverdraftIsReached() throws NegativeAmountException, OverdraftReachedException {
        // As balance at creation equals 0.0, we need to withdraw 150 to hit the overdraft limit
        bankAccTest.withdraw(150.0);
        // Then we try to withdraw again, and the exception is thrown
        double withdrawal = 200.0;
        bankAccTest.withdraw(withdrawal);
    }

    @Test
    public void retrieveOperationsOfMyAccount() throws NegativeAmountException, OverdraftReachedException {
        bankAccTest.deposit(5000.0);
        bankAccTest.withdraw(4500.0);
        bankAccTest.deposit(700.0);
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

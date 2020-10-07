package account;

import exception.NegativeAmountException;
import exception.OverdraftReachedException;
import operation.Operation;
import operation.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private BigDecimal balance;
    private List<Operation> operations;
    private BigDecimal overdraft;

    public BankAccount() {
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
        //Say that one can't withdraw under -150 of balance.
        this.overdraft = new BigDecimal(150).negate();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal deposit) throws NegativeAmountException {
        if (deposit.compareTo(BigDecimal.ZERO) <= -1) {
            String message = deposit + " is a negative amount. Please try again with a positive amount.";
            throw new NegativeAmountException(message);
        }
        performOperation(OperationType.DEPOSIT, deposit);
    }

    public void withdraw(BigDecimal withdrawal) throws NegativeAmountException, OverdraftReachedException {
        if (withdrawal.compareTo(BigDecimal.ZERO) <= -1) {
            String message = withdrawal + " is a negative amount. Please try again with a positive amount.";
            throw new NegativeAmountException(message);
        } else if (this.balance.compareTo(overdraft) == 0) {
            String message = "You have reached your bank overdraft, you can not perform a withdrawal at the moment.";
            throw new OverdraftReachedException(message);
        }
        performOperation(OperationType.WITHDRAWAL, withdrawal.negate());
    }

    private void performOperation(OperationType type, BigDecimal amount) {
        this.balance = this.balance.add(amount);
        System.out.println(this.balance.toString());
        addToOperations(type, amount);
    }

    private void addToOperations(OperationType type, BigDecimal amount) {
        Operation operation = new Operation(type, LocalDate.now(), amount);
        this.operations.add(operation);
    }

    public List<Operation> getOperations() {
        return operations;
    }
}

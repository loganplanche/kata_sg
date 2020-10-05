package account;

import exception.NegativeAmountException;
import exception.OverdraftReachedException;
import operation.Operation;
import operation.OperationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<Operation> operations;

    public BankAccount() {
        this.balance = 0.0;
        this.operations = new ArrayList<>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double deposit) throws NegativeAmountException {
        if (deposit < 0) {
            String message = deposit + " is a negative amount. Please try again with a positive amount.";
            throw new NegativeAmountException(message);
        }
        performOperation(OperationType.DEPOSIT, deposit);
    }

    public void withdraw(double withdrawal) throws NegativeAmountException, OverdraftReachedException {
        if (withdrawal < 0) {
            String message = withdrawal + " is a negative amount. Please try again with a positive amount.";
            throw new NegativeAmountException(message);
        } else if (this.balance <= -150.0) {
            String message = "You have reached your bank overdraft, you can not perform a withdrawal at the moment.";
            throw new OverdraftReachedException(message);
        }
        performOperation(OperationType.WITHDRAWAL, -withdrawal);
    }

    private void performOperation(OperationType type, double amount) {
        this.balance += amount;
        addToOperations(type, amount);
    }

    private void addToOperations(OperationType type, double amount) {
        Operation operation = new Operation(type, LocalDate.now(), amount);
        this.operations.add(operation);
    }

    public List<Operation> getOperations() {
        return operations;
    }
}

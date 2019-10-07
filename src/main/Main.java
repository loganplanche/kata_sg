import account.BankAccount;
import exception.NegativeAmountException;
import exception.OverdraftReachedException;
import operation.Operation;

import java.util.List;

public class Main {

    public static void main(String[] args) throws NegativeAmountException, OverdraftReachedException {
        BankAccount myBankAccount = new BankAccount();
        System.out.println(myBankAccount.getBalance());
        myBankAccount.withdraw(75.5);
        System.out.println(myBankAccount.getBalance());
        myBankAccount.deposit(1000.0);
        List<Operation> operations = myBankAccount.getOperations();
        for (Operation operation : operations) {
            System.out.println(operation.toString());
        }
        System.out.println(myBankAccount.getBalance());
    }

}

import account.BankAccount;
import exception.NegativeAmountException;
import exception.OverdraftReachedException;
import operation.Operation;

import java.math.BigDecimal;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NegativeAmountException, OverdraftReachedException {
		BankAccount myBankAccount = new BankAccount();
		System.out.println("My savings are " + myBankAccount.getBalance() + " euros");
		myBankAccount.withdraw(new BigDecimal(75.5));
		System.out.println("My savings are " + myBankAccount.getBalance() + " euros");
		myBankAccount.deposit(new BigDecimal(1000.0));
		System.out.println("My savings are " + myBankAccount.getBalance() + " euros");
		List<Operation> operations = myBankAccount.getOperations();
		for (Operation operation : operations) {
			System.out.println(operation.toString());
		}
		System.out.println(myBankAccount.getBalance());
	}

}

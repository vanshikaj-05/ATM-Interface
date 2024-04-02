import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(double balance) {
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

public class ATM {
    private Account account;
    private Scanner scanner;

    public ATM(double initialBalance) {
        account = new Account(initialBalance);
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Transaction History");
        System.out.println("6. Quit");
        System.out.print("Enter your choice: ");
    }

    public void checkBalance() {
        System.out.println("Your balance is: $" + account.getBalance());
    }

    public void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    public void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    public void transfer() {
        System.out.print("Enter the recipient's initial balance: $");
        double recipientBalance = scanner.nextDouble();
        Account recipient = new Account(recipientBalance);

        System.out.print("Enter the amount to transfer: $");
        double amount = scanner.nextDouble();
        account.transfer(recipient, amount);
    }

    public void viewTransactionHistory() {
        ArrayList<Transaction> history = account.getTransactionHistory();
        System.out.println("Transaction History:");
        for (Transaction transaction : history) {
            System.out.println("Type: " + transaction.getType() + ", Amount: $" + transaction.getAmount());
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM(1000.0);
        int choice;

        do {
            atm.displayMenu();
            choice = atm.scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    atm.deposit();
                    break;
                case 3:
                    atm.withdraw();
                    break;
                case 4:
                    atm.transfer();
                    break;
                case 5:
                    atm.viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        atm.scanner.close();
    }
}

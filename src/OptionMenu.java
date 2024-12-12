import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

// Simplified and commented version of the OptionMenu class
public class OptionMenu {
    private Scanner menuInput = new Scanner(System.in);
    private DecimalFormat moneyFormat = new DecimalFormat("'\u20B9' ###,##0.00");
    private HashMap<Integer, Account> accounts = new HashMap<>(); // Store customer accounts



    // Default sample accounts for testing
    private void initializeAccounts() {
        // Default Accounts
        accounts.put(789456, new Account(789456, 654321, 1500, 7500)); // Account 1
        accounts.put(456789, new Account(456789, 987654, 5000, 10000)); // Account 2
    }

    // Main menu for login or account creation
    public void mainMenu() throws IOException {
        while (true) {
            System.out.println("\nWelcome to the ATM System");
            System.out.println("--------------------------------------------------");
            System.out.println("1 - Login");
            System.out.println("2 - Create Account");
            System.out.println("3 - Exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Choose an option: ");

            try {
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1 -> login();
                    case 2 -> createAccount();
                    case 3 -> {
                        System.out.println("Thank you for using the ATM System. Goodbye!");
                        System.exit(0); // Exit the application
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                menuInput.next(); // Clear invalid input
            }
        }
    }

    // Login process
    private void login() throws IOException {
        System.out.print("\nEnter your customer number: ");
        int customerNumber = menuInput.nextInt();

        System.out.print("Enter your PIN: ");
        int pin = menuInput.nextInt();

        Account account = accounts.get(customerNumber); // Retrieve account

        if (account != null && account.getPinNumber() == pin) {
            accountMenu(account); // Proceed to account menu
        } else {
            System.out.println("Invalid customer number or PIN. Please try again.");
        }
    }

    // Menu for account actions
    private void accountMenu(Account account) {
        while (true) {
            System.out.println("\nSelect Account Type:");
            System.out.println("--------------------------------------------------");
            System.out.println("1 - Checking Account");
            System.out.println("2 - Savings Account");
            System.out.println("3 - Exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Choose an option: ");

            try {
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1 -> checkingMenu(account);
                    case 2 -> savingsMenu(account);
                    case 3 -> {
                        return; // Exit to the main menu
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                menuInput.next(); // Clear invalid input
            }
        }
    }

    // Checking account menu
    private void checkingMenu(Account account) {
        while (true) {
            System.out.println("\nChecking Account:");
            System.out.println("--------------------------------------------------");
            System.out.println("1 - View Balance");
            System.out.println("2 - Withdraw Funds");
            System.out.println("3 - Deposit Funds");
            System.out.println("4 - Exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Choose an option: ");

            try {
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1 -> System.out.println("Balance: " + moneyFormat.format(account.getCheckingBalance()));
                    case 2 -> account.getCheckingWithdrawInput();
                    case 3 -> account.getCheckingDepositInput();
                    case 4 -> {
                        return; // Exit to the account menu
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                menuInput.next(); // Clear invalid input
            }
        }
    }

    // Savings account menu
    private void savingsMenu(Account account) {
        while (true) {
            System.out.println("\nSavings Account:");
            System.out.println("--------------------------------------------------");
            System.out.println("1 - View Balance");
            System.out.println("2 - Withdraw Funds");
            System.out.println("3 - Deposit Funds");
            System.out.println("4 - Exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Choose an option: ");

            try {
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1 -> System.out.println("Balance: " + moneyFormat.format(account.getSavingBalance()));
                    case 2 -> account.getsavingWithdrawInput();
                    case 3 -> account.getSavingDepositInput();
                    case 4 -> {
                        return; // Exit to the account menu
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only.");
                menuInput.next(); // Clear invalid input
            }
        }
    }

    // Account creation process
    private void createAccount() {
        System.out.print("\nEnter a customer number: ");
        int customerNumber = menuInput.nextInt();

        if (accounts.containsKey(customerNumber)) {
            System.out.println("This customer number is already registered.");
            return;
        }

        System.out.print("Enter a PIN: ");
        int pin = menuInput.nextInt();

        accounts.put(customerNumber, new Account(customerNumber, pin));
        System.out.println("Account created successfully! Please log in.");
    }
}

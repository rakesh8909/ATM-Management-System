import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
    // variables
    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    Scanner sc = new Scanner(System.in);
    private DecimalFormat moneyFormat = new DecimalFormat("'\u20B9' ###,##0.00");

    public Account() {
    }

    public Account(int customerNumber, int pinNumber) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double calcCheckingWithdraw(double amount) {
        checkingBalance = (checkingBalance - amount);
        return checkingBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingBalance = (savingBalance - amount);
        return savingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingBalance = (checkingBalance + amount);
        return checkingBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingBalance = (savingBalance + amount);
        return savingBalance;
    }

    public void calcCheckTransfer(double amount) {
        checkingBalance = checkingBalance - amount;
        savingBalance = savingBalance + amount;
    }

    public void calcSavingTransfer(double amount) {
        savingBalance = savingBalance - amount;
        checkingBalance = checkingBalance + amount;
    }

    public void getCheckingWithdrawInput() {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
                System.out.print("\nAmount you want to withdraw from Checkings Account: ");
                double amount = sc.nextDouble();
                if ((checkingBalance - amount) >= 0 && amount >= 0) {
                    calcCheckingWithdraw(amount);
                    System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
                    flag = true;
                } else {
                    System.out.println("\nBalance Cannot be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void getsavingWithdrawInput() {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                System.out.print("\nAmount you want to withdraw from Savings Account: ");
                double amount = sc.nextDouble();
                if ((savingBalance - amount) >= 0 && amount >= 0) {
                    calcSavingWithdraw(amount);
                    System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                    flag = true;
                } else {
                    System.out.println("\nBalance Cannot Be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void getCheckingDepositInput() {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
                System.out.print("\nAmount you want to deposit from Checkings Account: ");
                double amount = sc.nextDouble();
                if ((checkingBalance + amount) >= 0 && amount >= 0) {
                    calcCheckingDeposit(amount);
                    System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
                    flag = true;
                } else {
                    System.out.println("\nBalance Cannot Be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void getSavingDepositInput() {
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                System.out.print("\nAmount you want to deposit into your Savings Account: ");
                double amount = sc.nextDouble();

                if ((savingBalance + amount) >= 0 && amount >= 0) {
                    calcSavingDeposit(amount);
                    System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                    flag = true;
                } else {
                    System.out.println("\nBalance Cannot Be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }

    public void getTransferInput(String accType) {
        boolean flag = false;
        while (!flag) {
            try {
                if (accType.equals("Checkings")) {
                    System.out.println("\nSelect an account you wish to tranfers funds to:");
                    System.out.println("1. Savings");
                    System.out.println("2. Exit");
                    System.out.print("\nChoice: ");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
                            System.out.print("\nAmount you want to deposit into your Savings Account: ");
                            double amount = sc.nextDouble();
                            if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {
                                calcCheckTransfer(amount);
                                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                                System.out.println(
                                        "\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
                                flag = true;
                            } else {
                                System.out.println("\nBalance Cannot Be Negative.");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nInvalid Choice.");
                            break;
                    }
                } else if (accType.equals("Savings")) {
                    System.out.println("\nSelect an account you wish to tranfers funds to: ");
                    System.out.println("1. Checkings");
                    System.out.println("2. Exit");
                    System.out.print("\nChoice: ");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                            System.out.print("\nAmount you want to deposit into your savings account: ");
                            double amount = sc.nextDouble();
                            if ((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {
                                calcSavingTransfer(amount);
                                System.out.println("\nCurrent checkings account balance: " + moneyFormat.format(checkingBalance));
                                System.out.println("\nCurrent savings account balance: " + moneyFormat.format(savingBalance));
                                flag = true;
                            } else {
                                System.out.println("\nBalance Cannot Be Negative.");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nInvalid Choice.");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                sc.next();
            }
        }
    }
}

package Atm;

import java.util.Scanner;

class BankingOperation {
    String name;
    String userId;
    float accountNo;
    String password;
    float balance = 15000f;
    int transactions = 0;
    String transactionHist = "";
    Scanner sc = new Scanner(System.in);

    public void register() {

        System.out.println("\n Please Enter Your Name");
        this.name = sc.nextLine();
        System.out.println("\nPlease Enter Your UserId");
        this.userId = sc.nextLine();
        System.out.println("\nPlease Enter Your Account Number");
        this.accountNo = sc.nextFloat();
        System.out.println("\nPlease Enter A Strong Password");
        sc.nextLine();
        this.password = sc.nextLine();
        System.out.println("\nYou Registered Successfully...Please Login Yourself");
    }

    public boolean login() {
        Scanner s = new Scanner(System.in);
        boolean log = false;
        int attempt = 0;
        String Password = "";
        String UserId = "";
        while (!log) {
            if (attempt == 0)
                System.out.println("\nPlease Enter Your UserId....");
            UserId = s.nextLine();
            if (UserId.equals(userId)) {
                while (!log) {
                    if (attempt == 0)
                        System.out.println("\nPlease Enter Your Password...");
                    Password = s.nextLine();
                    if (Password.equals(password)) {
                        System.out.println("\nLogin Successfully");
                        log = true;
                    } else {
                        if (attempt >= 2) {
                            System.out.println("\nYou Enter wrong Password again \nOops!! You Exceeded Maximum Number of Attempts...\nPlease Try after 24 hours");
                            log = true;
                            System.exit(0);
                        }
                        System.out.println("\nIncorrect Password");
                        System.out.println("\nPlease ReEnter Your Password");
                        attempt++;

                    }
                }
            } else {
                if (attempt >= 2) {
                    System.out.println("\nOops!! You Exceeded Maximum Number of Attempts...\nPlease Try after 24 hours");
                    log = true;
                    System.exit(0);
                }
                System.out.println("\nUserId is not Found...\nPlease Reenter the userId..");
                attempt++;

            }

        }
        return log;
    }

    public void withdraw() {
        Scanner ip = new Scanner(System.in);
        System.out.println("\nEnter amount of withdraw...");
        float amount = ip.nextFloat();
        try {
            if (balance >= amount) {
                transactions++;
                balance = balance - amount;
                System.out.println("\nWithdraw Successfully");
                String str = amount + " Rs withdraw\n";
                transactionHist = transactionHist.concat(str);
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
        }
    }

    public void transfer() {
        Scanner sca = new Scanner(System.in);
        System.out.println("\nEnter the recipient name ....");
        String recipient = sca.nextLine();
        System.out.println("\nEnter the recipient Account Number...");
        String recipient_account = sca.nextLine();
        System.out.println("\nEnter amount of the transfer...");
        float amount = sca.nextFloat();
        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\n Successfully Transaction to" + recipient);
                    String str = amount + " Rs transferred to " + recipient + "\n";
                    transactionHist = transactionHist.concat(str);
                } else {
                    System.out.println("\nSorry....Limit is 50000.00");
                }
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {

        }
    }

    public void deposit() {
        System.out.println("\nEnter amount to deposit...");
        float amount = sc.nextFloat();
        try {
            if (amount <= 10000f) {
                transactions++;
                balance += amount;
                System.out.println("\n Successfully Deposited");
                String str = amount + "rs deposited\n";
                transactionHist = transactionHist.concat(str);
            } else {
                System.out.println("\nSorry ...Limit is 100000.00");
            }
        } catch (Exception e) {
        }
    }

    public void checkBalance() {
        System.out.println("\n" + balance + " Rs");
    }

    public void transactionHist() {
        if (transactions == 0) {
            System.out.println("\nEmpty");
        } else {
            System.out.println("\n" + transactionHist);
        }
    }
}

public class AtmInterface {
    public static int takeIntergerInput(int limit) {
        int input = 0;

        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (flag && input > limit || input < 1) {
                    System.out.println(" Choose only number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only  integer value");
                flag = false;
            }
        }
        ;
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n*************WELCOME TO APNA BANK ATM************");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println(" Enter your choice ... ");
        int choice = takeIntergerInput(2);
        if (choice == 1) {
            BankingOperation b = new BankingOperation();
            b.register();
            while (true) {
                System.out.println("\n1.login \n2.Exit");
                System.out.println("Enter your choice ...");
                int ch = takeIntergerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n\n*************WELCOME BACK " + b.name + "*************8\n");
                        boolean isFinish = false;
                        while (!isFinish) {
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.check Balance \n5.Transaction history \n6.isFinish");
                            System.out.println("\nEnter your Choice ... ");
                            int c = takeIntergerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transactionHist();
                                    break;
                                case 6:
                                    isFinish = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}





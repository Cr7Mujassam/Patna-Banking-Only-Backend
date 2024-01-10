package BankLogic;

import java.sql.SQLException;
import java.util.Scanner;

import BankLogicWithdrawBalance.WithDrawYourBalance;
import CloseAccount.CloseAccount;

public class PatnaBank {
	
	//Global Variables
	public static String phNumnber;

	public static void main(String[] args) throws SQLException,NumberFormatException  {
		Scanner input = new Scanner(System.in);
		//Banking Project
		System.out.println("Welcome to Patna Bank Of India");
		System.out.println("How can we Help You");
		
		//Giving Option to Customer
		//Press 1 = Create Account,2 = Already Customer
		
		System.out.println("Press 1 if you want to open account");
		System.out.println("Press 2 if you are Existing Customer");
		int bankingChoice = input.nextInt();
		
		if(bankingChoice==1) {
			createdAccount(input);
			
			//for displaying values of created account please type 1 for displaying else nothing
			System.out.println("Please Press 1 for your Account Number");
			int display = input.nextInt();
			displayAccountDeatil(display);
		}else if(bankingChoice==2) {
			System.out.println("Press 1 for withdraw Balance");
			System.out.println("Press 2 for Know your Account Balance");
			System.out.println("Press 3 for close your Account");
			callingLogic(input);
		}else {
			System.out.println("Invalid Input!!!");
		}
		
	
	}
	
	public static void createdAccount(Scanner input) throws SQLException,NumberFormatException {
		CreatingNewAccount creatingNewAccount = new CreatingNewAccount();
		//Taking Input for open Account like Name,Address And Initial Balance
		System.out.println("Enter Your Amount");
		String amounts = input.next();
		System.out.println("Enter Your name");
		String name = input.next();
		System.out.println("Enter Your address");
		String address = input.next();
		System.out.println("Enter your phone Number");
		String phoneNumber = input.next();
		phNumnber = phoneNumber;
		
		int accountCreated = creatingNewAccount.dataInsertionIsInProgress(name,phoneNumber, address, Integer.parseInt(amounts));
		System.out.println("Accoutn Created = " + accountCreated);
	}
	
	//for displaying the account detail which is created
	public static void displayAccountDeatil(int display) throws SQLException {
		CreatingNewAccount diaplayingAccountNo = new CreatingNewAccount();
		if(display==1) {
			diaplayingAccountNo.displayAccDetails(phNumnber);
		}
	}
	
	//pathway for optimising the code quality
	public static void callingLogic(Scanner input) throws SQLException {
		int choosedOption = input.nextInt();
		
		//for withdrawing the balance
		if(choosedOption==1) {
			withdrawAmount();
		}else if(choosedOption==2) {
			displayAccountBalance();
		}else if(choosedOption==3) {
			closeAccount();
		}
	}
	
	//for withdrawing amount
	public static void withdrawAmount() throws SQLException {
		Scanner withDraw = new Scanner(System.in);
		WithDrawYourBalance withDrawYourBalance = new WithDrawYourBalance();
		System.out.println("Please Enter your account number");
		String acc = withDraw.next();
		int remainingBalance = 0;
		
		remainingBalance = withDrawYourBalance.fetchBalance(acc);
		System.out.println("Your Balance :-" +remainingBalance);
		
		System.out.println("Please Enter Amount to Withdraw");
		int ammount = withDraw.nextInt();
		
		if(remainingBalance>ammount) {
			int updateBalanceafterWithdraw = remainingBalance - ammount;
			withDrawYourBalance.updateBalanceAfterWithdraw(updateBalanceafterWithdraw,Integer.parseInt(acc));
			System.out.println("You have successfully Withdraw your amount :-" + ammount);
			remainingBalance = withDrawYourBalance.fetchBalance(acc);
			System.out.println("And your Remaining Blanace is :-" + remainingBalance);
		}else {
			System.out.println("Sorry!!! Please Enter Less amount");
			withdrawAmount();
		}
		
		
		//for not data leakage
		withDraw.close();
	}
	
	//for displaying the account balance
	public static void displayAccountBalance() throws SQLException {
		Scanner show = new Scanner(System.in);
		WithDrawYourBalance showYourBalance = new WithDrawYourBalance();
			System.out.println("Enter your Account Number to See your Balance");
			String accNo = show.next();
		int balance = showYourBalance.fetchBalance(accNo);
		System.out.println("Your Balnace is:- " + balance);
			show.close();

	}
	
	//for close your account
	public static void closeAccount() throws SQLException {
		Scanner show = new Scanner(System.in);
		CloseAccount closeAccount = new CloseAccount();
		
		System.out.println("Enter your Account Number to Close");
		String accNo = show.next();
		
		closeAccount.closeAccount(accNo);
		
		show.close();
	}

}

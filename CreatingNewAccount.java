package BankLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import CreateSerialNumber.CountingAccountNumber;

public class CreatingNewAccount {
	//Taking Input for open Account like Name,Address And Initial Balance
	
	public int dataInsertionIsInProgress(String name,String phoneNumber,String address,int amount) throws SQLException,NumberFormatException {
		//DataBaseConnectionLogic insertData = new DataBaseConnectionLogic();
		String url = "jdbc:mysql://localhost:3306/PatnaBankOfIndia";
		Connection con = DriverManager.getConnection(url,"root","Patna@2022");
		
		//logic for account number creation
		int accountNumber = accNumber(phoneNumber,name);
		
		//query writing
		String insertQuery = "insert into CreateAccount values(?,?,?,?,?,?)";
		PreparedStatement pre = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		CountingAccountNumber countingAccountNumber = new CountingAccountNumber();
		pre.setInt(1,countingAccountNumber.generateSerailNumber());
		pre.setString(2,String.valueOf(accountNumber));
		pre.setString(3, phoneNumber);
		pre.setString(4, name);
		pre.setString(5, address);
		pre.setInt(6, amount);
		
		//inserting the customerInfo
		int successOrFail = pre.executeUpdate();
		return successOrFail;
	}
	
	public void accountDeatilShowing() {
		
	}
	
	//logic for making uniqueAccountNumber
	public int accNumber(String phoneNumber,String name) {
		int key = name.charAt(1);
		String stringAcc = phoneNumber.substring(0, 5)  +  String.valueOf(key);
		int accountNo = Integer.parseInt(stringAcc);
		return accountNo-100000;
	}
	
	//logic for displaying the accountNumber After Creation of account
	public void displayAccDetails(String number) throws SQLException {
		String Query = "Select AccountNo from CreateAccount where CustMobileNumber=?";
		String url = "jdbc:mysql://localhost:3306/PatnaBankOfIndia";
		Connection con = DriverManager.getConnection(url,"root","Patna@2022");
		PreparedStatement pre = con.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
		pre.setString(1, number);
		ResultSet rs = pre.executeQuery();
		
		while(rs.next()) {
			System.out.println("Your Account Number is :- " + rs.getInt("AccountNo"));
		}
	}
}

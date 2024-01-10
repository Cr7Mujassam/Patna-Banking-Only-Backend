package CloseAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import BankLogicWithdrawBalance.WithDrawYourBalance;

//for closing account;
public class CloseAccount {
	
	public void closeAccount(String accNo) throws SQLException {
		//DataBaseConnectionLogic insertData = new DataBaseConnectionLogic();
		String url = "jdbc:mysql://localhost:3306/PatnaBankOfIndia";
		Connection con = DriverManager.getConnection(url,"root","Patna@2022");
		
		WithDrawYourBalance accountBalanceRemaining = new WithDrawYourBalance();
		int balance = accountBalanceRemaining.fetchBalance(accNo);
		
		String Query = "Delete from CreateAccount where AccountNo=?";
		PreparedStatement pre = con.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
		pre.setString(1, accNo);
		
		int confirmation = pre.executeUpdate();
		
		if(confirmation==1) {
			System.out.println("Account Closed Please take your Remaining Balance:-" + balance);
		}
	}
}

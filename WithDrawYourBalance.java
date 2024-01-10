package BankLogicWithdrawBalance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WithDrawYourBalance {
	
	public int fetchBalance(String accNo) throws SQLException {
		//DataBaseConnectionLogic insertData = new DataBaseConnectionLogic();
		String url = "jdbc:mysql://localhost:3306/PatnaBankOfIndia";
		Connection con = DriverManager.getConnection(url,"root","Patna@2022");
		
		String Query = "Select InitialDepositAmount from CreateAccount where AccountNo=?";
		PreparedStatement pre = con.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
		pre.setString(1, accNo);
		ResultSet rs = pre.executeQuery();
		int accAmmount = 0 ;
		while(rs.next()) {
			rs.getInt("InitialDepositAmount");
			accAmmount = rs.getInt("InitialDepositAmount");
		}
		
		return accAmmount;
	}
	
	public void updateBalanceAfterWithdraw(int balance,int accountNumber) throws SQLException {
		//DataBaseConnectionLogic insertData = new DataBaseConnectionLogic();
		String url = "jdbc:mysql://localhost:3306/PatnaBankOfIndia";
		Connection con = DriverManager.getConnection(url,"root","Patna@2022");
				
		String Query = "update CreateAccount set InitialDepositAmount=? where AccountNo=?";
		PreparedStatement pre = con.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
		pre.setString(1, String.valueOf(balance));
		pre.setInt(2, accountNumber);
		
		pre.executeUpdate();
		
		System.out.println("Balance Update!!!");
	}
}

package CreateSerialNumber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CountingAccountNumber {
	
	//generating the logic to create the serial Number
	public int generateSerailNumber() throws SQLException {
		//DataBaseConnectionLogic insertData = new DataBaseConnectionLogic();
		String url = "jdbc:mysql://localhost:3306/PatnaBankOfIndia";
		Connection con = DriverManager.getConnection(url,"root","Patna@2022");
				
		String Query = "Select SerialNo from CreateAccount";
		Statement smt = con.createStatement();
		
		ResultSet rs = smt.executeQuery(Query);
		
		ArrayList<Integer> accountCount = new ArrayList<>();
		
		while(rs.next()) {
			accountCount.add(rs.getInt("SerialNo"));
		}
		
		int size = accountCount.size();
		
		if(size==0) {
			return 1;
		}else {
			return accountCount.get(size-1)+1;
		}
		
	}

}

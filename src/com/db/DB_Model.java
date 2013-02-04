package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.model.*;

public class DB_Model {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*
	 * 
	 * Check if a user exists
	 * 
	 * */

	public static boolean checkIfUserExists(String user, String password) {
		Connection conn = null;
		Statement stmt = null;
		String checkLogin = "Select count(*) from customers where name = '"+user+"' and pass ='"+password+"'";
		ResultSet rs = null;
		Integer accessCount = new Integer(0);

		try {
			System.out.println("Before loading the driver");
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());

			stmt = conn.createStatement();

			rs = stmt.executeQuery(checkLogin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while(rs.next()){
				if(rs.getInt(1) != 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * 
	 * Insert a new user into the database
	 * 
	 * */
	public static boolean insert_user(Object obj) {
		CustomerForm num = (CustomerForm)obj;
		Statement stmt = null;

		try {
			System.out.println("Before loading the driver");
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Inserting new user");
			stmt = conn.createStatement();

			String insert = "INSERT INTO customers VALUES ( FLOOR(RAND() * 1000000), '"+num.getName()+"', '" +num.getAddress()+"', '"
					+ num.getCity()+"', '"+num.getState()+"', '"+num.getPin()+"', '"+num.getPhone()+"', '"+num.getEmail()+"', '"+num.getPass()+"', '"
					+num.getQuestion()+"' )" ;

			stmt.execute(insert);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean checkIfUserHasChecking(Connection conn, String name) {
		Statement stmt = null;
		int count = 0;
		String getCount = "SELECT count(*) from accounts where cust_id IN (SELECT pk_id from customers where name = '"+name+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Getting checking account info");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(getCount);

			try {
				while(rs.next()){
					count = rs.getInt(1);
					System.out.println("The count is " + count);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(count > 0){
				System.out.println(count);
				return true;
			}
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}	

	public static boolean checkIfUserHasAccount(Connection conn, Object obj) {
		String className = obj.getClass().getCanonicalName();
		String getCount = "";
		System.out.println("The class name is " + className);

		if(className.equals("com.struts.CheckAccountForm")){
			CheckAccountForm caf = (CheckAccountForm) obj;
			getCount = "SELECT count(*) from accounts where type='"+caf.getType()+"' and cust_id IN (SELECT pk_id from customers where name = '"+caf.getName()+"')";
		}
		else {
			DepositForm caf = (DepositForm) obj;
			getCount = "SELECT count(*) from accounts where type='"+caf.getType()+"' and cust_id IN (SELECT pk_id from customers where name = '"+caf.getName()+"')";
		}
		
		Statement stmt = null;
		int count = 0;
		//String getCount = "SELECT count(*) from accounts where type='"+caf.getType()+"' and cust_id IN (SELECT pk_id from customers where name = '"+caf.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Getting checking account info");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(getCount);

			try {
				while(rs.next()){
					count = rs.getInt(1);
					System.out.println("The count is " + count);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(count > 0){
				System.out.println(count);
				return true;
			}
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}	

	public static void depositChecking(Connection conn, Object obj) {
		Account cm = (Account)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		String deposit = "INSERT INTO accounts VALUES("+pk_id+",(SELECT pk_id from customers where name = '"+cm.getName()+"'), 'checking', "+cm.getBalance()+")";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			System.out.println("I am here...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Depositing " + cm.getBalance()+ " into checking.");
			stmt = conn.createStatement();

			stmt.execute(deposit);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void deposit(Connection conn, Object obj) {
		DepositForm cm = (DepositForm)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		String deposit = "UPDATE accounts SET balance = (balance + "+cm.getBalance()+") where type = '"+cm.getType()+"' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Depositing " + cm.getBalance()+ " into "+cm.getType()+".");
			stmt = conn.createStatement();

			stmt.execute(deposit);

			String trans = "INSERT into transactions values (FLOOR(RAND() * 1000000), SELECT pk_id from customers where name = '"+cm.getName()+"',"+
					"CURDATE(), 'deposit', "+cm.getBalance()+", '"+cm.getType()+"')";

			stmt.execute(trans);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void depositNew(Connection conn, Object obj) {
		DepositForm cm = (DepositForm)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String deposit = "INSERT into accounts VALUES(FLOOR(RAND() * 1000000), (SELECT pk_id from customers WHERE name = '"+cm.getName()+"'), '"+cm.getType()+"', "+cm.getBalance()+")";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Depositing " + cm.getBalance()+ " into "+cm.getType()+".");
			stmt = conn.createStatement();

			stmt.execute(deposit);

			String trans = "INSERT into transactions values (FLOOR(RAND() * 1000000), SELECT pk_id from customers where name = '"+cm.getName()+"',"+
					"CURDATE(), 'deposit', "+cm.getBalance()+", '"+cm.getType()+"')";

			stmt.execute(trans);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkIfUserHasSavings(Connection conn, String name) {
		Statement stmt = null;
		int count = 0;
		System.out.println("Checking if " +name+ " has a savings account.");
		String getCount = "SELECT count(*) from accounts where type='savings' and cust_id IN (SELECT pk_id from customers where name = '"+name+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Getting checking account info");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(getCount);

			try {
				while(rs.next()){
					count = rs.getInt(1);
					System.out.println("The count is " + count);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(count > 0){
				System.out.println(count);
				return true;
			}
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}	

	public static void depositSavings(Connection conn, Object obj) {
		Account sm = (Account)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		String deposit = "INSERT INTO accounts VALUES("+pk_id+",(SELECT pk_id from customers where name = '"+sm.getName()+"'), '"+sm.getType()+"', "+sm.getBalance()+")";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Depositing " + sm.getBalance()+ " into savings.");
			stmt = conn.createStatement();

			stmt.execute(deposit);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String getSavingsBalance(Connection conn, String name) {
		Statement stmt = null;
		int balance = 0;
		String ret_bal = "";
		String bal = "SELECT balance FROM accounts WHERE type = 'savings' and cust_id IN (SELECT pk_id from customers where name = '"+name+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(bal);

			try {
				while(rs.next()){
					balance = rs.getInt(1);
					ret_bal = Integer.toString(balance);

					System.out.println("The balance is " + balance);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return ret_bal;
	}

	public static String getCheckingBalance(Connection conn, String name) {
		Statement stmt = null;
		double balance = 0;
		String ret_bal = "";

		String bal = "SELECT balance FROM accounts WHERE type = 'checking' and cust_id IN (SELECT pk_id from customers where name = '"+name+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Grabbing balance from checking.");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(bal);

			try {
				while(rs.next()){
					balance = rs.getDouble(1);
					ret_bal = Double.toString(balance);
					System.out.println("balance is " + ret_bal);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return ret_bal;
	}

	public static String getBalance(Connection conn, Object obj) {
		CheckAccountForm caf = (CheckAccountForm) obj;
		Statement stmt = null;
		double balance = 0;
		String ret_bal = "";

		String bal = "SELECT balance FROM accounts WHERE type = '"+caf.getType()+"' and cust_id IN (SELECT pk_id from customers where name = '"+caf.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			System.out.println("Grabbing balance from checking.");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(bal);

			try {
				while(rs.next()){
					balance = rs.getDouble(1);
					ret_bal = Double.toString(balance);
					System.out.println("balance is " + ret_bal);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return ret_bal;
	}

	public static void withdrawalChecking(Connection conn, Object obj) {
		Account cm = (Account)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		String check = "SELECT balance from accounts where type='checking' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";
		String withdrawal = "UPDATE accounts SET balance = (balance - "+cm.getWithdrawal()+") where type = 'checking' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(check);

			while(rs.next()) {
				double i = rs.getDouble(1);
				if(Double.valueOf(cm.getWithdrawal()) > i) {
					return;
				}
			}
			stmt.execute(withdrawal);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void withdrawalSavings(Connection conn, Object obj) {
		Account cm = (Account)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		System.out.println("YO TRYING TO WITHDRAWAL " + cm.getWithdrawal());
		String check = "SELECT balance from accounts where type='"+cm.getType()+"' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";
		String withdrawal = "UPDATE accounts SET balance = (balance - "+cm.getWithdrawal()+") where type = 'savings' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(check);
			
			while(rs.next()) {
				double i = rs.getDouble(1);

				if(Double.valueOf(cm.getWithdrawal()) > i) {
					return;
				}
			}

			stmt.execute(withdrawal);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void withdrawal(Connection conn, Object obj) {
		WithdrawalForm cm = (WithdrawalForm)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		System.out.println("YO TRYING TO WITHDRAWAL " + cm.getBalance());
		String check = "SELECT balance from accounts where type='"+cm.getType()+"' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";
		String withdrawal = "UPDATE accounts SET balance = (balance - "+cm.getBalance()+") where type = '"+cm.getType()+"' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(check);

			while(rs.next()) {
				double i = rs.getDouble(1);

				if(Double.valueOf(cm.getBalance()) > i) {
					return;
				}
			}

			stmt.execute(withdrawal);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void updateSavings(Connection conn, Object obj) {
		Account cm = (Account)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		System.out.println("Trying to update savings with " + cm.getBalance());
		String deposit = "UPDATE accounts SET balance = (balance + "+cm.getDeposit()+") where type = '"+cm.getType()+"' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			stmt.execute(deposit);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updateChecking(Connection conn, Object obj) {
		Account cm = (Account)obj;
		Statement stmt = null;
		Random rand = new Random();
		int pkid = rand.nextInt(100000);
		String pk_id = String.valueOf(pkid);
		String deposit = "UPDATE accounts SET balance = (balance + "+cm.getDeposit()+") where type = 'checking' and cust_id IN (SELECT pk_id from customers WHERE name = '"+cm.getName()+"')";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			stmt.execute(deposit);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkQuestion(Object obj) throws SQLException {
		RecoverForm cm = (RecoverForm)obj;
		Statement stmt = null;
		int count = 0;
		String deposit = "SELECT * from customers where name = '"+cm.getName()+"' and question = '"+cm.getQuestion()+"'";

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(EnvManager.getDbUrl());
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(deposit);

			while(rs.next()) {
				count++;
			}

			if(count > 0) {
				String updatePW = "UPDATE customers SET pass='"+cm.getPass()+"' WHERE name = '"+cm.getName()+"'";
				stmt.execute(updatePW);

				return true;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
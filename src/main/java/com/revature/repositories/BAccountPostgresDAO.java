package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.launcher.BankUserLauncher;
import com.revature.models.Accounts;
import com.revature.models.BankCustomer;
import com.revature.models.BankUser;
import com.revature.util.ConnectionFactory;

public class BAccountPostgresDAO implements BAccountDAO{
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	public Accounts findOneAccount(int accountNumber, int customerID) throws AccountDoesNotExistException {
		
		Connection conn = this.cf.getConnection();
		Accounts ac=null;
		try {
			String sql = "select * from account where (account_number=? and account_user=?);";// we know this is a view for a gross sql query
			// we only use statements for very basic sql queries
			PreparedStatement getAccount=conn.prepareStatement(sql);
			getAccount.setInt(1, accountNumber);
			getAccount.setInt(2, customerID);
			//Statement s=conn.createStatement();
			ResultSet res=getAccount.executeQuery();
			if (res.next()) {
				// make a new movie
				 ac = new Accounts();
				ac.setAccountBalance(res.getDouble("account_balance"));
				ac.setAccountType(res.getString("account_name"));
				ac.setAccountId(res.getInt("accountid"));
				ac.setAccountNumber(res.getInt("account_number"));;
				return ac;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				BankUserLauncher.e720Logger.debug("Fined account failed");
			}finally {
				// if we actually had a pool of connections, we would do this
				cf.releaseConnection(conn);
			}
return ac;
	}

	public List<Accounts> findAllAccounts(int customerID) {
		Connection conn = this.cf.getConnection();
		List<Accounts> all = new ArrayList<Accounts>();

		try {
			String sql = "select * from account where account_user=?;";// we know this is a view for a gross sql query
			// we only use statements for very basic sql queries
			PreparedStatement getUser=conn.prepareStatement(sql);
			getUser.setInt(1, customerID);
			ResultSet res=getUser.executeQuery();

			while (res.next()) {
				// make a new movie
				Accounts a = new Accounts();
				a.setAccountNumber(res.getInt("account_number"));
				a.setAccountBalance(res.getDouble("account_balance"));
				a.setAccountType(res.getString("account_name"));
				all.add(a);
			}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				BankUserLauncher.e720Logger.debug("Operation select all accounts failed");
			}finally {
				// if we actually had a pool of connections, we would do this
				cf.releaseConnection(conn);
			}
		return all;
	}

	public Accounts createAccount(Accounts a,int userId) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);	
			String accountSQL="insert into account (account_name,account_number, account_balance, account_user) values (?,?,?,?) returning accountid;";
			PreparedStatement insertAccount = conn.prepareStatement(accountSQL);
			insertAccount.setString(1, a.getAccountType());
			insertAccount.setInt(2, a.getAccountNumber());
			insertAccount.setDouble(3, a.getAccountBalance());
			insertAccount.setInt(4, userId);
			
			ResultSet res = insertAccount.executeQuery();
			int newId;
			if (res.next()) {
				newId = res.getInt("accountid");
			} else {
				throw new SQLException();
			}
			a.setAccountId(newId);
		} catch (SQLException e) {
			e.printStackTrace();
			BankUserLauncher.e720Logger.debug("Operation create account failed");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}finally {
				try {
					conn.commit();
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cf.releaseConnection(conn);
			}
		/*if(a!=null) {
			System.out.println("Object build");
			System.out.println(a.display());
		}*/
		return a;
	}
	public Accounts updateAccount(Accounts a, double amount) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(true);
			String sql="update account set account_balance= ? where account_number= ?;";
			PreparedStatement updateAccount=conn.prepareStatement(sql);
			updateAccount.setDouble(1,amount);
			updateAccount.setInt(2, a.getAccountNumber());
			updateAccount.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			BankUserLauncher.e720Logger.debug("Operation UPDATE account failed");
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			
			cf.releaseConnection(conn);
		}
		return a;
	}
	public int getAccountById(int accountId) {
		Connection conn = this.cf.getConnection();
		int accountNumber=0;
		String sql="select account_number from account where accountid=?;";
		try {
			PreparedStatement getAccount=conn.prepareStatement(sql);
		getAccount.setInt(1,accountId);
		ResultSet res=getAccount.executeQuery();
		if(res.next()) {
			accountNumber=res.getInt("account_number");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountNumber;
	}
}

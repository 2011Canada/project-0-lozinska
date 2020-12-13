package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.AccountDoesNotExistException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.models.Accounts;
import com.revature.models.BankCustomer;
import com.revature.models.BankUser;
import com.revature.util.ConnectionFactory;

public class BAccountPostgresDAO implements BAccountDAO{
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	@Override
	public Accounts creteAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Accounts findOneAccount(int accountNumber, int customerID) throws AccountDoesNotExistException {
		
		Connection conn = this.cf.getConnection();

		try {
			String sql = "select * from account where (account_number=? and account_user=?);";// we know this is a view for a gross sql query
			// we only use statements for very basic sql queries
			PreparedStatement getAccount=conn.prepareStatement(sql);
			getAccount.setInt(1, accountNumber);
			getAccount.setInt(2, customerID);
			ResultSet res=getAccount.executeQuery();
			if (res.next()) {
				// make a new movie
				Accounts a = new Accounts();
				a.setAccountBalance(res.getDouble("account_balance"));
				a.setAccountType(res.getString("account_name"));
				return a;
			}		
			else {
				System.out.println("Im here");
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				// if we actually had a pool of connections, we would do this
				cf.releaseConnection(conn);
			}
throw new AccountDoesNotExistException();		
	}

	@Override
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
			}finally {
				// if we actually had a pool of connections, we would do this
				cf.releaseConnection(conn);
			}
		return all;


	
	}
}

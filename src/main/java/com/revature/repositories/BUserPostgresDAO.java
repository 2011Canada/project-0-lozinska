package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.jdbc.PgArray;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.NoUserFindException;
import com.revature.launcher.BankUserLauncher;
import com.revature.models.*;
import com.revature.util.ConnectionFactory;

public class BUserPostgresDAO implements BUserDAO{
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public BankUser createOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankUser findUser(String username, String password) throws NoUserFindException, SQLException, InternalErrorException{
	Connection conn=this.cf.getConnection();
	try {
	String sql="select * from bankuser where username=? and passwrd=?;";
	PreparedStatement getUser=conn.prepareStatement(sql);
	getUser.setString(1, username);
	getUser.setString(2, password);
	ResultSet res=getUser.executeQuery();
	if(res.next()) {
		BankUser u=new BankUser();
		u.setUsername(res.getString("username"));
		u.setPassword(res.getString("passwrd"));
		u.setRole(res.getString("user_role"));
		return u;
	}
	else {
		throw new NoUserFindException();
	}
	}catch(SQLException e) {
		e.printStackTrace();
		
	}
	throw new InternalErrorException();
	}

	@Override
	public List<BankUser> findAll() {
		Connection conn = this.cf.getConnection();
		List<BankUser> all = new ArrayList<BankUser>();

		try {
			String sql = "select * from all_bank_customers;";// we know this is a view for a gross sql query
			// we only use statements for very basic sql queries
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);

			while (res.next()) {
				// make a new movie
				BankCustomer m = new BankCustomer();
				m.setUsername(res.getString("username"));
				m.setPassword(res.getString("passwrd"));
				m.setRole(res.getString("user_role"));
				all.add(m);
			}
			// TODO
			// repeat for finding books as well

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if we actually had a pool of connections, we would do this
			cf.releaseConnection(conn);
		}
		//Entertainment720Launcher.e720Logger.info(all);
		return all;
	}

}

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

	public BankUser createOne(BankUser bu) {
	Connection conn=cf.getConnection();
	try {
	conn.setAutoCommit(false);
		String sql="insert into bankuser (username, passwrd,user_role) values(?,?,?) returning bankuserid;";
		PreparedStatement insertUser=conn.prepareStatement(sql);
		insertUser.setString(1, bu.getUsername());;
		insertUser.setString(2, bu.getPassword());;
		if(bu.getClass()==BankCustomer.class) {
			insertUser.setString(3, "customer");
		}
		if(bu.getClass()==BankEmployee.class) {
			insertUser.setString(3, "employee");
		}
		ResultSet res=insertUser.executeQuery();
		int newId;
		if(res.next()) {
			newId=res.getInt("bankuserid");
		}
		else {
			throw new SQLException();
		}
		bu.setUserId(newId);
		if(bu.getClass()==BankCustomer.class) {
			BankCustomer bc=(BankCustomer)bu;
			String custSql="insert into customer (main_user) values (?);";
			PreparedStatement insertCust=conn.prepareStatement(custSql);
			insertCust.setInt(1, newId);
			insertCust.executeUpdate();
		}
		if(bu.getClass()==BankEmployee.class) {
			BankEmployee be=(BankEmployee)bu;
			String emplSql="insert into employee (main_user) values (?);";
			PreparedStatement insertEmpl=conn.prepareStatement(emplSql);
			insertEmpl.setInt(1, newId);
			insertEmpl.executeUpdate();
		}
	}catch(SQLException e) {
		e.printStackTrace();
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} finally {
		try {
			//System.out.println("Commit");
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cf.releaseConnection(conn);
	}
	
	return bu;
	}

	public BankUser findUser(String username, String password) throws NoUserFindException, SQLException, InternalErrorException{
	Connection conn=this.cf.getConnection();
	BankUser u = null;
	try {
	String sql="select * from bankuser where username=? and passwrd=?;";
	PreparedStatement getUser=conn.prepareStatement(sql);
	getUser.setString(1, username);
	getUser.setString(2, password);
	ResultSet res=getUser.executeQuery();
	if(res.next()) {
		u=new BankUser();
		u.setUsername(res.getString("username"));
		u.setPassword(res.getString("passwrd"));
		u.setRole(res.getString("user_role"));
		u.setUserId(res.getInt("bankuserid"));
		return u;
	}
	else {
		throw new NoUserFindException();
	}
	}catch(SQLException e) {
		e.printStackTrace();
		
	}
	return u;
	}


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
				m.setUserId(res.getInt("bankuserid"));
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
	public int findCustomerByBankId(int id) {
		Connection conn = this.cf.getConnection();
		int custId=0;
		String sql="select customerid from customer where main_user=?;";
		try {
			PreparedStatement getCust=conn.prepareStatement(sql);
		getCust.setInt(1,id);
		ResultSet res=getCust.executeQuery();
		if(res.next()) {
			custId=res.getInt("customerid");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custId;
	}

}

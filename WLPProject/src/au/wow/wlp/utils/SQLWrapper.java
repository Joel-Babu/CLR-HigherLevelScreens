/**
 * This class connects to RC DB, executes the query 
 * and return results in List<List<Object>> 
 * 
 * To execute a query, call the below method
 * SQLWrapper sql = new SQLWrapper(log);
 * List<List<Object>> results = sql.executeQuery(getContext(), Query)
 */
package au.wow.wlp.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import au.wow.WLPmain.tests.StoreProperties;
//import au.wow.stores.utils.TestContext;

public class SQLWrapper extends StoreProperties{

	Connection con = null;
	Statement stmt = null;
	ResultSet rs;
	Logger log;
	
	
	public SQLWrapper(Logger log){
		this.log=log;
	}
	
	/** Oracle DB
	 * 
	 */
	
	public void connectToOracle(TestContext context){		
		String dbURL = context.getStringProperty("dbURL");
		String dbuserid = context.getStringProperty("dbuserid");
		String dbpwd = context.getStringProperty("dbpassword");
		try {
			System.out.println("pass");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbURL,dbuserid, dbpwd);
		
		} catch (ClassNotFoundException e) {
			Assert.fail("OracleDriver class is not loaded/found", e.fillInStackTrace());
		} catch (SQLException e) {
			Assert.fail("Error occurred in establishing a connection", e.fillInStackTrace());
		}
	}
	
	public void connectToCLRUAT(TestContext context) {	
		String dbURL = "jdbc:oracle:thin:@localhost:1521:WLPUA1";
		//String dbURL = "jdbc:oracle:thin:@ (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = TRA9-SCAN)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = WLP_UA_SRV)))";
		//String dbURL ="jdbc:oracle:thin:@TRA9-SCAN:1521:WLP_UA_SRV";
		String dbuserid = "WW_WLP";
		String dbpwd = "WOW44wlp" ;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(dbURL,dbuserid, dbpwd);
		System.out.println("Successfully connected to CLR UAT DB");
		} catch (ClassNotFoundException e) {
		Assert.fail("OracleDriver class is not loaded/found", e.fillInStackTrace());
		} catch (SQLException e) {
		Assert.fail("Error occurred in establishing a connection", e.fillInStackTrace());
		}
		}
	
	public void connectToKronos(TestContext context)
	{		
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = TRT3-SCAN)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = WFC_TS) ) )";
		String dbuserid = "TKCSOWNER";
		String dbpwd = "TKCSOWNER#12345";
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbURL,dbuserid, dbpwd);
		} catch (ClassNotFoundException e) {
			Assert.fail("OracleDriver class is not loaded/found", e.fillInStackTrace());
		} catch (SQLException e) {
			Assert.fail("Error occurred in establishing a connection", e.fillInStackTrace());
		}
	}
	
	 
	/**
	 * Establishes a connection to RC DB
	 * @param context
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void connectToRCDB(TestContext context) throws SQLException 
	{
		String uid1,pwd1,eng,database,host;
		if(System.getProperty("store")==null){
			uid1 = context.getStringProperty("uid1");
			pwd1 = context.getStringProperty("pwd1");
			eng = context.getStringProperty("eng");
			database = context.getStringProperty("database");
			host = context.getStringProperty("host");
		}else{
			uid1 = user;
			pwd1 = pwd;
			eng = eng1;
			database = database1;
			host = host1;
		}
		
		System.out.println("engine"+eng);
			try {
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				String dburl = "jdbc:sqlanywhere:uid="+ uid1 +";pwd="+pwd1+";eng="+eng+ ";database="+database+";links=tcpip(host="+host+")";
				con = DriverManager.getConnection(dburl);
			} catch (SQLException e) {
				Assert.fail("Error occurred in establishing a connection", e.fillInStackTrace());
			}
		}
	
	/**
	 * Creates a connection statement
	 */
	public void createStatement(){
		try {
			stmt=con.createStatement();
		} catch (SQLException e) {
			Assert.fail("Error in creating the statement", e);
		}
	}
	
	/**
	 * Establishes connection to RC DB, executes query, return results and closing the connection 
	 * @param context
	 * @param Query
	 * @return ResultSet
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<List<Object>> executeQuery(TestContext context, String Query) throws SQLException{
		connectToOracle(context);
		createStatement();
		executeQuery(Query); 
		List<List<Object>> results= getResults();
		closeConnection();
		return results;
	}

	
	/**
	 * Executes the query once connection is already established
	 * @param Query
	 */
	public void CLRConnectDB(TestContext context) throws SQLException{
		connectToOracle(context);
		createStatement();
	}
	
	public void CLRConnectDBUAT(TestContext context) throws SQLException{
		connectToCLRUAT(context);
		createStatement();
	}
	
	
	public void KronosConnectDB(TestContext context) throws SQLException{
		connectToKronos(context);
		createStatement();
	}
	
	public List<List<Object>> CLRexecuteQuery(TestContext context, String Query) throws SQLException
	{		
		
		executeQuery(Query); 
		List<List<Object>> results= getResults();
		return results;
	}
	
	public String CLRexecuteQueryw(TestContext context, String Query) throws SQLException
	{		
		executeQuery(Query); 
		String results= getResultsTwo();
		return results;
	}
	
	public void CLRCloseConnection() throws SQLException
	{
		System.out.println("Connection Closed Successfully");
		closeConnection();
	}
	
	public ResultSet executeQuery(String Query){
		try {
			
			rs=stmt.executeQuery(Query);
		} catch (SQLException e) {
			System.out.println(e);
			Assert.fail("Error in executing Query: " + Query, e.fillInStackTrace());
		}
		return rs;
	}
	
	
	/**
	 * Close the connection once the queries are executed
	 */
	public void closeConnection(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				Assert.fail("Error in closing the DB connection", e.fillInStackTrace());
			}
		}
	}
	
	/**
	 * Converts resultset object into List<List<Object>>
	 * @return List<List<Object>> of results
	 */
	public List<List<Object>> getResults(){
		List<List<Object>> results = new ArrayList<List<Object>>();
		if(rs!=null){
			try {
				int columnsize = rs.getMetaData().getColumnCount();
				while(rs.next()){
					List<Object> row = new ArrayList<>(columnsize);
					
					for(int i=1;i<=columnsize;i++){
						row.add(rs.getObject(i));
					}
					results.add(row);
				}
			} catch (SQLException e) {
				Assert.fail("Error in processing DB results", e);
			}
		}
		if(con!=null){
			try {
				//con.close();
				 System.out.println("Connection is ON");
			} catch (Exception e) {
				Assert.fail("Error in closing the connection", e.fillInStackTrace());
			}
		}
		
		return results;
	}
	
	public String getResultsTwo(){
		//String results = new ArrayList<List<Object>>();
		String Result = null;
		if(rs!=null){
			try {
				int columnsize = rs.getMetaData().getColumnCount();
				while(rs.next()){
					//List<Object> row = new ArrayList<>(columnsize);
					
					for(int i=1;i<=columnsize;i++){
						Result=(String) rs.getObject(i);
					}
					//results.add(row);
				}
			} catch (SQLException e) {
				Assert.fail("Error in processing DB results", e);
			}
		}
		if(con!=null){
			try {
				//con.close();
				 System.out.println("Connection is ON");
			} catch (Exception e) {
				Assert.fail("Error in closing the connection", e.fillInStackTrace());
			}
		}
		
		return Result;
	}
	
	public double getQueryResult(){
		double results = 0.0;
		if(rs!=null){
			try {
				int columnsize = rs.getMetaData().getColumnCount();
				while(rs.next()){
					List<Object> row = new ArrayList<>(columnsize);
					
					for(int i=1;i<=columnsize;i++){
						row.add(rs.getObject(i));
					}
				}
			} catch (SQLException e) {
				Assert.fail("Error in processing DB results", e);
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				Assert.fail("Error in closing the connection", e.fillInStackTrace());
			}
		}
		
		return results;
	}
	
}
	


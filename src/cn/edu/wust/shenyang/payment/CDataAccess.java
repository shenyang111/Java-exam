package cn.edu.wust.shenyang.payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CDataAccess {

	private List<Employer> employers = new ArrayList<Employer>();
	public static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    //MySQL JDBC驱动字符串

	private Statement statement;

	public CDataAccess() {
	   try
	    {
		   Class.forName(DRIVER_MYSQL);     //加载JDBC驱动
	       System.out.println("Driver Load Success.");

	       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payment","root","");    //创建数据库连接对象
	       statement = connection.createStatement();       //创建Statement对象
	     } catch (Exception e)
	     {
	    	 // TODO Auto-generated catch block
	         e.printStackTrace();
	     }
	}

	    /*
	     * 根据sql查询数据库，返回一个结果集
	     * 输    入:SQL语句
	     * 返回值:ResultSet 查询结果
	     */
	        
	public ResultSet query(String sql) {
		ResultSet result = null;

	    try
	    {
	    	result = statement.executeQuery(sql);
	    } catch (SQLException e)
	    {
	    	// TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return result;
	}

	    /*
	     * 打印UserInfo表的数据
	     * 输    入:结果集(数据表)
	     * 返回值:空
	     */
	public void printUserInfo(ResultSet result) {
		try
		{
			while(result.next()) {
			System.out.println("userNname:" + result.getString(1) 
					+ ", password:" + result.getString(2));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	    /*
	     * 执行数据操作
	     * 输    入:SQL语句
	     * 返回值:空
	     */
	public void executeSql(String sql) {
		try
		{
			statement.execute(sql);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletesql() {
		try
	    {
			statement.executeQuery("TRUNCATE TABLE UsersInfo");
	    } catch (SQLException e)
	    {
	    	// TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public void save() {
		deletesql();
		for (Employer employer : employers) {
			executeSql("INSERT INTO UserInfo VALUES("+employer.no+", "+employer.name+", 'Saler', 10000)");
		}
	}
	
	public void load() {
		String sql = "SELECT * FROM UserInfo";
		
		ResultSet result = query(sql);
		printUserInfo(result);
	}
}

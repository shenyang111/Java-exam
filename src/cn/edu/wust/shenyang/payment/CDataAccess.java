package cn.edu.wust.shenyang.payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CDataAccess {

	private List<Employer> employers = new ArrayList<Employer>();
	public static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    //MySQL JDBC�����ַ���

	private Statement statement;

	public CDataAccess() {
	   try
	    {
		   Class.forName(DRIVER_MYSQL);     //����JDBC����
	       System.out.println("Driver Load Success.");

	       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payment","root","");    //�������ݿ����Ӷ���
	       statement = connection.createStatement();       //����Statement����
	     } catch (Exception e)
	     {
	    	 // TODO Auto-generated catch block
	         e.printStackTrace();
	     }
	}

	    /*
	     * ����sql��ѯ���ݿ⣬����һ�������
	     * ��    ��:SQL���
	     * ����ֵ:ResultSet ��ѯ���
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
	     * ��ӡUserInfo�������
	     * ��    ��:�����(���ݱ�)
	     * ����ֵ:��
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
	     * ִ�����ݲ���
	     * ��    ��:SQL���
	     * ����ֵ:��
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

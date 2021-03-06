package cn.edu.wust.shenyang.payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CDataAccess {

    private List<Employer> employers = new ArrayList<Employer>();
    public static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";

    private Statement statement;

    public CDataAccess() {
        try
        {
            Class.forName(DRIVER_MYSQL);
            System.out.println("Driver Load Success.");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Payment","root","");
            statement = connection.createStatement();
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
            statement.executeQuery("TRUNCATE TABLE UserInfo");
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void save(List<Employer> employers) {
        deletesql();
        for (Employer employer : employers) {
            double workload = 0;
            if (employer.type == EmployerType.techer) {
                workload = ((Techer)employer).getSalaryHours();
            } else if (employer.type == EmployerType.saler) {
                workload = ((Saler)employer).getSaleNum();
            }

            executeSql("INSERT INTO UserInfo VALUES('" + employer.no + "', '" + employer.name + "', '" +
                    employer.type.toString() + "', '" + workload + "')");
        }
    }

    public void load(CEmployerSet employerSet) {
        String sql = "SELECT * FROM UserInfo";

        ResultSet result = query(sql);
        //printUserInfo(result);

        try {
            while(result.next()) {
                System.out.println("userNname:" + result.getString(1)
                        + ", password:" + result.getString(2));
                long no = result.getLong(1);
                String name = result.getString(2);
                String type_str = result.getString(3);
                double workload = result.getDouble(4);

                EmployerType type = null;
                if (type_str.equals("manager")) {
                    type = EmployerType.manager;
                } else if (type_str.equals("techer")) {
                    type = EmployerType.techer;
                } else if (type_str.equals("saler")) {
                    type = EmployerType.saler;
                } else {
                    throw new RuntimeException("unknown type [" + type_str + "]");
                }

                employerSet.addNewEmployer(no, type, name, workload);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
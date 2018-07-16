package info.inetsolv;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplateDemo {

	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("db.properties"));
		String driverClass=properties.getProperty("DriverClass");
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;

		Class.forName(driverClass);
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement=connection.createStatement();
			String qry="SELECT * FROM emp_tbl";
			resultSet = statement.executeQuery(qry);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(resultSet.next())
			{
				for(int i=1;i<=columnCount;i++){
					System.out.print(resultSet.getString(i)+" ");
				}
				System.out.println();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			statement.close();
			resultSet.close();
			connection.close();
		}
		
	}
}
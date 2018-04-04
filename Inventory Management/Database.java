import java.sql.*;

class Database{
	private final String DB_URL="jdbc:mysql://localhost/ims"; //datatbase url
	private final String USER="root";//user
	private final String PASSWORD=null;//pass
	private final String JDBC_DRIVER="com.mysql.jdbc.Driver";//driver for mysql;
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	private boolean success=false;
	
	public Database(){
		try{
			Class.forName(JDBC_DRIVER);//registering driver
			System.out.println("Establishing connection to database...");
			connect=DriverManager.getConnection(DB_URL,USER,PASSWORD);
			System.out.println("Connected to Database.");
		}catch(SQLException se){//if any error regarding  DriverManager
			se.printStackTrace();
		}catch(Exception e){//if any error regarding Class.forName
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String sql){
		try{
			statement=connect.createStatement();
			System.out.println("Query in progress...");
			result=statement.executeQuery(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
		return result;
	}
	
	public boolean update(String sql){
		int flag=-1;
		try{
			statement=connect.createStatement();
			System.out.println("Update in progress...");
			flag=statement.executeUpdate(sql);
		}catch(SQLException se){
			se.printStackTrace();
		}
		if(flag>-1)
			return true;
		else
			return false;
	}
	
	public void close(){
		try{
				if(statement!=null)
					statement.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(connect!=null)
					connect.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
	}
	
}
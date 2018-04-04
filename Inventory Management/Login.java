import java.util.Scanner;
import java.io.Console;
import java.sql.ResultSet;

final class Login{
	private static String user="";
	private static String password="";
	private static boolean login=false;
	private static char passwd[];//readPassword() method returns char[]
	private static void setUser(String username){user=username;}
	private static void setPass(String pass){password=pass;}
	private static void setLogin(boolean bool){login=bool;}
	private static String getPass(){return password;}
	
	
	public static boolean getLogin(){return login;}
	public static String getUser(){
		if(login==true){
			return user;
		}
		else{
			return "Guest";
		}
	}
	
	public static void authenticate() throws Exception{
		Scanner scan=new Scanner(System.in);
		Database data=new Database();
		Console console=System.console();
		String usr=new String();
		
		Logo.logo();
		System.out.println("Note:Password will be invisible while typing.");
		System.out.println("Username: ");
		usr=scan.nextLine();
		System.out.println("Password: ");
		passwd=console.readPassword();
		String pass=new String(passwd);
		
		ResultSet rs=data.query("SELECT * FROM users WHERE USERNAME='"+usr+"' AND PASSWORD='"+pass+"'");
		rs.last();
		if(rs.getRow()>0){
			rs.beforeFirst();
			while(rs.next()==true){
				setUser(rs.getString("USERNAME"));
				setPass(rs.getString("PASSWORD"));
			}
			setLogin(true);
		}
		else{
			System.out.println("Wrong username or password!");
		}
		data.close();
	}
	
	public static void logout(){login=false;}
	
	public static void changeUsrPass() throws Exception{
		Scanner scan=new Scanner(System.in);
		Database data=new Database();
		Console console=System.console();
		System.out.println("Enter current Password:");
		passwd=console.readPassword();
		String curPass=new String(passwd);
		if(curPass.equals(getPass())==true){
			System.out.print("Enter new Username:");
			String nUser=scan.nextLine();
			System.out.print("Enter new Password:");
			passwd=console.readPassword();
			String nPass=new String(passwd);
			System.out.println("Confirm new Password:");
			passwd=console.readPassword();
			String cPass=new String(passwd);
			if(cPass.equals(nPass)==true){
				data.update("UPDATE users SET username='"+nUser+"',password='"+cPass+"' WHERE id=1");
				System.out.println("Credentials updated!");
			}
			else{
				System.out.println("Passwords mismatched! Please try again.");
			}
		}
		else{
			System.out.println("Wrong password!");
		}
		data.close();
	}	
	
}
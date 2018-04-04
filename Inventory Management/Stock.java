import java.sql.ResultSet;

class Stock implements iStock{
	public void stockReports() throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM item");
		System.out.println("   ID        NAME        PRICE/Unit      QTY");
		System.out.println("------------------------------------------------");
		while(rs.next()==true){
			System.out.println("  "+rs.getInt("ID")+"      "+rs.getString("NAME")+"        "+rs.getFloat("PRICE")+"       "+rs.getInt("QTY"));
			
		}
		data.close();
	}
	
	public void searchStock(String search) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM item WHERE NAME='"+search+"'");
		rs.last();
		if(rs.getRow()<1){
			System.out.println("Searched item does not exist!");
		}
		else{
			rs.beforeFirst();
			System.out.println("   ID        NAME        PRICE/Unit      QTY");
			System.out.println("------------------------------------------------");
			while(rs.next()==true){
				System.out.println("  "+rs.getInt("ID")+"      "+rs.getString("NAME")+"        "+rs.getFloat("PRICE")+"       "+rs.getInt("QTY"));
			}
		}
		data.close();	
	}
}
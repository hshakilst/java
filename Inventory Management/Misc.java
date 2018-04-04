import java.sql.ResultSet;

class Misc{
	public void reOrder() throws Exception{
		Database data=new Database();
		boolean flag=false;
		ResultSet rs=data.query("SELECT * FROM item");
		rs.last();
		if(rs.getRow()>0){
			rs.beforeFirst();
			System.out.println("Reorder:");
			while(rs.next()==true){
				if(rs.getInt("QTY")<4){
					System.out.println(rs.getString("NAME")+"'s are only "+rs.getInt("QTY")+" units left!");
					flag=true;
				}
				else{
					continue;
				}
			}
			if(flag==false){System.out.println("Every product's quantity is greater than 4 units.");}
		}
		else{
			System.out.println("No item exists!");
		}
	}
	
	public void supplierSearch(String supplier) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM purchase WHERE supplier='"+supplier+"'");
		rs.last();
		if(rs.getRow()>0){
			rs.beforeFirst();
			System.out.println(" ID     PRODUCT    QTY      STATUS      PRICE");
			System.out.println("-----------------------------------------------");
			float total=0f;
			while(rs.next()==true){
				System.out.println(" "+rs.getInt("ID")+"     "+rs.getString("PRODUCT")+"    "+rs.getInt("QTY")+"      "+rs.getString("STATUS")+"      "+rs.getFloat("PRICE"));
				total=total+rs.getFloat("PRICE");
			}
			System.out.println("-----------------------------------------------");
			System.out.println("                                 Total="+total+"(TK)");
		}
		else{
			System.out.println("Supplier does not exists!");
		}
	}
	
	public void createSupplier(String supplier) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM supplier WHERE name='"+supplier+"'");
		rs.last();
		if(rs.getRow()>0){
			System.out.println("Supplier already exists!");
		}
		else{
			data.update("INSERT INTO supplier(name) VALUES ('"+supplier+"')");
		}
		data.close();
	}
	
	public void showSupplier() throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM supplier");
		System.out.println("   ID        NAME");
		System.out.println("-----------------------");
		while(rs.next()==true){
			System.out.println("  "+rs.getInt("ID")+"      "+rs.getString("NAME"));
			
		}
		data.close();
	}
	
	public void overview() throws Exception{
		float sales=0f;
		float purchases=0f;
		Database data=new Database();
		ResultSet rs=data.query("SELECT SUM(price) AS \"Total Price\" FROM sales");
		while(rs.next()==true){
			sales=rs.getFloat("Total Price");
		}
		rs=data.query("SELECT SUM(price) AS \"Total Price\" FROM purchase");
		while(rs.next()==true){
			purchases=rs.getFloat("Total Price");
		}
		System.out.println("\nTotal Sales = "+sales+"\tTotal Purchases = "+purchases);
	}
}
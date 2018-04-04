import java.sql.ResultSet;

class Item{
	private String name;
	private float price;
	private int qty;
	
	public Item(){
		name=null;
		price=0f;
		qty=0;
	}
	
	public void setName(String name){this.name=name;}
	public void setPrice(float price){this.price=price;}
	public void setQty(int qty){this.qty=qty;}
	
	public String getName(){return name;}
	public float getPrice(){return price;}
	public int getQty(){return qty;}
	
	public void insertItem() throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM item WHERE name='"+getName()+"'");
		rs.last();
		if(rs.getRow()>0){
			System.out.println("Item already exists!");
		}
		else{
			data.update("INSERT INTO item(name,price,qty) VALUES ('"+getName()+"',"+getPrice()+","+getQty()+")");
		}
		data.close();
	}
	
	public void updatePrice(String name,float value) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM item WHERE name='"+name+"'");
		rs.last();
		if(rs.getRow()>0){
			data.update("UPDATE item SET price="+value+" WHERE name='"+name+"'");
		}
		else{
			System.out.println("Entered Item does not exist!");
		}
		data.close();
	}
	
	public void updateQty(String name,int value) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT qty FROM item WHERE name='"+name+"'");
		rs.last();
		if(rs.getRow()>0){
			rs.beforeFirst();
			while(rs.next()==true){
				value=value+rs.getInt("QTY");
			}
			data.update("UPDATE item SET qty="+value+" WHERE name='"+name+"'");
		}
		else{
			System.out.println("Entered Item does not exists!");
		}
		data.close();
	}
	
	public void showItem() throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM item");
		System.out.println("   ID        NAME        PRICE/Unit      QTY");
		System.out.println("------------------------------------------------");
		while(rs.next()==true){
			System.out.println("  "+rs.getInt("ID")+"      "+rs.getString("NAME")+"        "+rs.getFloat("PRICE")+"       "+rs.getInt("QTY"));
			
		}
		data.close();
	}
	
	public void deleteItem(String name)throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM item WHERE name='"+name+"'");
		rs.last();
		if(rs.getRow()>0){
			data.update("DELETE FROM item WHERE name='"+name+"'");
		}
		else{
			System.out.println("Entered Item does not exist!");
		}
	}
}
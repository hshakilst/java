import java.sql.ResultSet;

class Sales{
	private String pName,cName;
	private float price,p;
	private int qty,q;
	
	public Sales(){
		pName=null;
		cName=null;
		price=0f;
		qty=0;
	}
	
	public void setPname(String pName){this.pName=pName;}
	public String getPname(){return pName;}
	public void setCname(String cName){this.cName=cName;}
	public String getCname(){return cName;}
	public void setPrice(float price){this.price=price;}
	public float getPrice(){return price;}
	public void setQty(int qty){this.qty=qty;}
	public int getQty(){return qty;}
	
	public void salesEntry() throws Exception{ 
		Database data=new Database();
		ResultSet rs=data.query("SELECT name,price,qty FROM item WHERE name='"+getPname()+"'");
		rs.last();
		if(rs.getRow()<1){
			System.out.println("Product name does not exist!");
		}
		else{
			rs.beforeFirst();
			while(rs.next()==true){
				q=rs.getInt("QTY");
				p=rs.getFloat("PRICE");
			}
			if(getQty()>q){
				System.out.println("Only "+q+" units are available.");
			}
			else{
				setPrice(p*getQty());
				data.update("UPDATE item SET qty="+(q-getQty())+" WHERE name='"+getPname()+"'");
				data.update("INSERT INTO sales (p_name,c_name,qty,price) VALUES ('"+getPname()+"','"+getCname()+"',"+getQty()+","+getPrice()+")");
			}
		}
		data.close();
	}
	
	public void salesReturn(String id) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT p_name,qty FROM sales WHERE id="+id);
		rs.last();
		if(rs.getRow()<1){
			System.out.println("Invalid sales ID.");
		}
		else{
			rs.beforeFirst();
			while(rs.next()==true){
				setPname(rs.getString("P_NAME"));
				setQty(rs.getInt("QTY"));
			}
			data.update("DELETE FROM sales WHERE id="+id);
			rs=data.query("SELECT qty FROM item WHERE name='"+getPname()+"'");
			while(rs.next()){
				q=rs.getInt("QTY");
			}
			data.update("UPDATE item SET qty="+(q+getQty())+" WHERE name='"+getPname()+"'");
		}
		data.close();
	}
	
	public void salesReport() throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM sales");
		rs.last();
		if(rs.getRow()>0){
			rs.beforeFirst();
			System.out.println("  ID      PRODUCT      CUSTOMER      QUANTITY      PRICE");
			System.out.println("----------------------------------------------------------");
			while(rs.next()){
				System.out.println(" "+rs.getInt("ID")+"     "+rs.getString("P_NAME")+"      "+rs.getString("C_NAME")+"        "+rs.getInt("QTY")+"         "+rs.getFloat("PRICE"));
			}
		}
		else{
			System.out.println("No entries found!");
		}
		data.close();
	}
}
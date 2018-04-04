import java.sql.ResultSet;

class Purchase implements iPurchase{
	private String supplier,product,status;
	private int qty;
	private float price;
	
	public Purchase(){
		supplier=null;
		product=null;
		status=null;
		qty=0;
		price=0f;
	}
	
	public void setSupplier(String supplier){this.supplier=supplier;}
	public void setProduct(String product){this.product=product;}
	public void setStatus(String status){this.status=status;}
	public void setQty(int qty){this.qty=qty;}
	public void setPrice(float price){this.price=price;}
	
	public String getSupplier(){return supplier;}
	public String getProduct(){return product;}
	public String getStatus(){return status;}
	public int getQty(){return qty;}
	public float getPrice(){return price;}
	
	public void purchaseEntry() throws Exception{
		Database data=new Database();
		if(getStatus().equals("received")){
			ResultSet rs=data.query("SELECT * FROM item WHERE name='"+getProduct()+"'");
			ResultSet rs1=data.query("SELECT * FROM supplier WHERE name='"+getSupplier()+"'");
			rs.last();
			if(rs.getRow()>0 && rs1.getRow()>0){
				rs.beforeFirst();
				while(rs.next()==true){
					data.update("UPDATE item SET qty="+(getQty()+rs.getInt("QTY"))+" WHERE name='"+getProduct()+"'");
				}
				data.update("INSERT INTO purchase(supplier,product,qty,status,price) VALUES ('"+getSupplier()+"','"+getProduct()+"',"+getQty()+",'"+getStatus()+"',"+getPrice()+")");
			}
			else{
				System.out.println("Product or Supplier is not recognized! Create them first!");
			}
		}
		else if(getStatus().equals("not received")){
			ResultSet rs=data.query("SELECT * FROM item WHERE name='"+getProduct()+"'");
			rs.last();
			if(rs.getRow()>0){
				data.update("INSERT INTO purchase(supplier,product,qty,status,price) VALUES ('"+getSupplier()+"','"+getProduct()+"',"+getQty()+",'"+getStatus()+"',"+getPrice()+")");
			}
			else{
				System.out.println("Product is not recognized! Create the item first!");				
			}
		}
		else{
			System.out.println("Invalid status! Please type \"received\" or \"not received\" only.");
		}
		data.close();
	}
	
	public void purchaseReturn(String id) throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT product,qty,status FROM purchase WHERE id="+id);
		rs.last();
		if(rs.getRow()<1){
			System.out.println("Invalid Purchase ID.");
		}
		else{
			rs.beforeFirst();
			while(rs.next()==true){
				setProduct(rs.getString("PRODUCT"));
				setQty(rs.getInt("QTY"));
				setStatus(rs.getString("STATUS"));
			}
			if(getStatus().equals("received")){
				rs=data.query("SELECT qty FROM item WHERE name='"+getProduct()+"'");
				while(rs.next()){
					data.update("UPDATE item SET qty="+(rs.getInt("QTY")-getQty())+" WHERE name='"+getProduct()+"'");
					}
				data.update("DELETE FROM purchase WHERE id="+id);
			}
			else{
				data.update("DELETE FROM purchase WHERE id="+id);
			}
		}
		data.close();
	}
	
	public void purchaseReport() throws Exception{
		Database data=new Database();
		ResultSet rs=data.query("SELECT * FROM purchase");
		rs.last();
		if(rs.getRow()>0){
			rs.beforeFirst();
			System.out.println(" ID    SUPPLIER     PRODUCT    QTY      STATUS      PRICE");
			System.out.println("-----------------------------------------------------------");
			while(rs.next()){
				System.out.println(" "+rs.getInt("ID")+"    "+rs.getString("SUPPLIER")+"     "+rs.getString("PRODUCT")+"    "+rs.getInt("QTY")+"      "+rs.getString("STATUS")+"      "+rs.getFloat("PRICE"));
			}
		}
		else{
			System.out.println("No entries found!");
		}
		data.close();
	}
}
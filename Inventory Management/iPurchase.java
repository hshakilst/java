interface iPurchase{
	public void setSupplier(String supplier);
	public void setProduct(String product);
	public void setStatus(String status);
	public void setQty(int qty);
	public void setPrice(float price);
	
	public String getSupplier();
	public String getProduct();
	public String getStatus();
	public int getQty();
	public float getPrice();
	
	public void purchaseEntry()throws Exception;
	public void purchaseReturn(String id)throws Exception;
	public void purchaseReport()throws Exception;
}
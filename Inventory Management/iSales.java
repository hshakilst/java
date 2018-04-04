interface iSales{
	public void setPname(String pName);
	public String getPname();
	public void setCname(String cName);
	public String getCname();
	public void setPrice(float price);
	public float getPrice();
	public void setQty(int qty);
	public int getQty();
	
	public void salesEntry() throws Exception;
	public void salesReturn(String id) throws Exception;
	public void salesReport() throws Exception;
}
import java.util.Scanner;

class UI{
	 public void start() throws Exception{
		 Logo.logo();
		 System.out.println("Welcome "+Login.getUser()+"!"+System.lineSeparator());
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Sales");
		 System.out.println("           2. Purchase");
		 System.out.println("           3. Stock");
		 System.out.println("           4. Settings");
		 if(Login.getLogin()==true){
			 System.out.println("           5. Log Out");
		 }
		 else{
			 System.out.println("           5. Log In");
		 }
		  System.out.println("           6. Inventory Overview");
		 System.out.println("           7. Exit");
		 System.out.print("IMS>");
			
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine(); //string for stopping  type mismatch problem
		 
		 switch(c){
			 case "1":
				sales();
				break;
			 case "2":
				purchase();
				break;
			 case "3":
				stock();
				break;
			 case "4":
				settings();
				break;
			 case "5":
				if(Login.getLogin()==true){
					Login.logout();
				}
				else{
					Login.authenticate();
				}
				start();
				break;
			case "6":
				if(Login.getLogin()==true){
					Misc misc=new Misc();
					misc.overview();
				}
				else{
					System.out.println("You must login first!");
				}
				start();
				break;
			case "7":
				System.exit(0);
				break;
			 default:
				System.out.println("Invalid Selection!");
				start();
				break;
		}
		 
		 
	 }
	 
	 private void sales() throws Exception{
		 Logo.logo();
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Sales Bill");
		 System.out.println("           2. Sales Return");
		 System.out.println("           3. Reports");
		 System.out.println("           4. Main Menu");
		 System.out.print("IMS>");
		 
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine();
		 iSales sale=new Sales();//plymorphism
		 
		 switch(c){
			 case "1":
				if(Login.getLogin()==true){	
					scan=new Scanner(System.in);
					System.out.print("Enter product name: ");
					String pName=scan.nextLine();
					System.out.print("Enter customer name: ");
					String cName=scan.nextLine();
					System.out.print("Enter quantity: ");
					int qty=scan.nextInt();
					sale.setPname(pName);
					sale.setCname(cName);
					sale.setQty(qty);
					sale.salesEntry();
				}
				else{
					System.out.println("You must login first!");
				}
				sales();
				break;
			 case "2":
				if(Login.getLogin()==true){
					scan=new Scanner(System.in);
					System.out.println("Enter sales ID: ");
					String sId=scan.nextLine();
					sale.salesReturn(sId);
				}
				else{
					System.out.println("You must login first!");
				}
				sales();
				break;
			 case "3":
				if(Login.getLogin()==true){
					System.out.println("Generating sales reports...");
					sale.salesReport();
				}
				else{
					System.out.println("You must login first!");
				}
				sales();
				break;
			 case "4":
				 start();
				break;
			 default:
				System.out.println("Invalid Selection!");
				sales();
				break;
		 }
		 
	 }
	 
	 private void purchase() throws Exception{
		 Logo.logo();
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Purchase Entry");
		 System.out.println("           2. Purchase Return");
		 System.out.println("           3. Reports");
		 System.out.println("           4. Main Menu");
		 System.out.print("IMS>");
		 
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine();
		 iPurchase purchase=new Purchase();//polymorphism
		 String supplier,status,product,id;
		 int qty;
		 float price;
		 
		 switch(c){
			 case "1":
				if(Login.getLogin()==true){	
					scan=new Scanner(System.in);
					System.out.print("Enter Product Name: ");
					product=scan.nextLine();
					System.out.print("Enter Quantity: ");
					qty=Integer.parseInt(scan.nextLine());
					System.out.print("Enter Status: ");
					status=scan.nextLine();
					System.out.print("Enter Supplier Name: ");
					supplier=scan.nextLine();
					System.out.print("Enter Total Price: ");
					price=Float.parseFloat(scan.nextLine());
					purchase.setProduct(product);
					purchase.setQty(qty);
					purchase.setStatus(status);
					purchase.setSupplier(supplier);
					purchase.setPrice(price);
					purchase.purchaseEntry();
				}
				else{
					System.out.println("You must login first!");
				}
				purchase();
				break;
			 case "2":
				if(Login.getLogin()==true){
					scan=new Scanner(System.in);
					System.out.println("Enter Purchase ID: ");
					id=scan.nextLine();
					purchase.purchaseReturn(id);
				}
				else{
					System.out.println("You must login first!");
				}
				purchase();
				break;
			 case "3":
				if(Login.getLogin()==true){
					System.out.println("Generating sales reports...");
					purchase.purchaseReport();
				}
				else{
					System.out.println("You must login first!");
				}
				purchase();
				break;
			 case "4":
				 start();
				break;
			 default:
				System.out.println("Invalid Selection!");
				purchase();
				break;
		 }
	 }
	 
	 private void stock() throws Exception{
		 Logo.logo();
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Search");
		 System.out.println("           2. Reports");
		 System.out.println("           3. Main Menu");
		 System.out.print("IMS>");
		 
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine();
		 iStock stock=new Stock();//plymorphism
		 
		 switch(c){
			 case "1":
				if(Login.getLogin()==true){	
					scan=new Scanner(System.in);
					System.out.print("Search a Product:");
					String search=scan.nextLine();
					stock.searchStock(search);
				}
				else{
					System.out.println("You must login first!");
				}
				stock();
				break;
			 case "2":
				if(Login.getLogin()==true){
					stock.stockReports();
				}
				else{
					System.out.println("You must login first!");
				}
				stock();
				break;
			 case "3":
				start();
				break;
			 default:
				System.out.println("Invalid Selection!");
				stock();
				break;
		 }
	 }
	 
	 private void settings() throws Exception{
		 Logo.logo();
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Supplier");
		 System.out.println("           2. Item");
		 System.out.println("           3. Re-order");
		 System.out.println("           4. Change Credentials");
		 System.out.println("           5. Main Menu");
		 System.out.print("IMS>");
		 
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine();
		 
		 switch(c){
			 case "1":
				supplier();
				break;
			 case "2":
				item();
				break;
			 case "3":
				if(Login.getLogin()==true){
					Misc misc=new Misc();
					misc.reOrder();
				}
				else{
					System.out.println("You must login first!");
				}
				settings();
				break;
			 case "4":
				if(Login.getLogin()==true){
					Login.changeUsrPass();
				}
				else{
					System.out.println("You must login first!");
				}
				settings();
				break;
			 case "5":
				 start();
				break;
			 default:
				System.out.println("Invalid Selection!");
				settings();
				break;
		 }
	 }
	 
	 public void item() throws Exception{
		 Logo.logo();
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Insert");
		 System.out.println("           2. Update Price");
		 System.out.println("           3. Update Quantity");
		 System.out.println("           4. Delete");
		 System.out.println("           5. Show Items");
		 System.out.println("           6. Main Menu");
		 System.out.print("IMS>");
		 
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine();
		 Item item=new Item();
		 String name;
		 float price;
		 int qty;
		 
		 switch(c){
			 case "1":
				if(Login.getLogin()==true){
					scan=new Scanner(System.in);
					System.out.print("Item Name:");
					name=scan.nextLine();
					item.setName(name);
					System.out.print("Price Per Unit:");
					price=scan.nextFloat();
					item.setPrice(price);
					System.out.print("Quantity:");
					qty=scan.nextInt();
					item.setQty(qty);
					item.insertItem();
				}
				else{
					System.out.println("You must login first!");
				}
				item();
				break;
			 case "2":
				if(Login.getLogin()==true){
					scan=new Scanner(System.in);
					System.out.print("Product Name:");
					name=scan.nextLine();
					System.out.print("Price Per Unit:");
					price=scan.nextFloat();
					item.updatePrice(name,price);
				}
				else{
					System.out.println("You must login first!");
				}
				item();
				break;
			 case "3":
				if(Login.getLogin()==true){
					scan=new Scanner(System.in);
					System.out.print("Product Name:");
					name=scan.nextLine();
					System.out.print("Quantity:");
					qty=scan.nextInt();
					item.updateQty(name,qty);
				}
				else{
					System.out.println("You must login first!");
				}
				item();
				break;
			 case "4":
				if(Login.getLogin()==true){
					scan=new Scanner(System.in);
					System.out.print("Product Name:");
					name=scan.nextLine();
					item.deleteItem(name);
				}
				else{
					System.out.println("You must login first!");
				}
				item();
				break;
			 case "5":
				if(Login.getLogin()==true){
					item.showItem();
				}
				else{
					System.out.println("You must login first!");
				}
				item();
				break;
			 case "6":
				start();
				break;
			 default:
				System.out.println("Invalid Selection!");
				item();
				break;
		 }
	 }
	 
	 public void supplier() throws Exception{
		  Logo.logo();
		 System.out.println("Please select an option from below:"+System.lineSeparator());
		 System.out.println("           1. Search");
		 System.out.println("           2. Create Supplier");
		 System.out.println("           3. Show Suppliers");
		 System.out.println("           4. Main Menu");
		 System.out.print("IMS>");
		 
		 Scanner scan=new Scanner(System.in);
		 String c=scan.nextLine();
		 Misc misc=new Misc();
		 
		 switch(c){
			case "1":
				if(Login.getLogin()==true){
					System.out.println("Enter a Supplier Name: ");
					String sName=scan.nextLine();
					misc.supplierSearch(sName);
				}
				else{
					System.out.println("You must login first!");
				}
				supplier();
				break;
			case "2":
				if(Login.getLogin()==true){
					System.out.println("Enter a Supplier Name: ");
					String sName=scan.nextLine();
					misc.createSupplier(sName);
				}
				else{
					System.out.println("You must login first!");
				}
				supplier();
				break;
			case "3":
				if(Login.getLogin()==true){
					misc.showSupplier();
				}
				else{
					System.out.println("You must login first!");
				}
				supplier();
				break;
			case "4":
				start();
				break;
			default:
				System.out.println("Invalid Selection!");
				supplier();
				break;
		 }
	 }
}
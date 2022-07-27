
public class Product {
	// This is the file for the Github Repository.
	private String productName;
	private java.util.Calendar saleDate;
	private double price;
	
	public Product(String sName, java.util.Calendar sDate, double price) throws Exception {
		setProductName(sName);
		setSaleDate(sDate);
		setPrice(price);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) throws Exception {
		if(productName.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			this.productName = productName;
		}
		
	}

	public java.util.Calendar getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(java.util.Calendar saleDate) {
		this.saleDate = saleDate;
	}

	public double getPrice() {
		return price;
	}
	// An exception is thrown if the required condition is not met
	public void setPrice(double price) throws Exception {
		if(price<0) {
			throw new Exception("The value must be positive");
		}else {
			this.price = price;	
		}
		
	}
	
	public String toString() {
		return "Product [productName="+this.getProductName()+", transactionDate="+getSaleDate().get(5)+"/"+(getSaleDate().get(2)+1)+"/"+getSaleDate().get(1)+", price="+this.getPrice()+"]";
	}
}	

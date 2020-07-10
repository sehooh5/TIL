package day14.copy;
import java.util.HashSet;
import java.util.Iterator;

class Product{
	private String productID;
	private String productName;
	private String productPrice;
	Product (String productID,String productName,String productPrice){
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		
	}
	
	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public boolean equals (Object o) {
		boolean result = false;
		if(o!=null&&o instanceof Product) {
			Product obj = (Product)o;
			if(productID.equals(obj.productID)) {
				result = true;
			}
		}
		return result;
	}
	// @Override 애너테이션 구문
	public int hashCode() {
		return productID.hashCode();
	}
	///to String 쓰기

	@Override
	public String toString() {
		return String.format(productID+"   "+productName+"    "+productPrice);
//return String.format("%-10s%-10s%-10s",productID, productName, productPrice);
	}
}

public class ProductTest {

	public static void main(String[] args) {
		Product[] p = new Product[4];

		p[0] = new Product("p100","TV","20000");
		p[1] = new Product("p200","Computer","10000");
		p[2] = new Product("p100","MP3","700");
		p[3] = new Product("p300","Audio","1000");
		
		HashSet<Product> hs = new HashSet<>();
		for(int i=0;i<4;i++){
			if (hs.add(p[i])) {
			System.out.println("성공적으로 저장되었습니다");
			}else
				System.out.println("동일한 ID의 제품이 이미 저장 되었습니다");
		}
		
		Iterator<Product> iter = hs.iterator();
		while(iter.hasNext()){
			Product pd = iter.next();			
			System.out.println(pd);
			
		}
		
	}

}



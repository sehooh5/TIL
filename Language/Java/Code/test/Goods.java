package test;

public class Goods {
	String prodId; 	// ��ǰ ���̵� 
	String prodName; // ��ǰ �̸� 
	int price; 	// ��ǰ ���� 
	char asYn; 	// AS ���� ���� 
	Goods(String prodId, String prodName, int price) {
		this.prodId = prodId;
		this.prodName = prodName;
		this.price = price;
	}
	Goods(String prodId, String prodName, int price, char asYn) {
		this.prodId = prodId;
		this.prodName = prodName;
		this.price = price;
		this.asYn = asYn;
	}
	public String getGoodsInfo() {
		String a;
		if (asYn=='N') {
			a = "��ǰ ID : "+prodId+", ��ǰ�� : "+prodName+", ���� : "+price+",AS���ɿ��� : �Ұ�";
		}else {
			a = "��ǰ ID : "+prodId+", ��ǰ�� : "+prodName+", ���� : "+price+",AS���ɿ��� : ����";
		}
		return a;
	}
	
	
	// getGoodsInfo() �޼��带 �����Ͻÿ�.
}

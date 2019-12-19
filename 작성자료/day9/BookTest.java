package day9;
class Book{
	String title;
	String author;
	private int price;				////*** �ܺο��� ���� ���ϰ� private ����
	Book(){

		this("�ڹ��� ����","���� ��",30000);
	}
	Book(String title,String author,int price){
		this.title = title;						
		this.author = author;
		setPrice(price);					//set Price �޼��带 ����Ͽ� ����ε� price ����
	}
	void setPrice(int price) {				//setter ���� --> ������ void
		if (price < 0)						//setter �����Ͽ� �߸��� �� �ɷ�����
			this.price = -price;
		else
			this.price = price;
	}
	String getBookInfo() {
		return "å�̸� : "+title+" "+author+" "+price;
	}
	void BookTest(){
		System.out.println(getBookInfo());
	}
}
public class BookTest {

	public static void main(String[] args) {
		Book info1 = new Book();
		info1.setPrice(-30000);
		
		System.out.println(info1.getBookInfo());

	}

}

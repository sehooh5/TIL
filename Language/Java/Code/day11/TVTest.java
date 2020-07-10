package day11;

class TV {
	private String model;
	private int size;
	private int channel;

	public TV() {
	}

	public TV(String model, int size, int channel) {
		this.model = model;
		this.size = size;
		this.channel = channel;
	}

	public int getChannel() {
		return channel;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public void channelUp() {
		if (getChannel() < 10) {
			setChannel(getChannel() + 1);
		} else {
			setChannel(1);
		}
	}

	public void channelDown() {
		if (getChannel() > 1) {
			setChannel(getChannel() - 1);
		} else {
			setChannel(10);
		}
	}
}

class SaleTV extends TV {
	private int price;

	public SaleTV() {
	}

	public SaleTV(int price, String model, int size, int channel) {
		super(model, size, channel);
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void play() {
		System.out.println("판매 TV 채널 " + getChannel() + " 번의 프로를 플레이합니다.");
	}

	public void sale() {
		System.out.println(getModel() + " 모델의 상품을 판매합니다." + price + "을 지불해 주세요");
	}

	public String toString() {
		return "판매상품정보 : 모델명(" + getModel() + "), 가격(" + String.format("%,d", price) + "원), 크기("
				+ "("+String.format("%d", getSize()+")");
	}
}



class RentalTV extends TV implements Rentable {
	private int price;

	public RentalTV() {
	}

	public RentalTV(int price, String model, int size, int channel) {
		super(model, size, channel);
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void play() {
		System.out.println("대여 TV 채널 " + getChannel() + " 번의 프로를 플레이합니다.");
	}

	public void rent() {
		System.out.println(getModel() + " 모델의 상품을 대여합니다." + price + "을 지불해 주세요");
	}

	public String toString() {
		return "대여상품정보 : 모델명(" + getModel() + "), 가격(" + String.format("%,d", price) + "원), 크기("
				+ String.format("%d", getSize() + ")");
	}
}
interface Rentable {
	void rent();
}

public class TVTest {

	public static void main(String[] args) {
		TV stv = new SaleTV(300000, "SALETV-1", 40, 1);
		TV rtv = new RentalTV(100000, "RENTALTV-10", 42, 1);
		for (int i = 0; i < 2; i++) {
			stv.channelUp();
		}
		for (int i = 0; i < 3; i++) {
			rtv.channelDown();
		}
		printAllTV(stv);
		printAllTV(rtv);
		printRentalTV((Rentable) rtv); //
		
	}
	static void printAllTV(TV tv) {
		System.out.println(tv.toString());	//
		if (tv instanceof SaleTV) {			//
			((SaleTV) tv).play();
			((SaleTV) tv).sale();
		} else {
			((RentalTV) tv).play();
		}
	}
	static void printRentalTV(Rentable tv) {
		((RentalTV)tv).rent();			//
	}
}
package day10; //getter setter 사용하는 이유와 방법 다시 공부해보기

abstract class Mobile {
	private String mobileName;
	private int batterySize;
	private String osType;

	public Mobile() {
	}

	public Mobile(String mobileName, int batterySize, String osType) {
		this.mobileName = mobileName;
		this.batterySize = batterySize;
		this.osType = osType;
	}

	abstract public void operate(int time);

	abstract public void charge(int time);

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

}

class Ltab extends Mobile {
	public Ltab() {
	}

	public Ltab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	public void operate(int time) {
		setBatterySize(getBatterySize() + time * -10);

	}

	public void charge(int time) {
		setBatterySize(getBatterySize() + time * 10);
	}
}

class Otab extends Mobile {
	public Otab() {
	}

	public Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}

	public void operate(int time) {
		setBatterySize(getBatterySize() + time * -12);
	}

	public void charge(int time) {
		setBatterySize(getBatterySize() + time * 8);
	}
}

public class MobileTest {

	public static void main(String[] args) {
		Mobile[] kind = new Mobile[2];
		kind[0] = new Ltab("Ltab", 500, "ABC-01");
		kind[1] = new Otab("Otab", 1000, "XYZ-20");

		MobileTest.printTitle();
		printMobile(kind[0]);
		printMobile(kind[1]);
		System.out.println("[10분 충전]");
		MobileTest.printTitle();
		kind[0].charge(10);
		kind[1].charge(10);
		printMobile(kind[0]);
		printMobile(kind[1]);
		System.out.println("[5분 통화]");
		MobileTest.printTitle();
		kind[0].operate(5);
		kind[1].operate(5);
		printMobile(kind[0]);
		printMobile(kind[1]);
	}

	public static void printMobile(Mobile mobile) {
		System.out.println(mobile.getMobileName() + "     " + mobile.getBatterySize() + "         " + mobile.getOsType());
	}

	public static void printTitle() {
		System.out.println("Mobile  Battery       OS");
		System.out.println("----------------------------");
	}
}
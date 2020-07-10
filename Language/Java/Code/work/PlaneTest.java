package work;

abstract class Plane {
	private String planeName;
	private int fuelSize;

	public Plane() {
	}

	public Plane(String planeName, int fuelSize) {
		this.planeName = planeName;
		this.fuelSize = fuelSize;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public int getFuelSize() {
		return fuelSize;
	}

	public void setFuelSize(int fuelSize) {
		this.fuelSize = fuelSize;
	}

	public void refuel(int fuel) {
		setFuelSize(getFuelSize() + fuel);
	}

	public abstract void flight(int distance);
}

class Airplane extends Plane {
	public Airplane() {
	}

	public Airplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}

	public void flight(int distance) {
		setFuelSize(getFuelSize() + distance * -3);
	}
}

class Cargoplane extends Plane {
	public Cargoplane() {
	}

	public Cargoplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}

	public void flight(int distance) {
		setFuelSize(getFuelSize() + distance * -5);
	}
}

public class PlaneTest {

	public static void main(String[] args) {

		Plane[] kind = new Plane[2];
		kind[0] = new Airplane("L747", 1000);
		kind[1] = new Cargoplane("C40", 1000);
		printInfo(kind);
		System.out.println("[100 운항]");
		kind[0].flight(100);
		kind[1].flight(100);
		printInfo(kind);
		System.out.println("[200 주유]");
		kind[0].refuel(200);
		kind[1].refuel(200);
		printInfo(kind);

	}

	public static void printInfo(Plane[] list) {
		int i = 0;
		System.out.println("Plane      fuelSize");
		System.out.println("--------------------");
		for (i = 0; i < 2; i++)
			System.out.println(list[i].getPlaneName() + "      " + list[i].getFuelSize());
	}
}

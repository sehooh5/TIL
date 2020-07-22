package vo;

public class livingPopulationVO {
	private int q_id;
	private int area_id;
	private int all_living_num;
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public int getAll_living_num() {
		return all_living_num;
	}
	public void setAll_living_num(int all_living_num) {
		this.all_living_num = all_living_num;
	}
	
	@Override
	public String toString() {
		return "livingPopulationVO [q_id=" + q_id + ", area_id=" + area_id + ", all_living_num=" + all_living_num + "]";
	}
	
	
}

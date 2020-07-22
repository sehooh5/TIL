package vo;

public class quaterVO {
	private int q_id;
	private int area_id;
	
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
	
	@Override
	public String toString() {
		return "quaterVO [q_id=" + q_id + ", area_id=" + area_id + "]";
	}
}

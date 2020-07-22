package vo;

public class apartmentVO {
	@Override
	public String toString() {
		return "apartmentVO [area_id=" + area_id + ", q_id=" + q_id + ", apart_num=" + apart_num + "]";
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public int getApart_num() {
		return apart_num;
	}
	public void setApart_num(int apart_num) {
		this.apart_num = apart_num;
	}
	private int area_id;
	private int q_id;
	private int apart_num;
	
	

}

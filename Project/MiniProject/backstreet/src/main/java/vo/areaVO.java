package vo;

public class areaVO {
	private int area_id;
	private String area_coname;
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getArea_coname() {
		return area_coname;
	}
	public void setArea_coname(String area_coname) {
		this.area_coname = area_coname;
	}
	
	@Override
	public String toString() {
		return "areaVO [area_id=" + area_id + ", area_coname=" + area_coname + "]";
	}
	
}

package vo;

public class areaScopeVO {
	private int area_id;
	private float x;
	private float y;
	private int gu_id;
	private String gu_name;
	
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getGu_id() {
		return gu_id;
	}
	public void setGu_id(int gu_id) {
		this.gu_id = gu_id;
	}
	public String getGu_name() {
		return gu_name;
	}
	public void setGu_name(String gu_name) {
		this.gu_name = gu_name;
	}
	@Override
	public String toString() {
		return "areaScopeVO [area_id=" + area_id + ", x=" + x + ", y=" + y + ", gu_id=" + gu_id + ", gu_name=" + gu_name
				+ "]";
	}

}

package vo;

public class changeVO {
	private int q_id;
	private int area_id;
	private int change_id;
	private String change_coname;
	private int oper_month;
	private int close_month;
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
	public int getChange_id() {
		return change_id;
	}
	public void setChange_id(int change_id) {
		this.change_id = change_id;
	}
	public String getChange_coname() {
		return change_coname;
	}
	public void setChange_coname(String change_coname) {
		this.change_coname = change_coname;
	}
	public int getOper_month() {
		return oper_month;
	}
	public void setOper_month(int oper_month) {
		this.oper_month = oper_month;
	}
	public int getClose_month() {
		return close_month;
	}
	public void setClose_month(int close_month) {
		this.close_month = close_month;
	}
	
	@Override
	public String toString() {
		return "changeVO [q_id=" + q_id + ", area_id=" + area_id + ", change_id=" + change_id + ", change_coname="
				+ change_coname + ", oper_month=" + oper_month + ", close_month=" + close_month + "]";
	}
	
}

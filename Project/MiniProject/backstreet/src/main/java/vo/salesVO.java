package vo;

public class salesVO {
	private int q_id;
	private int area_id;
	private String serv_id;
	// 공통 컬럼
	private long month_sal_money;

	@Override
	public String toString() {
		return "salesVO [q_id=" + q_id + ", area_id=" + area_id + ", serv_id=" + serv_id + ", month_sal_money="
				+ month_sal_money + ", month_sal_num=" + month_sal_num + ", wday_sal_money=" + wday_sal_money
				+ ", wkend_sal_money=" + wkend_sal_money + ", m_sal_money=" + m_sal_money + ", w_sal_money="
				+ w_sal_money + ", sal_money_10=" + sal_money_10 + ", sal_money_20=" + sal_money_20 + ", sal_money_30="
				+ sal_money_30 + ", sal_money_40=" + sal_money_40 + ", sal_money_50=" + sal_money_50 + ", sal_money_60="
				+ sal_money_60 + ", wday_sal_num=" + wday_sal_num + ", wkend_sal_num=" + wkend_sal_num + ", m_sal_num="
				+ m_sal_num + ", w_sal_num=" + w_sal_num + ", sal_num_10=" + sal_num_10 + ", sal_num_20=" + sal_num_20
				+ ", sal_num_30=" + sal_num_30 + ", sal_num_40=" + sal_num_40 + ", sal_num_50=" + sal_num_50
				+ ", sal_num_60=" + sal_num_60 + "]";
	}
	private int month_sal_num;
	private long wday_sal_money;
	private long wkend_sal_money;
	private long m_sal_money;
	private long w_sal_money;
	// sales_1
	
	private long sal_money_10;
	private long sal_money_20;
	private long sal_money_30;
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
	public String getServ_id() {
		return serv_id;
	}
	public void setServ_id(String serv_id) {
		this.serv_id = serv_id;
	}
	public long getMonth_sal_money() {
		return month_sal_money;
	}
	public void setMonth_sal_money(long month_sal_money) {
		this.month_sal_money = month_sal_money;
	}
	public int getMonth_sal_num() {
		return month_sal_num;
	}
	public void setMonth_sal_num(int month_sal_num) {
		this.month_sal_num = month_sal_num;
	}
	public long getWday_sal_money() {
		return wday_sal_money;
	}
	public void setWday_sal_money(long wday_sal_money) {
		this.wday_sal_money = wday_sal_money;
	}
	public long getWkend_sal_money() {
		return wkend_sal_money;
	}
	public void setWkend_sal_money(long wkend_sal_money) {
		this.wkend_sal_money = wkend_sal_money;
	}
	public long getM_sal_money() {
		return m_sal_money;
	}
	public void setM_sal_money(long m_sal_money) {
		this.m_sal_money = m_sal_money;
	}
	public long getW_sal_money() {
		return w_sal_money;
	}
	public void setW_sal_money(long w_sal_money) {
		this.w_sal_money = w_sal_money;
	}
	public long getSal_money_10() {
		return sal_money_10;
	}
	public void setSal_money_10(long sal_money_10) {
		this.sal_money_10 = sal_money_10;
	}
	public long getSal_money_20() {
		return sal_money_20;
	}
	public void setSal_money_20(long sal_money_20) {
		this.sal_money_20 = sal_money_20;
	}
	public long getSal_money_30() {
		return sal_money_30;
	}
	public void setSal_money_30(long sal_money_30) {
		this.sal_money_30 = sal_money_30;
	}
	public long getSal_money_40() {
		return sal_money_40;
	}
	public void setSal_money_40(long sal_money_40) {
		this.sal_money_40 = sal_money_40;
	}
	public long getSal_money_50() {
		return sal_money_50;
	}
	public void setSal_money_50(long sal_money_50) {
		this.sal_money_50 = sal_money_50;
	}
	public long getSal_money_60() {
		return sal_money_60;
	}
	public void setSal_money_60(long sal_money_60) {
		this.sal_money_60 = sal_money_60;
	}
	public long getWkend_sal_num() {
		return wkend_sal_num;
	}
	public void setWkend_sal_num(long wkend_sal_num) {
		this.wkend_sal_num = wkend_sal_num;
	}
	public int getM_sal_num() {
		return m_sal_num;
	}
	public void setM_sal_num(int m_sal_num) {
		this.m_sal_num = m_sal_num;
	}
	public int getW_sal_num() {
		return w_sal_num;
	}
	public void setW_sal_num(int w_sal_num) {
		this.w_sal_num = w_sal_num;
	}
	public int getSal_num_10() {
		return sal_num_10;
	}
	public void setSal_num_10(int sal_num_10) {
		this.sal_num_10 = sal_num_10;
	}
	public int getSal_num_20() {
		return sal_num_20;
	}
	public void setSal_num_20(int sal_num_20) {
		this.sal_num_20 = sal_num_20;
	}
	public int getSal_num_30() {
		return sal_num_30;
	}
	public void setSal_num_30(int sal_num_30) {
		this.sal_num_30 = sal_num_30;
	}
	public int getSal_num_40() {
		return sal_num_40;
	}
	public void setSal_num_40(int sal_num_40) {
		this.sal_num_40 = sal_num_40;
	}
	public int getSal_num_50() {
		return sal_num_50;
	}
	public void setSal_num_50(int sal_num_50) {
		this.sal_num_50 = sal_num_50;
	}
	public int getSal_num_60() {
		return sal_num_60;
	}
	public void setSal_num_60(int sal_num_60) {
		this.sal_num_60 = sal_num_60;
	}
	private long sal_money_40;
	private long sal_money_50;
	private long sal_money_60;
	// sales_2
	
	private long wday_sal_num;
	public long getWday_sal_num() {
		return wday_sal_num;
	}
	public void setWday_sal_num(long wday_sal_num) {
		this.wday_sal_num = wday_sal_num;
	}
	private long wkend_sal_num;
	private int m_sal_num;
	private int w_sal_num;
	// sales_3
	
	private int sal_num_10;
	private int sal_num_20;
	private int sal_num_30;
	private int sal_num_40;
	private int sal_num_50;
	private int sal_num_60;
	// sales_4

	

}

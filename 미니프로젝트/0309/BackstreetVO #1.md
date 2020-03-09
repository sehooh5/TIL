# BackstreetVO #1

```java
//20200309(mon)
//데이터 테이블에 있는 모든 VO 작성 (게시판 제외)
//toString 으로 출력 가능하게 해놓았지만 생략 가능
package vo;

public class BackstreetVO {
	private int area_id;
	private int q_id; 
	private int all_living_num;
	private String area_coname;
	private int apart_num;
	private String serv_id;
	private String serv_coname;
	private int store_num;
	private int sim_store_num;
	private int start_rate;
	private int close_rate;
	private int start_store_num;
	private int close_store_num;
	private int all_job_num;
	private int month_sal_money;
	private int month_sal_num;
	private int wday_sal_money;
	private int wkend_sal_money;
	private int m_sal_money;
	private int w_sal_money;
	private int sal_money_10;
	private int sal_money_20;
	private int sal_money_30;
	private int sal_money_40;
	private int sal_money_50;
	private int sal_money_60;
	private int wday_sal_num;
	private int wkend_sal_num;
	private int m_sal_num;
	private int w_sal_num;
	private int sal_num_10;
	private int sal_num_20;
	private int sal_num_30;
	private int sal_num_40;
	private int sal_num_50;
	private int sal_num_60;
	private int gu_id;
	private int change_id;
	private String change_coname;
	private String gu_coname;
	private int oper_month;
	private int close_month;
	private int x;
	private int y;
	private int cust_id;
	private int mana_id;
	private String cust_name;
	private String cust_email;
	private String cust_pw;
	private String mana_email;
	private String mana_pw;
	private String gu_name;
	
	//getter and setter method
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
	public int getAll_living_num() {
		return all_living_num;
	}
	public void setAll_living_num(int all_living_num) {
		this.all_living_num = all_living_num;
	}
	public String getArea_coname() {
		return area_coname;
	}
	public void setArea_coname(String area_coname) {
		this.area_coname = area_coname;
	}
	public int getApart_num() {
		return apart_num;
	}
	public void setApart_num(int apart_num) {
		this.apart_num = apart_num;
	}
	public String getServ_id() {
		return serv_id;
	}
	public void setServ_id(String serv_id) {
		this.serv_id = serv_id;
	}
	public String getServ_coname() {
		return serv_coname;
	}
	public void setServ_coname(String serv_coname) {
		this.serv_coname = serv_coname;
	}
	public int getStore_num() {
		return store_num;
	}
	public void setStore_num(int store_num) {
		this.store_num = store_num;
	}
	public int getSim_store_num() {
		return sim_store_num;
	}
	public void setSim_store_num(int sim_store_num) {
		this.sim_store_num = sim_store_num;
	}
	public int getStart_rate() {
		return start_rate;
	}
	public void setStart_rate(int start_rate) {
		this.start_rate = start_rate;
	}
	public int getClose_rate() {
		return close_rate;
	}
	public void setClose_rate(int close_rate) {
		this.close_rate = close_rate;
	}
	public int getStart_store_num() {
		return start_store_num;
	}
	public void setStart_store_num(int start_store_num) {
		this.start_store_num = start_store_num;
	}
	public int getClose_store_num() {
		return close_store_num;
	}
	public void setClose_store_num(int close_store_num) {
		this.close_store_num = close_store_num;
	}
	public int getAll_job_num() {
		return all_job_num;
	}
	public void setAll_job_num(int all_job_num) {
		this.all_job_num = all_job_num;
	}
	public int getMonth_sal_money() {
		return month_sal_money;
	}
	public void setMonth_sal_money(int month_sal_money) {
		this.month_sal_money = month_sal_money;
	}
	public int getMonth_sal_num() {
		return month_sal_num;
	}
	public void setMonth_sal_num(int month_sal_num) {
		this.month_sal_num = month_sal_num;
	}
	public int getWday_sal_money() {
		return wday_sal_money;
	}
	public void setWday_sal_money(int wday_sal_money) {
		this.wday_sal_money = wday_sal_money;
	}
	public int getWkend_sal_money() {
		return wkend_sal_money;
	}
	public void setWkend_sal_money(int wkend_sal_money) {
		this.wkend_sal_money = wkend_sal_money;
	}
	public int getM_sal_money() {
		return m_sal_money;
	}
	public void setM_sal_money(int m_sal_money) {
		this.m_sal_money = m_sal_money;
	}
	public int getW_sal_money() {
		return w_sal_money;
	}
	public void setW_sal_money(int w_sal_money) {
		this.w_sal_money = w_sal_money;
	}
	public int getSal_money_10() {
		return sal_money_10;
	}
	public void setSal_money_10(int sal_money_10) {
		this.sal_money_10 = sal_money_10;
	}
	public int getSal_money_20() {
		return sal_money_20;
	}
	public void setSal_money_20(int sal_money_20) {
		this.sal_money_20 = sal_money_20;
	}
	public int getSal_money_30() {
		return sal_money_30;
	}
	public void setSal_money_30(int sal_money_30) {
		this.sal_money_30 = sal_money_30;
	}
	public int getSal_money_40() {
		return sal_money_40;
	}
	public void setSal_money_40(int sal_money_40) {
		this.sal_money_40 = sal_money_40;
	}
	public int getSal_money_50() {
		return sal_money_50;
	}
	public void setSal_money_50(int sal_money_50) {
		this.sal_money_50 = sal_money_50;
	}
	public int getSal_money_60() {
		return sal_money_60;
	}
	public void setSal_money_60(int sal_money_60) {
		this.sal_money_60 = sal_money_60;
	}
	public int getWday_sal_num() {
		return wday_sal_num;
	}
	public void setWday_sal_num(int wday_sal_num) {
		this.wday_sal_num = wday_sal_num;
	}
	public int getWkend_sal_num() {
		return wkend_sal_num;
	}
	public void setWkend_sal_num(int wkend_sal_num) {
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
	public int getGu_id() {
		return gu_id;
	}
	public void setGu_id(int gu_id) {
		this.gu_id = gu_id;
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
	public String getGu_coname() {
		return gu_coname;
	}
	public void setGu_coname(String gu_coname) {
		this.gu_coname = gu_coname;
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getMana_id() {
		return mana_id;
	}
	public void setMana_id(int mana_id) {
		this.mana_id = mana_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public String getCust_pw() {
		return cust_pw;
	}
	public void setCust_pw(String cust_pw) {
		this.cust_pw = cust_pw;
	}
	public String getMana_email() {
		return mana_email;
	}
	public void setMana_email(String mana_email) {
		this.mana_email = mana_email;
	}
	public String getMana_pw() {
		return mana_pw;
	}
	public void setMana_pw(String mana_pw) {
		this.mana_pw = mana_pw;
	}
	public String getGu_name() {
		return gu_name;
	}
	public void setGu_name(String gu_name) {
		this.gu_name = gu_name;
	}
	
	@Override
	public String toString() {
		return "BackstreetVO [area_id=" + area_id + ", q_id=" + q_id + ", all_living_num=" + all_living_num
				+ ", area_coname=" + area_coname + ", apart_num=" + apart_num + ", serv_id=" + serv_id
				+ ", serv_coname=" + serv_coname + ", store_num=" + store_num + ", sim_store_num=" + sim_store_num
				+ ", start_rate=" + start_rate + ", close_rate=" + close_rate + ", start_store_num=" + start_store_num
				+ ", close_store_num=" + close_store_num + ", all_job_num=" + all_job_num + ", month_sal_money="
				+ month_sal_money + ", month_sal_num=" + month_sal_num + ", wday_sal_money=" + wday_sal_money
				+ ", wkend_sal_money=" + wkend_sal_money + ", m_sal_money=" + m_sal_money + ", w_sal_money="
				+ w_sal_money + ", sal_money_10=" + sal_money_10 + ", sal_money_20=" + sal_money_20 + ", sal_money_30="
				+ sal_money_30 + ", sal_money_40=" + sal_money_40 + ", sal_money_50=" + sal_money_50 + ", sal_money_60="
				+ sal_money_60 + ", wday_sal_num=" + wday_sal_num + ", wkend_sal_num=" + wkend_sal_num + ", m_sal_num="
				+ m_sal_num + ", w_sal_num=" + w_sal_num + ", sal_num_10=" + sal_num_10 + ", sal_num_20=" + sal_num_20
				+ ", sal_num_30=" + sal_num_30 + ", sal_num_40=" + sal_num_40 + ", sal_num_50=" + sal_num_50
				+ ", sal_num_60=" + sal_num_60 + ", gu_id=" + gu_id + ", change_id=" + change_id + ", change_coname="
				+ change_coname + ", gu_coname=" + gu_coname + ", oper_month=" + oper_month + ", close_month="
				+ close_month + ", x=" + x + ", y=" + y + ", cust_id=" + cust_id + ", mana_id=" + mana_id
				+ ", cust_name=" + cust_name + ", cust_email=" + cust_email + ", cust_pw=" + cust_pw + ", mana_email="
				+ mana_email + ", mana_pw=" + mana_pw + ", gu_name=" + gu_name + "]";
	}
	
	
}

```


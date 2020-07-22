package vo;

public class jobVO {
	private int area_id;
	private int q_id;
	private int all_job_num;
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
	public int getAll_job_num() {
		return all_job_num;
	}
	public void setAll_job_num(int all_job_num) {
		this.all_job_num = all_job_num;
	}
	
	@Override
	public String toString() {
		return "jobVO [area_id=" + area_id + ", q_id=" + q_id + ", all_job_num=" + all_job_num + "]";
	}
	
	
}

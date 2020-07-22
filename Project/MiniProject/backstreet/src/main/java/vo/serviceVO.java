package vo;

public class serviceVO {
	private String serv_id;
	private String serv_coname;
	
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
	
	@Override
	public String toString() {
		return "serviceVO [serv_id=" + serv_id + ", serv_coname=" + serv_coname + "]";
	}
	
	
}

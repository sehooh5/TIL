package vo;

public class storeVO {
   private String q_id;
   private int area_id;
   private String serv_id;
   private int store_num;
   private int sim_store_num;
   private int start_rate;
   private int start_store_num;
   private int close_rate;
   private int close_store_num;
   
   public String getQ_id() {
      return q_id;
   }
   public void setQ_id(String q_id) {
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
   public int getStart_store_num() {
      return start_store_num;
   }
   public void setStart_store_num(int start_store_num) {
      this.start_store_num = start_store_num;
   }
   public int getClose_rate() {
      return close_rate;
   }
   public void setClose_rate(int close_rate) {
      this.close_rate = close_rate;
   }
   public int getClose_store_num() {
      return close_store_num;
   }
   public void setClose_store_num(int close_store_num) {
      this.close_store_num = close_store_num;
   }
   @Override
   public String toString() {
      return "storeVO [q_id=" + q_id + ", area_id=" + area_id + ", serv_id=" + serv_id + ", store_num=" + store_num
            + ", sim_store_num=" + sim_store_num + ", start_rate=" + start_rate + ", start_store_num="
            + start_store_num + ", close_rate=" + close_rate + ", close_store_num=" + close_store_num + "]";
   }
}
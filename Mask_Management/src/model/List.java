package model;

public class List {

	private int list_id;   // 订单号
	private String list_state;   // 订单状态
	private long list_date;   // 订单时间
	
	public long getList_date() {
		return list_date;
	}
	public void setList_date(long list_date) {
		this.list_date = list_date;
	}
	public int getList_id() {
		return list_id;
	}
	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public String getList_state() {
		return list_state;
	}
	public void setList_state(String list_state) {
		this.list_state = list_state;
	}
	
	
	
}

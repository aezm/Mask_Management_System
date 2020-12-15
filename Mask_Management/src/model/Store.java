package model;

import java.sql.PreparedStatement;

public class Store {

	private int shop_id;
	private int mask_id;
	private int store_num;
	
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getMask_id() {
		return mask_id;
	}
	public void setMask_id(int mask_id) {
		this.mask_id = mask_id;
	}
	public int getStore_num() {
		return store_num;
	}
	public void setStore_num(int store_num) {
		this.store_num = store_num;
	}
	
	
}

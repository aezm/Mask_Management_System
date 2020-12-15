package model;

/**
 * 用户实体
 * @author 52639
 *
 */
public class User
{
	private String user_phone;   // 手机号
	private String user_pwd;     // 密码
	private String user_name;	 // 姓名
	private String id;			 // 身份证号
	private long last_order_time;// 上次预约时间
	
	public String getUser_phone()
	{
		return user_phone;
	}
	public void setUser_phone(String user_phone) 
	{
		this.user_phone = user_phone;
	}
	
	public String getUser_pwd() 
	{
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) 
	{
		this.user_pwd = user_pwd;
	}
	
	public String getUser_name() 
	{
		return user_name;
	}
	public void setUser_name(String user_name) 
	{
		this.user_name = user_name;
	}
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public long getLast_order_time() 
	{
		return last_order_time;
	}
	public void setLast_order_time(long last_order_time) 
	{
		this.last_order_time = last_order_time;
	}
}

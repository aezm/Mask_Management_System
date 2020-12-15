package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil
{
	
	/**
	 * 返回当前时间毫秒数
	 * @return
	 */
	public static long getTheTime()
	{
		Date date=new Date();
		long time = date.getTime();
		
		return time;
	}
	
	
	/**
	 * 返回规范模式时间
	 * @param time
	 * @return
	 */
	public static String outTheTime(long time)
	{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(time); 
        
        return str;
	}
}

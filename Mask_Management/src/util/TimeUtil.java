package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil
{
	
	/**
	 * ���ص�ǰʱ�������
	 * @return
	 */
	public static long getTheTime()
	{
		Date date=new Date();
		long time = date.getTime();
		
		return time;
	}
	
	
	/**
	 * ���ع淶ģʽʱ��
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

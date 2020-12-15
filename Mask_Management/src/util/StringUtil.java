package util;

public class StringUtil
{
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if("".equals(str.trim()) || str == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñ²»Îª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str)
	{
		if(str != null && !("".equals(str.trim())))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

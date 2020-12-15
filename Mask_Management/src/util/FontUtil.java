package util;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class FontUtil {
	
	/**
	 * 设置全局字体
	 * @param font
	 */
	public static void initGobalFont(Font font) {  
	    FontUIResource fontResource = new FontUIResource(font);  
	    for(Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {  
	        Object key = keys.nextElement();  
	        Object value = UIManager.get(key);  
	        if(value instanceof FontUIResource) {  
	            System.out.println(key);  
	            UIManager.put(key, fontResource);  
	        }  
	    }  
	}
}

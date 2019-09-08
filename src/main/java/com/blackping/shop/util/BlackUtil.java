package com.blackping.shop.util;

public class BlackUtil {
	public static String NPEtoString(Object obj) {
		if(null == obj) return "";
		return obj.toString();
	}
}

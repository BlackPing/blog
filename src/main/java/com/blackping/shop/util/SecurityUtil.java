package com.blackping.shop.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class SecurityUtil {
	public static String find_UserName(SecurityContext sc) {
		return sc.getAuthentication().getName();
	}
	
	public static String find_SessionID(SecurityContext sc) {
		WebAuthenticationDetails details = (WebAuthenticationDetails) sc.getAuthentication().getDetails();
		return details.getRemoteAddress().toString();
	}
	
	public static String find_Ip(SecurityContext sc) {
		WebAuthenticationDetails details = (WebAuthenticationDetails) sc.getAuthentication().getDetails();
		return details.getSessionId().toString();
	}
	
	public static String find_Authorities(SecurityContext sc) {
		return sc.getAuthentication().getAuthorities().toString();
	}
}

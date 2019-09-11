package com.blackping.shop.util;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BlackUtil {
	public static String NPEtoString(Object obj) {
		if(null == obj) return "";
		return obj.toString();
	}
	
	public static HashMap<String, Object> errors(BindingResult bindresult) {
		List<ObjectError> errors = bindresult.getAllErrors();
		HashMap<String, Object> errorsMap = new HashMap<String, Object>();
		
		if(errors.size() > 0) {
			for(ObjectError error : errors) {
				Object[] Argument = error.getArguments();
				DefaultMessageSourceResolvable dms = (DefaultMessageSourceResolvable) Argument[0];
				
				errorsMap.put(dms.getDefaultMessage(), error.getDefaultMessage());
			}
		}
		
		return errorsMap;
	}
}

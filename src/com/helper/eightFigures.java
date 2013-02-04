package com.helper;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

// add some commas to the doller bills ya'll

public class eightFigures {
	public static String moneyProper(String money) {
		if(!StringUtils.contains(money, '.')) {
			return money + ".00";
		}
		
		double amount = Double.parseDouble(money);
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		
		return formatter.format(amount);		
	}
	
	public static String phoneProper(String phoneNumber) {
		
        StringBuilder sb = new StringBuilder(phoneNumber)
                                .insert(0,"(")
                                .insert(4,") ")
                                .insert(9,"-");
        String output = sb.toString();
        return output;
		
	}
}
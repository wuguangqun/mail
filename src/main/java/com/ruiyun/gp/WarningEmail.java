package com.ruiyun.gp;

import com.ruiyun.gp.utils.EmailUtil;

public class WarningEmail {

	public static String sendEmail(String mail, String msg) {
		try {
			EmailUtil.sendMial(msg, mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public static void main(String[] args) {
		sendEmail("2482513235@qq.com", "hai");
	}
}

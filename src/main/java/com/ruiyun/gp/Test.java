package com.ruiyun.gp;

public class Test {

	public static String main(String name) {

		return name != null && !"".equals(name.trim()) ? name + ",hello!!!!" : "请正确传入参数！！！";
	}

}

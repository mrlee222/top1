package com.webservice.wsdd;

public class HelloWSDD {
	//定制提供web服务的方法
	private int requestCount = 0;
	public String hello(String name){
		requestCount++;
		System.out.println("调用的次数"+requestCount);
		return "欢迎你"+name;
	}
	public Float add(Float a,Float b){
		requestCount++;
		System.out.println("调用的次数"+requestCount);
		return a+b;
	}

}

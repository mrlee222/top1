package com.webservice.wsdd;

public class HelloWSDD {
	//�����ṩweb����ķ���
	private int requestCount = 0;
	public String hello(String name){
		requestCount++;
		System.out.println("���õĴ���"+requestCount);
		return "��ӭ��"+name;
	}
	public Float add(Float a,Float b){
		requestCount++;
		System.out.println("���õĴ���"+requestCount);
		return a+b;
	}

}

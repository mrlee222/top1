package haha;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.webservice.wsdd.HelloWSDD;

public class Employee {
	
	private int id;
	private String name;
	
	public static void main(String[] args) {
	    HelloWSDD d = new HelloWSDD();
	    
	}
	@Test
	public void test1(){
		Set<String> a = new HashSet<String>();
		Set<String> b = new HashSet<String>();

		a.add("111");
		a.add("222");
		a.add("333");

		b.add("222");
		b.add("333");
		b.add("444");

		Set<String> a1 = new HashSet<String>();
		Set<String> b1 = new HashSet<String>();
		
		a1.addAll(a);
		b1.addAll(b);
		
		System.out.println("a1 as the clone of a:"+a1);
		System.out.println("b1 as the clone of b:"+b1);
		
		a1.removeAll(b);
		b1.removeAll(a);
		
		System.out.println("In a but not in b:"+a1);
		System.out.println("In b but not in a:"+b1);
	}
	@Test
	public  void testCou(){
		BigDecimal bd = new BigDecimal(20.00);
		String bdStr = String.valueOf(bd);
		StringBuffer sBuffer = new StringBuffer();
		String[] strArray = bdStr.split(".");
		for (int i = 0; i < strArray.length; i++) {
			sBuffer.append(strArray[i]);
			
			
		}
		System.out.println(sBuffer);
		
		
	}
	
	@Test
	public  void test3(){
		 Calendar calendar=Calendar.getInstance();    
	     calendar.setTime(new Date());    
	     System.out.println("现在时间是："+new Date());    
	     String year=String.valueOf(calendar.get(Calendar.YEAR));    
	     String month=String.valueOf(calendar.get(Calendar.MONTH)+1);    
	     String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));    
	     String week=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);  
	     System.out.println("现在时间是："+year+"年"+month+"月"+day+"日，星期"+week); 
	     int year2 = calendar.MONTH;
	     System.out.println(year2);
	}
	
		

}


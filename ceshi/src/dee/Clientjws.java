package dee;

import handle.HandlerService;
import handle.LogHandler;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import objectWS.anni.Order;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


public class Clientjws {

	public static void main(String[] args) {
         try {
        	 //定义字符串，描述要访问的服务的地址
        	 String url = "http://localhost:8080/axis/HelloWs.jws";
        	 //创建一个webservice的服务
        	 Service service =new Service();
        	 //创建一个调用
        	Call call = (Call) service.createCall();
        	//指定服务的来源
        	call.setTargetEndpointAddress(url);
        	//指定调用的具体的方法名
        	call.setOperationName(new QName(url,"test"));
        	//执行远端调用
        	String result = (String) call.invoke(new Object[]{"Student","teacher"});
        	System.out.println(result);
			
		} catch (Exception e) {
		}
	}
	@Test
	public void test1() throws Exception{
		String url = "http://localhost:8080/axis/services/HelloWSDD";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(url));
		call.setOperationName(new QName(url, "add"));
		Float returnvalue = (Float) call.invoke(new Object[]{new Float(4.3),new Float(5.5)});
		System.out.println(returnvalue);
		
	}
	@Test
	public void testOrder() throws Exception{
		//调用的ws的方法，返回值是javaobject对象，要求在客户端必须创建java对象
		Order order =  new Order();
		order.setId("3");
		String url = "http://localhost:8080/axis/services/OrderService";
		Service service = new Service();
		Call call = (Call) service.createCall();
		//注册javaBean注意和server-config.wsdd编写的内容一致
		QName qName = new QName("urn:BeanService", "Order");
		call.registerTypeMapping(Order.class, qName, new BeanSerializerFactory(Order.class, qName), new BeanDeserializerFactory(Order.class, qName));
	    String name = "no";
	    //创建调用
	    
	    call.setTargetEndpointAddress(new URL(url));
	    call.setOperationName(new QName("order","returnorder"));
	    //设定传入的参数
	    call.addParameter("arg1", qName, ParameterMode.IN);
	    //设置返回的类型
	    call.setReturnType(qName,Order.class);
	    //接受结果
	    Order result = (Order) call.invoke(new Object[]{order});
	    if(result != null){
	    	System.err.println(result.getName());
	    }
	    
	
	}
	@Test
	public void testhandlerServer() throws Exception{
		Log log = LogFactory.getLog(Clientjws.class);
		String url = "http://localhost:8080/axis/services/HandlerService";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(url));
		call.setOperationName(new QName("HandleredService","publicMethod"));
		String result = (String) call.invoke(new Object[]{"Mr zk"});
		log.info(result);
	}
	@Test
	public void testAuthentical() throws Exception{
		Log log = LogFactory.getLog(Clientjws.class);
		String url = "http://localhost:8080/axis/services/HandlerService";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(url));
		call.setOperationName(new QName("HandleredService","publicMethod"));
		//由于当前的ws方法，添加认证的信息，设置访问的权限
		call.getMessageContext().setUsername("andy");
		call.getMessageContext().setPassword("123456");
		String result = (String) call.invoke(new Object[]{"Authen message"});
		log.info(result);
	}
	@Test
	public void testAuthentical2() throws Exception{
		Log log = LogFactory.getLog(Clientjws.class);
		String url = "http://localhost:8080/axis/services/HandlerService";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(url));
		call.setOperationName(new QName("HandleredService","publicMethod"));
		//由于当前的ws方法，添加认证的信息，设置访问的权限
		call.getMessageContext().setUsername("andy");
		call.getMessageContext().setPassword("123456");
		String result = (String) call.invoke(new Object[]{"Authen message33"});
		log.info(result);
	}

}

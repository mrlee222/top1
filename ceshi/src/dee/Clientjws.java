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
        	 //�����ַ���������Ҫ���ʵķ���ĵ�ַ
        	 String url = "http://localhost:8080/axis/HelloWs.jws";
        	 //����һ��webservice�ķ���
        	 Service service =new Service();
        	 //����һ������
        	Call call = (Call) service.createCall();
        	//ָ���������Դ
        	call.setTargetEndpointAddress(url);
        	//ָ�����õľ���ķ�����
        	call.setOperationName(new QName(url,"test"));
        	//ִ��Զ�˵���
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
		//���õ�ws�ķ���������ֵ��javaobject����Ҫ���ڿͻ��˱��봴��java����
		Order order =  new Order();
		order.setId("3");
		String url = "http://localhost:8080/axis/services/OrderService";
		Service service = new Service();
		Call call = (Call) service.createCall();
		//ע��javaBeanע���server-config.wsdd��д������һ��
		QName qName = new QName("urn:BeanService", "Order");
		call.registerTypeMapping(Order.class, qName, new BeanSerializerFactory(Order.class, qName), new BeanDeserializerFactory(Order.class, qName));
	    String name = "no";
	    //��������
	    
	    call.setTargetEndpointAddress(new URL(url));
	    call.setOperationName(new QName("order","returnorder"));
	    //�趨����Ĳ���
	    call.addParameter("arg1", qName, ParameterMode.IN);
	    //���÷��ص�����
	    call.setReturnType(qName,Order.class);
	    //���ܽ��
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
		//���ڵ�ǰ��ws�����������֤����Ϣ�����÷��ʵ�Ȩ��
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
		//���ڵ�ǰ��ws�����������֤����Ϣ�����÷��ʵ�Ȩ��
		call.getMessageContext().setUsername("andy");
		call.getMessageContext().setPassword("123456");
		String result = (String) call.invoke(new Object[]{"Authen message33"});
		log.info(result);
	}

}

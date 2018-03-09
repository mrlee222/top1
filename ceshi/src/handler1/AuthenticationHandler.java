package handler1;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.axis.security.simple.SimpleSecurityProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthenticationHandler extends BasicHandler {
       Log log = LogFactory.getLog(AuthenticationHandler.class);
	//������֤�Ĳ�����Ϣ
       String securityProvider ="securityProvider" ;//��ȫ�������
       String unauthenticated = "unauthenticated";//δ��֤
       String authenticatedUser = "authenticatedUser";//��֤
       String canAuth = "canAuth";//��֤���û�
	
	
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
           log.info("AuthenticationHandler:invoke:start");
	       SecurityProvider provider = (SecurityProvider) msgContext.getProperty(securityProvider);//��ȡ��ǰ�İ�ȫ����
	       //�жϵ�ǰ�İ�ȫ����״̬
	       if(provider==null){
	    	   //����һ����ȫ�ļ���
	    	   provider = new SimpleSecurityProvider();
	    	   //��������ȫ�������õ���ǰws������
	    	   msgContext.setProperty(securityProvider, provider);
	       }
	       if(provider != null){
	    	   //��ȡ��ǰws�������֤����Ϣ
	    	   String userid = msgContext.getUsername();
	    	   String password = msgContext.getPassword();
	    	   log.info(userid+","+password);
	    	   //�Է��ʵ��û�������֤
	    	   AuthenticatedUser authUser = provider.authenticate(msgContext);
	       if(authUser == null){
	    	   throw new AxisFault(unauthenticated,"error",null,null);
	       }
	       //�û�ͨ����֤�����û�����Ϊ��֤�û�
	       msgContext.setProperty(authenticatedUser, authUser);
	       
	       }
	       log.info("Authentication Handler:invoke:end");
	
	}
	
	public static void main(String[] args) {
		new AuthenticationHandler();
	}

}

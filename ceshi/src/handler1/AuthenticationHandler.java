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
	//定义认证的参数信息
       String securityProvider ="securityProvider" ;//安全级别对象
       String unauthenticated = "unauthenticated";//未认证
       String authenticatedUser = "authenticatedUser";//认证
       String canAuth = "canAuth";//认证的用户
	
	
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
           log.info("AuthenticationHandler:invoke:start");
	       SecurityProvider provider = (SecurityProvider) msgContext.getProperty(securityProvider);//获取当前的安全服务
	       //判断当前的安全机制状态
	       if(provider==null){
	    	   //创建一个安全的级别
	    	   provider = new SimpleSecurityProvider();
	    	   //将创建安全级别设置到当前ws服务中
	    	   msgContext.setProperty(securityProvider, provider);
	       }
	       if(provider != null){
	    	   //获取当前ws服务的认证的信息
	    	   String userid = msgContext.getUsername();
	    	   String password = msgContext.getPassword();
	    	   log.info(userid+","+password);
	    	   //对访问的用户进行认证
	    	   AuthenticatedUser authUser = provider.authenticate(msgContext);
	       if(authUser == null){
	    	   throw new AxisFault(unauthenticated,"error",null,null);
	       }
	       //用户通过认证，将用户设置为认证用户
	       msgContext.setProperty(authenticatedUser, authUser);
	       
	       }
	       log.info("Authentication Handler:invoke:end");
	
	}
	
	public static void main(String[] args) {
		new AuthenticationHandler();
	}

}

package handler1;

import java.util.StringTokenizer;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.i18n.Messages;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//编写授权的handler
public class AuthorizatHandler extends BasicHandler {
       Log log = LogFactory.getLog(AuthorizatHandler.class);
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
          log.info("start:");
          //首先创建一个授权的用户
          AuthenticatedUser user =(AuthenticatedUser) msgContext.getProperty("authenticatedUser");
	      if(user == null){
	    	  throw new AxisFault("server,not user",Messages.getMessage("needUser()"),null,null);
	      }
	      //从认证用户中获取用户名
	      String userid = user.getName();
	      //获取当前的服务
	     Handler serviceHandler = msgContext.getService();
	     if(serviceHandler == null){
	    	 throw new AxisFault(Messages.getMessage("needServvice"));
	    	 
	     }
	     //获取服务名
	     String serviceName = serviceHandler.getName();
	     //生成访问角色信息
	     String allowdRole = (String) serviceHandler.getOption("allowedRoles");
	          if(allowdRole == null){
	        	  log.info("不需要验证");
	        	  return;
	          }
	      //开始授权认证
	          SecurityProvider provider = (SecurityProvider) msgContext.getProperty("securityProvider");
            if(provider == null){
            	throw new AxisFault(Messages.getMessage("noSecuritye"));
            	
            }
            //根据allowedRoles字符串进行验证
            for(StringTokenizer st= new StringTokenizer(allowdRole, ",");st.hasMoreTokens();){
            	//获取第一个roles信息
            	String thisRoles = st.nextToken();
            	//比对
            	if(provider.userMatches(user, thisRoles)){
            		log.info("通过验证");
            		return;
            	}
            }
            //没有通过验证和授权，不能访问目标的服务
            throw new AxisFault("Service unauthentication:"+Messages.getMessage("cant auth"));
	
	
	}
	public static void main(String[] args) {
		new AuthorizatHandler();
	}

}

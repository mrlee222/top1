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
//��д��Ȩ��handler
public class AuthorizatHandler extends BasicHandler {
       Log log = LogFactory.getLog(AuthorizatHandler.class);
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
          log.info("start:");
          //���ȴ���һ����Ȩ���û�
          AuthenticatedUser user =(AuthenticatedUser) msgContext.getProperty("authenticatedUser");
	      if(user == null){
	    	  throw new AxisFault("server,not user",Messages.getMessage("needUser()"),null,null);
	      }
	      //����֤�û��л�ȡ�û���
	      String userid = user.getName();
	      //��ȡ��ǰ�ķ���
	     Handler serviceHandler = msgContext.getService();
	     if(serviceHandler == null){
	    	 throw new AxisFault(Messages.getMessage("needServvice"));
	    	 
	     }
	     //��ȡ������
	     String serviceName = serviceHandler.getName();
	     //���ɷ��ʽ�ɫ��Ϣ
	     String allowdRole = (String) serviceHandler.getOption("allowedRoles");
	          if(allowdRole == null){
	        	  log.info("����Ҫ��֤");
	        	  return;
	          }
	      //��ʼ��Ȩ��֤
	          SecurityProvider provider = (SecurityProvider) msgContext.getProperty("securityProvider");
            if(provider == null){
            	throw new AxisFault(Messages.getMessage("noSecuritye"));
            	
            }
            //����allowedRoles�ַ���������֤
            for(StringTokenizer st= new StringTokenizer(allowdRole, ",");st.hasMoreTokens();){
            	//��ȡ��һ��roles��Ϣ
            	String thisRoles = st.nextToken();
            	//�ȶ�
            	if(provider.userMatches(user, thisRoles)){
            		log.info("ͨ����֤");
            		return;
            	}
            }
            //û��ͨ����֤����Ȩ�����ܷ���Ŀ��ķ���
            throw new AxisFault("Service unauthentication:"+Messages.getMessage("cant auth"));
	
	
	}
	public static void main(String[] args) {
		new AuthorizatHandler();
	}

}

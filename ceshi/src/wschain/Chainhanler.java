package wschain;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

public class Chainhanler extends BasicHandler{

	@Override
	public void invoke(MessageContext msgcontext) throws AxisFault {
		System.out.println("��ʱ���õ��ǵ�һ��handler����");
		
	}

}

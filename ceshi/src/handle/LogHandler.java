package handle;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Handler;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.handlers.soap.SOAPService;

public class LogHandler extends BasicHandler{
   //invoke������ÿһ��Handler����ʵ�ֵķ���
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
	//ÿ��web���񱻵��ã���¼log��
		try {
			//ͨ��MessageContext���󣬻�ȡweb����
			SOAPService handl = msgContext.getService();
			//����log���ļ���
			String filename = (String) this.getOption("filename");//��ȡ��ǰweb�ϵ�log��־�ļ�
			if(filename == null || filename.equals("")){
				throw new AxisFault("Server nologFile", "no logfile configuration for the loghandler", null, null);
			}
			//����һ�������ļ������
			FileOutputStream fos = new FileOutputStream(filename, true);
			//��װ�����ļ������--��ʽ���������
			PrintWriter writer = new PrintWriter(fos);
			//ͳ��web���񱻵����˼���
			Integer count = (Integer) handl.getOption("accesses");
			if(count == null){
				count = new Integer(0);
				count = new Integer(count.intValue()+1);
				Date date = new Date();
				//�ڿ���̨������ʾ
				msgContext.getMessage().writeTo(System.out);
				//������־��Ϣ
				String result = "��"+date+":web����"+msgContext.getTargetService()+"�����ã���ֹ������һ��������"+count+"��";
			handl.setOption("accesses", count);
			writer.println(result);
			writer.close();
			
			}
		} catch (Exception e) {
		}
	}

}

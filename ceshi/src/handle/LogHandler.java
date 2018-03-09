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
   //invoke方法是每一个Handler必须实现的方法
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
	//每当web服务被调用，记录log中
		try {
			//通过MessageContext对象，获取web服务
			SOAPService handl = msgContext.getService();
			//生成log的文件名
			String filename = (String) this.getOption("filename");//获取当前web上的log日志文件
			if(filename == null || filename.equals("")){
				throw new AxisFault("Server nologFile", "no logfile configuration for the loghandler", null, null);
			}
			//创建一个基础文件输出流
			FileOutputStream fos = new FileOutputStream(filename, true);
			//包装基础文件输出流--格式化的输出流
			PrintWriter writer = new PrintWriter(fos);
			//统计web服务被调用了几次
			Integer count = (Integer) handl.getOption("accesses");
			if(count == null){
				count = new Integer(0);
				count = new Integer(count.intValue()+1);
				Date date = new Date();
				//在控制台上做显示
				msgContext.getMessage().writeTo(System.out);
				//创建日志信息
				String result = "在"+date+":web服务"+msgContext.getTargetService()+"被调用，截止到现在一共调用了"+count+"次";
			handl.setOption("accesses", count);
			writer.println(result);
			writer.close();
			
			}
		} catch (Exception e) {
		}
	}

}

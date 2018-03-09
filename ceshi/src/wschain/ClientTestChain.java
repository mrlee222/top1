package wschain;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ClientTestChain {

	public static void main(String[] args) throws ServiceException, RemoteException {
		String url = "http://localhost:8080/axis/services/Hellowrokld1";
        Service service =  new Service();
        Call call = (Call) service.createCall();
        call.setOperationName(new QName(url,"hello"));
        String result = (String) call.invoke(new Object[]{"Student"});
        System.out.println(result);
	}

}

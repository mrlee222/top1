package objectWS.anni;

import java.math.BigDecimal;

public class OrderService {
	public Order returnorder(Order order){
		Order newOrder = new Order();
		if(order.getId().equals("1")){
			newOrder.setName("Andy");
		}else{
			newOrder.setName("Selina");
		}
		return newOrder;
		 
	}
	
	public static void main(String[] args) {
		BigDecimal b1 =new BigDecimal(Double.toString(2.2));
		BigDecimal b2 =new BigDecimal(Double.toString(3));
		BigDecimal ji = b1.multiply(b2);
		System.out.println(Math.abs("Ð¡Ð¡¿¡".hashCode()%1024));
		
	}

}

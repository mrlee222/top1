package wschain;

public class Hellowrokld1 {
	private int requestcount = 0;
        public String hello(String name){
        	System.out.println("requestCount="+requestcount);
        	
        	System.out.println("you receive name="+name);
        	return name;
        	
        }
        public static void main(String[] args) {
			new Hellowrokld1();
		}
}

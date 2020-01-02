package etapp.module.ppt;

public class Util {
	
	private static Util instance = null;
	
	private Util() {
		
	}
	
	public static Util getInstance() {
		if ( instance == null ) instance = new Util();
		return instance;
	}
	
    public boolean isNumeric(String s) {  
    	return s.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
    }
    
    public static void main(String[] args) {
    	Util u = Util.getInstance();
    	System.out.println(u.isNumeric("-8989.9090"));
    }
    
    

}

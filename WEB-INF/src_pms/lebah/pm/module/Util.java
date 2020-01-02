package lebah.pm.module;

public class Util {
	
	int max = 30;
	
	public String getRemarkAtLine(int no, String remark) {
		String str = "";
		int length = remark.length();
		for ( int i=0;i<length-1;i++) {
			str += remark.substring(i,i+1);
			if ( i > max ) break;
		}
		
		return str;
	}
	
	public static void main(String[] args) {
		
		String remark = "Your computer is currently violating a network policy. Your computer does not have any antivirus software installed. Please install antivirus software to gain access to the network.";
		Util u = new Util();
		String str = u.getRemarkAtLine(1, remark);
		System.out.println(str);
		
	}

}

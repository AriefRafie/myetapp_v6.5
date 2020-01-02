package ekptg.fpx;

import java.net.URLEncoder;

public class FpxUtility {
	public static String numberToWord(int no){
		String [] sa ={"","satu","dua","tiga","empat","lima","enam","tujuh","lapan","sembilan"};
		String [] belas={"sepuluh","sebelas","dua belas","tiga belas","empat belas","lima belas","enam belas","tujuh belas","lapan belas","sembilan belas"};
		final String puluh[] = new String[]{"", "dua puluh", "tiga puluh", "empat puluh", "lima puluh", "enam puluh", "tujuh puluh", "lapan puluh", "sembilan puluh"}; 
		final String ratus = "ratus"; 
		final String ribu = "ribu"; 
		final String juta = "juta"; 

		if(no == 0)
			return "kosong";
		
		StringBuilder numWord = new StringBuilder(); 

		int n = no; 

		// append with selective recursion for all our cases 
		if (n >= 1000000) { 
		numWord.append(numberToWord(n / 1000000)); 
		numWord.append(' '); 
		numWord.append(juta); 
		numWord.append(' '); 
		n %= 1000000; 
		} 

		if (n >= 1000) { 
		numWord.append(numberToWord(n / 1000)); 
		numWord.append(' '); 
		numWord.append(ribu); 
		numWord.append(' '); 
		n %= 1000; 
		} 
		if (n >= 100) { 
		numWord.append(sa[n / 100]); 
		numWord.append(' '); 
		numWord.append(ratus); 
		numWord.append(' '); 
		n %= 100; 

		} 
		if (n >= 20) { 
		numWord.append(puluh[(n / 10) - 1]); 
		numWord.append(' '); 
		n %= 10; 
		} 
		if (n >= 10) { 
		numWord.append(belas[n - 10]); 
		} 
		if (n < 10) { 
			numWord.append(sa[n]); 
		}


		return numWord.toString().trim(); 
	}
	public static String convertCent(int cent){
		String [] sa ={"","satu","dua","tiga","empat","lima","enam","tujuh","lapan","sembilan"};
		String [] belas={"sepuluh","sebelas","dua belas","tiga belas","empat belas","lima belas","enam belas","tujuh belas","lapan belas","sembilan belas"};
		final String puluh[] = new String[]{"", "dua puluh", "tiga puluh", "empat puluh", "lima puluh", "enam puluh", "tujuh puluh", "lapan puluh", "sembilan puluh"};
		if(cent == 0)
			return "";
		
		int n = cent; 

		// append with selective recursion for all our cases 
	
		StringBuilder numWord = new StringBuilder(); 
	
		if (n >= 20) { 
			numWord.append(puluh[(n / 10) - 1]); 
		numWord.append(' '); 
		n %= 10; 
		} 
		if (n >= 10) { 
		numWord.append(belas[n - 10]); 
		} 
		if (n < 10) { 
			numWord.append(sa[n]); 
		}


		return numWord.toString().trim(); 
	}
	public static String generateWord(double amount){
		if(amount == 0)
			return "kosong";
		
		
		String input = String.format("%.2f",amount);
		
		String cent = input.substring(input.indexOf(".")+1,input.length());
		String note = input.substring(0,input.indexOf("."));
		
		String x = FpxUtility.numberToWord(Integer.parseInt(note));
		String y = FpxUtility.convertCent(Integer.parseInt(cent));
		StringBuffer bff = new StringBuffer();
		if(!x.equals("")){
			bff.append(x);
		}
		if(!y.equals("")){
			bff.append(" dan ");
			bff.append(y);
			bff.append(" sen");
		}
		
		bff.append(" sahaja");
		return bff.toString();
	}
	public static String generateWordEncodeURL(double amount)throws Exception{
		String encode =  URLEncoder.encode(generateWord(amount),"UTF-8");
		return encode;
	}
	public static void main(String [] args){
		String input ="dua ratus sahaja";
		try{
		System.out.println(generateWord(1201.21));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

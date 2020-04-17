package ekptg.report;

import java.io.Serializable;

/*  
 * sample to call this method from jsp pages.
   $EkptgUtil.subString("hello world from azam",0,5)
 */
public class Utils implements Serializable  {

	public static String show() {
		return "ekptg.report.Utils";
	}
	
	public static String getBulanSebelum(int i){
		String m="";
		String[]names = {"12", "01", "02","03", "04", "05","06", "07", "08","09", "10", "11"};
		m = names[i-1];
		return m;
	}
	
	public static String getTahunSebelum(int bulan,int i){
		String m="";
		if(bulan==1) 
			m = ""+(i-1);
		else
			m = ""+i;
		return m;
	}
	
	public static String getBulan(int i){
		String m="";
		String[]names = {"JANUARI", "FEBRUARI", "MAC","APRIL", "MEI", "JUN","JULAI", "OGOS", "SEPTEMBER","OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		return m;
	}
	public static String getBulanRingkas(int i){
		String m="";
		String[]names = {"JAN", "FEB", "MAC","APR", "MEI", "JUN","JUL", "OGOS", "SEPT","OKT", "NOV", "DIS"};
		m = names[i-1];
		return m;
	}	
	 public static String getBulanNum(int i){
			String m2="";
			String[]num = {"01","02","03","04","05","06","07","08","09","10","11","12"};
			m2 = num[i-1];
			return m2;
	 }
}

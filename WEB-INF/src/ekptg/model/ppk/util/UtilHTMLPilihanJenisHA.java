package ekptg.model.ppk.util;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.htp.utiliti.IUtilHTMLPilihan;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
//
import org.apache.log4j.Logger;


public class UtilHTMLPilihanJenisHA implements IUtilHTMLPilihan{

	static Logger myLog = Logger.getLogger(ekptg.model.ppk.util.UtilHTMLPilihanJenisHA.class);
	
	public String Pilihan(String name) throws Exception {
		return Pilihan(name, null, null, null);		
	}

	public String Pilihan(String name, String jsFunction) throws Exception {
		return Pilihan(name, null, null, jsFunction);		
	}

	public String Pilihan(String name, String selectedValue,String disability) throws Exception {
		return Pilihan(name, selectedValue, disability, null);		
	}
	
	public String Pilihan(String name, String selectedValue,String disability, String jsFunction) 
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + name + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH JENIS HARTA</option>\n");
			FrmPrmhnnSek8DaftarSek8InternalData logic = new FrmPrmhnnSek8DaftarSek8InternalData(); 
			Vector<?> v = logic.getJenisHa();
			//myLog.info(v);
			Hashtable<String,String> f = null;
			
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<String,String>) v.get(i);
				if (String.valueOf(f.get("idjenisha")).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				String jenisHA="";
				if(f.get("idjenisha").equals("4"))
					jenisHA = "(Pengambilan Balik Tanah)";
						
				sb.append("<option " + s + " value=" + f.get("idjenisha") + ">"
						+ f.get("kod")+" - "+f.get("keterangan") +jenisHA
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	

}
package ekptg.model.utils.rujukan;

import java.util.Vector;

import ekptg.model.entities.Tblsemakan;
import ekptg.model.utils.IUtilHTMLPilihan;
//import org.apache.log4j.Logger;


public class UtilHTMLPilihanSemakan implements IUtilHTMLPilihan{

	//static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.UtilHTMLPilihanMT.class);
	
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
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Tblsemakan> v = ekptg.model.htp.FrmSemakan.getSemakan(null,null);

			Tblsemakan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblsemakan) v.get(i);
				if (String.valueOf(f.getIdSemakan()).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSemakan() + ">"
						+ f.getPerihal()
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
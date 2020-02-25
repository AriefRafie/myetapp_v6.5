package ekptg.model.utils.rujukan;

import java.util.Vector;

import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.utils.IUtilHTMLPilihan;
//import org.apache.log4j.Logger;


public class UtilHTMLPilihanJenisHakmilik implements IUtilHTMLPilihan{

	//static Logger myLog = Logger.getLogger(ekptg.model.htp.utils.rujukan.UtilHTMLPilihanJenisHakmilik.class);
	
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
			Vector<Tblrujjenishakmilik> v = DBPPT.getJenisHakmiliks("");
			Tblrujjenishakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenishakmilik) v.get(i);
				if (String.valueOf(f.getIdJenishakmilik()).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenishakmilik() + ">"
						+ f.getKodJenisHakmilik()+" - "+f.getKeterangan()
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
package ekptg.model.utils.rujukan;

import ekptg.model.utils.IUtilHTMLPilihan;
//import org.apache.log4j.Logger;


public class UtilHTMLPilihanPerolehanLaporanRekod implements IUtilHTMLPilihan{

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
			String[] urusan = getUrusan(); 
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			//Vector<Tblsemakan> v = ekptg.model.htp.FrmSemakan.getSemakan(null,null);

			String f = null;
			String s = "";
			for (int i = 0; i < urusan.length; i++) {
				f = (String) urusan[i];
				if (String.valueOf(i).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + i + ">"
						+ urusan[i]
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	private String[] getUrusan() {
		String[] urusan = {"PERMOHONAN"
						,"PEMBELIAN"
						,"PERLETAKHAKAN"
						}; 
		return urusan;
		
	}

}
package ekptg.model.utils.rujukan;

import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.utils.IUtilHTMLPilihanExt;
//import org.apache.log4j.Logger;


public class UtilHTMLPilihanJenisPBPPK implements IUtilHTMLPilihanExt{

	//static Logger myLog = Logger.getLogger(ekptg.model.utils.rujukan.UtilHTMLPilihanJenisPBPPK.class);
	public String Pilihan(String selectName
		,String selectedValue
		,String disability
		,String jsFunction
		,String Status) throws Exception {
		String status = "Y";
			
		StringBuffer sb = new StringBuffer("");
		Db db = null;
		try {
			db = new Db();
			FrmPrmhnnSek8DaftarSek8InternalData internalData = new FrmPrmhnnSek8DaftarSek8InternalData();
			if(Status != null)
				status = Status;
					
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			
			if (jsFunction != null)
				sb.append(jsFunction);
				
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Hashtable<?,?>> v = internalData.getListStatusPemilikDb(status,db);
			Hashtable<?,?> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<?,?>) v.get(i);
				if (String.valueOf(f.get("id")).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("id") + ">"
					+ f.get("kod") + " - "
					+ String.valueOf(f.get("keterangan")).toUpperCase() + "</option>\n");
			}
				sb.append("</select>");
				
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		}
		return sb.toString();
			
	}
	

}
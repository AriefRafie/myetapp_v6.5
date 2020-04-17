package ekptg.model.ppk;
/**
 * Created by Mohamad Rosli
 * create alternative utilities for PPK 
 * */
import java.util.Hashtable;
import java.util.Vector;

public class UtilHTML {
	/**
	 * Create selection Unit Pusaka 
	 * */
	public static String SelectUnitPPK(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>Sila Pilih</option>\n");
			Vector<?> v = FrmUtilData.getUnitPPK();
			Hashtable<?, ?> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<?, ?>) v.get(i);
				if (f.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("id")
						+ ">" + f.get("keterangan").toString().toUpperCase() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}



}
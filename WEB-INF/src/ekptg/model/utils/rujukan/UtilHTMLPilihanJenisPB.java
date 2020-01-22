package ekptg.model.utils.rujukan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujjenisnopb;
import ekptg.model.utils.IUtilHTMLPilihan;
import etapp.entity.rujukan.JenisRizab;
//import org.apache.log4j.Logger;


public class UtilHTMLPilihanJenisPB implements IUtilHTMLPilihan{

	//static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.UtilHTMLJenisRizabBean.class);
	
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
			Vector<Tblrujjenisnopb> v = DB.getRujJenisNoPB();
			//Vector<?> v = getData(null);
			Tblrujjenisnopb f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenisnopb) v.get(i);
				if (String.valueOf(f.getIdJenisnopb()).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenisnopb() + ">"
						+ f.getKodJenisNopb()+" - "+f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	public Vector<JenisRizab> getData(String ids) {
		Db db = null;
		Vector<JenisRizab> v = new Vector<JenisRizab>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT RJR.ID_JENISRIZAB, RJR.KOD_JENIS, UPPER(RJR.KETERANGAN) KETERANGAN, RJR.AKTIF "+
			" FROM TBLHTPRUJJENISRIZAB RJR "+
			" WHERE RJR.AKTIF = '1' ";
			if(ids != null){
				sql +=" AND RJR.ID_JENISRIZAB in ("+ids+") ";
			}
			sql +=" ORDER BY RJR.KOD_JENIS ";
			ResultSet rs = stmt.executeQuery(sql);
			JenisRizab jenisRizabPTP = null;
			while(rs.next()){
				jenisRizabPTP = new JenisRizab();
				jenisRizabPTP.setId(rs.getLong("ID_JENISRIZAB"));
				jenisRizabPTP.setKod(rs.getString("KOD_JENIS"));
				jenisRizabPTP.setKeterangan(rs.getString("KETERANGAN"));			
				v.addElement(jenisRizabPTP);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();		
		}finally{
			 if (db != null){	db.close();	}
		}
		return v;
		
	}
	

}
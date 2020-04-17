package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujstatus;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserPegawaiBean;


public class UtilHTMLBean implements IUtilHTML{

	static Logger myLog = Logger.getLogger(ekptg.model.htp.UtilHTMLBean.class);
 	private IUserPegawai iUserPegawai = null;  

	/**
	 * Created by 	: Mohamad Rosli 
	 * Created on 	: 06/02/2010 
	 * Updated by 	: 21/02/2018 (Salin dari HTML.java) 
	 * Propose 		: Menyenarai kakitangan yang berjawatan Pegawai Pra syarat : Pengguna biasa
	 * 				  hendaklah diassign kepada sub -Pegawai- terlebih dahulu
	 */
	public String SelectPegawaiMengikutSeksyen(String idSeksyen,
		String idPegawai, String selectName, Long selectedValue,
		String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");

			//Vector v = ekptg.model.utils.FrmMappingUserPegawaiData.getSenaraiPegawai(idSeksyen, idPegawai, null);
			Vector<Hashtable<String, String>> v = getIUP().getSenaraiPegawai(idSeksyen, idPegawai, null);
			Hashtable<String, String> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<String, String>) v.get(i);
				if (f.get("idpegawai").equals("" + selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("idpegawai") + ">"
						+ f.get("namapegawai") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	public String SelectStatusPermohonan(String selectName) throws Exception {
		return SelectStatusPermohonan(selectName, null, null, null,null);		
	}

	public String SelectStatusPermohonan(String selectName, String jsFunction,String idSubUrusans)
		throws Exception {
		return SelectStatusPermohonan(selectName, null, null, jsFunction,idSubUrusans);
	}
//    
//	public String SelectNegeri(String selectName, Long selectedValue)
//			throws Exception {
//		return SelectStatusPermohonan(selectName, selectedValue, null, null,null);
//	}

	public String SelectStatusPermohonan(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectStatusPermohonan(selectName, selectedValue, disability, null,null);
	}

	public String SelectStatusPermohonan(String selectName, Long selectedValue,
			String disability,String idSubUrusans) throws Exception {
		return SelectStatusPermohonan(selectName, selectedValue, disability, null,idSubUrusans);
	}
	
	public String SelectStatusPermohonan(String selectName, Long selectedValue,
			String disability, String jsFunction, String idSubUrusans ) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<?> v = getStatus(idSubUrusans);
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public Vector getStatus(String idSubUrusans) {
		Db db = null;
		Vector v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT distinct(RS.ID_STATUS) ID_STATUS, RS.KOD_STATUS, RS.KETERANGAN "+
			" FROM TBLRUJSTATUS RS,TBLRUJSUBURUSANSTATUS RSUS "+
			" WHERE RSUS.ID_STATUS=RS.ID_STATUS "+
			" AND RSUS.ID_SUBURUSAN in ("+idSubUrusans+") "+
			" AND RSUS.AKTIF='1' AND RSUS.LANGKAH IS NOT NULL "+
			" ";
			//sql +=" ORDER BY P.ID_PERMOHONAN desc";
			//
			ResultSet rs = stmt.executeQuery(sql);
			Tblrujstatus rStatus = null;
			while(rs.next()){
				rStatus = new Tblrujstatus();
				rStatus.setIdStatus(rs.getLong("ID_STATUS"));
				rStatus.setKeterangan(rs.getString("KETERANGAN"));			
				v.addElement(rStatus);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			 if (db != null){	db.close();	}
		}
		return v;
		
	}
	 private IUserPegawai getIUP(){
		if(iUserPegawai== null)
			iUserPegawai = new UserPegawaiBean();
		
		return iUserPegawai;
	
	 }
	 
	 
}